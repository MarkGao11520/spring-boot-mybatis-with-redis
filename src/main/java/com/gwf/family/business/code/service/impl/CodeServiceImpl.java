package com.gwf.family.business.code.service.impl;


import com.gwf.family.business.code.model.CodeSchema;
import com.gwf.family.business.code.model.CodeTable;
import com.gwf.family.business.code.service.CodeService;
import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.common.component.FamilyDbUtils;
import com.gwf.family.common.model.DbColumnModel;
import com.gwf.family.common.model.DbTableModel;
import com.gwf.family.common.model.ProjectConstant;
import com.gwf.family.common.util.DateUtil;
import com.gwf.family.common.util.ZipCompress;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * Created by lcy on 17/6/28.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
@Slf4j
public class CodeServiceImpl implements CodeService {
    private static final long serialVersionUID = -3827321264317955429L;
    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    @Autowired
    private FamilyDbUtils familyDbUtils;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Configuration freemarkerConfiguration;


    public List<CodeTable> getCodeTablesBySchema(CodeSchema schema) {
        List<CodeTable> tables = new ArrayList<>();
        Connection conn = familyDbUtils.getConnection(schema);
        ResultSet rs = null;
        DatabaseMetaData dbmd = null;
        CodeTable ct = null;

        try {
            dbmd = (DatabaseMetaData) conn.getMetaData();
            rs = (ResultSet) dbmd.getTables(null, schema.getDbName(), "%", null);
            while (rs.next()) {
                ct = new CodeTable();
                ct.setTableName(rs.getString("TABLE_NAME"));
                tables.add(ct);
            }
        } catch (Exception e) {
            throw new ServiceException("数据库连接信息错误,或数据库不存在数据表!");
        } finally {
            this.killConnection(rs, conn);
        }
        return tables;
    }

    @Override
    public String generateCode(CodeSchema codeSchema) throws Exception {
        String filePath = null;
        List<DbTableModel> tableModels = new ArrayList<DbTableModel>();
        List<DbColumnModel> models = null;
        ResultSet rs = null;
        DatabaseMetaData dbmd = null;
        CodeTable ct = null;
        DbTableModel tableModel = null;
        DbColumnModel model = null;
        Connection conn = familyDbUtils.getConnection(codeSchema);

        if (null == codeSchema.getTables() && "".equals(codeSchema.getTables().trim()))
            throw new ServiceException("没有选择任何表！");
        if (conn == null)
            throw new ServiceException("数据库连接错误！");

        String[] tables = codeSchema.getTables().split(",");

        try {
            dbmd = (DatabaseMetaData) conn.getMetaData();
            for (String table : tables) {
                // table
                tableModel = new DbTableModel();
                models = new ArrayList<DbColumnModel>();
                tableModel.setTableName(table.toLowerCase());
                rs = (ResultSet) dbmd.getTables(null, codeSchema.getDbName(), table,  new String[] { "TABLE" });
                while (rs.next()) {
                    if(rs.getString("TABLE_NAME").equals(table)){
                        String remarks = rs.getString("REMARKS");
                     //   String remarks = rs.getString("REMARKS");
                        tableModel.setRemarks(remarks);
                    }
                }
                //column
                rs = (ResultSet) dbmd.getColumns(null, codeSchema.getDbName(), table.toLowerCase(), null);
                dbmd.getPrimaryKeys(null, null, tables[0].toLowerCase());
                while (rs.next()) {
                    model = new DbColumnModel();
                    model.copyColumnFromSqlResult(rs);
                    models.add(model);
                }
                rs = (ResultSet) dbmd.getPrimaryKeys(null, codeSchema.getDbName(), table.toLowerCase());
                while (rs.next()) {
                    String column = rs.getString("COLUMN_NAME");
                    for (DbColumnModel cm : models) {
                        if (column.equals(cm.getColName())) {
                            cm.setKey(true);
                            break;
                        }
                    }
                }
                //table -> column
                tableModel.setColumnModels(models);
                tableModels.add(tableModel);
            }
        } catch (Exception e) {
            throw new ServiceException("生成表模型错误", e);
        } finally {
            this.killConnection(rs, conn);
        }
        filePath = this.productCodeFromTable(codeSchema.getAuthor(), codeSchema.getUserId(), codeSchema.getBasePackage(), tableModels);
        return filePath;
    }

    /**
     * 通过freemarker 成功多层代码
     *
     * @param tableModels
     * @return
     */
    private String productCodeFromTable(String author,
                                        String userId,
                                        String packageName,
                                        List<DbTableModel> tableModels)
            throws Exception {
        Map root = null;
        List<File> fileList = new ArrayList<File>();
        File file = null;
        String dirPath = request.getServletContext().getRealPath("code_generate_location") +
                File.separator + userId +
                File.separator + DateUtil.getSimepleDate("yyyyMMddHHmmss", new Date());
        String zipPath = dirPath + ".zip";
        String zipDirPath = dirPath;
        //将. 换/
        String pathPattern = packageName.replaceAll("\\.", "\\" + File.separator);
        //存在删除
        dirPath = dirPath + File.separator + pathPattern;  //!! 此行一定要在 zipPath 生成后
        File dirF = new File(dirPath);
        File zipFile = new File(zipPath);
        dirF.deleteOnExit();
        zipFile.deleteOnExit();
        //生成
        for (DbTableModel dbTableModel : tableModels) {
            root = new HashMap<String, Object>();
            String basePackage = packageName + "." + dbTableModel.getEntityName().toLowerCase();
            root.put("author", author);
            root.put("basePackage", basePackage);
            root.put("corePackage", ProjectConstant.BASE_PACKAGE);
            root.put("table", dbTableModel);
            //通过一个文件输出流，就可以写到相应的文件中
            //1.entity , mapper
            String newPath = dirPath + File.separator + dbTableModel.getEntityName().toLowerCase();
            file = this.generateEntityModel(newPath, dbTableModel, root, "Entity");
            fileList.add(file);

            file = this.generateEntityModel(newPath, dbTableModel, root, "Mapper");
            fileList.add(file);
            //2.service
            file = this.generateEntityModel(newPath, dbTableModel, root, "Service");
            fileList.add(file);
            //3.serviceImpl
            file = this.generateEntityModel(newPath, dbTableModel, root, "ServiceImpl");
            fileList.add(file);
            //4.dao
            file = this.generateEntityModel(newPath, dbTableModel, root, "Repository");
            fileList.add(file);
            //5.controller
            file = this.generateEntityModel(newPath, dbTableModel, root, "Controller");
            fileList.add(file);
            //6.list jsp

            //7.list js

            //8.add jsp

            //9.add js

            //10.core code
         //   genCoreCode(fileList, dirPath);
        }
        ZipCompress.compressExe(zipDirPath, zipPath);
        return zipPath;
    }

    /**
     * 关闭 connection
     *
     * @param rs
     * @param conn
     */
    private void killConnection(ResultSet rs, Connection conn) {
        try {
            if (null != rs)
                rs.close();
            if (null != conn)
                conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * model  构建  entity 层
     *
     * @param dirPath
     * @param dbTableModel
     * @param root
     * @return
     */
    private File generateEntityModel(String dirPath, DbTableModel dbTableModel, Map root, String suffix) throws Exception {
        //源文件夹
        File dirFile = null;
        //目标文件
        File file = null;
        FileWriter out = null;
        Template temp = null;
        if (suffix.equals("Repository"))
            dirFile = new File(dirPath + File.separator + "dao");
        else if (suffix.equals("ServiceImpl"))
            dirFile = new File(dirPath + File.separator + "service" + File.separator + "impl");
        else
            dirFile = new File(dirPath + File.separator + suffix.toLowerCase());
        //不存在 创建文件夹
        if (!dirFile.exists())
            dirFile.mkdirs();
        if (suffix.equals("Repository"))
            file = new File(dirPath + File.separator + "dao" +
                    File.separator + dbTableModel.getEntityName() + suffix + ".java");
        else if (suffix.equals("ServiceImpl"))
            file = new File(dirPath + File.separator + "service" + File.separator + "impl" +
                    File.separator + dbTableModel.getEntityName() + suffix + ".java");
        else if (suffix.equals("Entity"))
            file = new File(dirPath + File.separator + suffix.toLowerCase() +
                    File.separator + dbTableModel.getEntityName() + ".java");
        else if (suffix.equals("Mapper"))
            file = new File(dirPath + File.separator + suffix.toLowerCase() +
                    File.separator + dbTableModel.getEntityName() + suffix + ".xml");
        else
            file = new File(dirPath + File.separator + suffix.toLowerCase() +
                    File.separator + dbTableModel.getEntityName() + suffix + ".java");
        try {
            out = new FileWriter(file);
            //fileList.add(file);
            temp = freemarkerConfiguration.getTemplate(suffix.toLowerCase() + ".ftl");
            temp.process(root, out);
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return file;
    }

    private void genCoreCode(List<File> fileList, String dirPath) throws Exception {
        File dir = new File(PROJECT_PATH + JAVA_PATH + "/com/gwf/core/business/core");

        dirPath+=File.separator+"core";
        foreachDir(dir, fileList, dirPath);
    }

    private void foreachDir(File dir, List<File> fileList, String dirPath) throws Exception {
        File[] listFiles = dir.listFiles();

        for (File f : listFiles) {

            File file = null;
            File fileDir = null;
            if (f.isDirectory()) {
                String newPath = dirPath + File.separator + f.getName();
                foreachDir(f, fileList, newPath);
            } else {
                InputStreamReader reader = null;
                OutputStreamWriter writer = null;
                try {
                    fileDir = new File(dirPath + File.separator );
                    file = new File(dirPath + File.separator + File.separator + f.getName());
                    if (!fileDir.exists())
                        fileDir.mkdirs();
                    if (!file.exists())
                        file.createNewFile();
                    reader = new InputStreamReader(new FileInputStream(f));
                    writer = new OutputStreamWriter(new FileOutputStream(file));
                    char[] buffer = new char[1024];
                    int len;
                    while ((len =reader.read(buffer)) != -1)
                        writer.write(buffer,0,len);
                    fileList.add(file);
                } finally {
                    try {
                        reader.close();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
