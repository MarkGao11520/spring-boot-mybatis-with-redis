package com.gwf.family.common.util;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;
import java.io.Serializable;

/**
 * Created by lcy on 17/7/6.
 *
 * 压缩
 */
public class ZipCompress implements Serializable {
    private static final long serialVersionUID = -2522528934048955246L;

    public ZipCompress() {
    }

    /**
     * 执行压缩操作
     * @param srcPathName 需要被压缩的文件/文件夹
     */
    public static void compressExe(String srcPathName, String dest) {
        File srcdir = new File(srcPathName);
        if (!srcdir.exists()){
            throw new RuntimeException(srcPathName + "不存在！");
        }
        File zipFile = new File(dest);
        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
        fileSet.setExcludes(".DS_Store"); //排除哪些文件或文件夹
        zip.addFileset(fileSet);
        zip.execute();
    }



    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ZipCompress book = new ZipCompress();
        try{
            book.compressExe(
                    "/working/virtualDirectory/projectFiles/family/upload/codegenerate/0d76004eccb54b03ab9a07de145f520e/20170706224325/",
                    "/working/ZipTestCompressing.zip");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
