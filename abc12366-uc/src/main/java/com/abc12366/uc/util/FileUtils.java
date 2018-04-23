package com.abc12366.uc.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by MY on 2017-06-11.
 */
public class FileUtils {

    public final static String defaultFolder = "E://abc12306-uploadFolder";

    public static void main(String[] args) {
        File dir = new File("E://abc12306-");
        judeDirExists(dir);
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     */
    public static void judeFileExists(File file) {

        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断文件夹是否存在
     *
     * @param file
     */
    public static void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }

    public static String getDefaultFolder() {
        File dir = new File(defaultFolder);
        judeDirExists(dir);
        return defaultFolder;
    }
}
