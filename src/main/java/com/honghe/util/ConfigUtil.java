package com.honghe.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @author caoqian
 */
public class ConfigUtil
{
    private static Logger logger = Logger.getLogger(ConfigUtil.class);

    private static ConfigUtil INSTANCE = null;

    public static final ConfigUtil getInstance() {
        if (null == INSTANCE) {
            synchronized (ConfigUtil.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ConfigUtil();
                }
            }
        }
        return INSTANCE;
    }

    public Object getConfig(String key)
    {
        Object value = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"config/config.properties")));
            if ((properties.containsKey(key)) && (!("".equals(properties.getProperty(key))))) {
                value = properties.get(key);
            }
        }
        catch (IOException e) {
            logger.error("读取配置文件出现异常,key:" + key, e);
        }
        return value;
    }
}