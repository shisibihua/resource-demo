package com.honghe.util;

import com.honghe.dao.JdbcTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description
 * @Author sunchao
 * @Date: 2018-01-16 10:08
 * @Mender:
 */

public final class JdbcTemplateUtil {

    private static Properties properties=new Properties();

    public static String url;

    public static String userName;

    public static String password;

    private JdbcTemplateUtil() {

    }

    static {
        /**
         * update by caoqian
         * 由读取target/classess/config改为读取打包程序的config目录
         */
        String filePath=System.getProperty("user.dir")+ File.separator + "config" + File.separator+"jdbc.properties";
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("jdbc.url", "jdbc:mysql://localhost:8400/service?useUnicode=true&characterEncoding=utf8");
        userName =  properties.getProperty("jdbc.username", "root");
        password = properties.getProperty("jdbc.password", "bhjRjxwC8EBqaJC7");

    }

    public static final Properties getProperties() {
        return properties;
    }

    public static final JdbcTemplate getJdbcTemplate() {
        return com.honghe.dao.JdbcTemplateUtil.getJdbcTemplate(url, userName, password);
    }

}
