package com.gwf.family.common.component;

import com.alibaba.druid.pool.DruidDataSource;
import com.gwf.family.business.code.model.CodeSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by lcy on 17/6/30.
 */
@Component
@Slf4j
public class FamilyDbUtils {
    /**
     * 获取       连接
     *
     * 通过druidSource 获取连接 连接信息错误时,无法catch住,暂未解决,连接不会停止,不断尝试连接
     *                      暂不使用
     *
     * @return
     *
     * select table_name from information_schema.tables where table_schema='csdb' and table_type='base table';
     *
     * show tables;
     */
    public SqlSession getConnectSession(CodeSchema schema){
        // data source
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl("jdbc:mysql://" + schema.getUrl() + ":3306/" + schema.getDbName());
        ds.setPassword(schema.getPassword());
        ds.setUsername(schema.getUsername());
        ds.setName(schema.getDbName());
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        if(ds.isTestOnReturn()) {
            ds.close();
        }
        //ds.setDriverClassName("");
        //ds.setDriver(new DruidDriver());
        // configure
        Configuration configuration = new Configuration();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment(schema.getUsername(),transactionFactory,ds);
        configuration.setEnvironment(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory.openSession();
    }


    /**
     * 通过jdbc 获取 connection
     * @param schema
     * @return
     */
    public Connection getConnection(CodeSchema schema) {

        Connection con = null;
        try {
            Class.forName(schema.getDriverClass());//创建驱动器
            con = DriverManager.getConnection(schema.getUrl(), schema.getUsername(), schema.getPassword());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return con;
    }

}
