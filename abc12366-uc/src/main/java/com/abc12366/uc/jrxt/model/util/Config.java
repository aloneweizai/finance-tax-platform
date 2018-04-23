package com.abc12366.uc.jrxt.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ljun
 * Date: 11/5/13
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class Config {

    protected Logger _log = LoggerFactory.getLogger(Config.class);

    private Properties properties;
    /**
     * @pdOid c2d51ad2-bac3-4e87-a02b-389255f7ce5f
     */
    private InputStream inputFile;

    public Config(String filePath){}

    public String getValue(String key) {
        String proFile = "application.properties";
        properties = new Properties();
        inputFile = Config.class.getClassLoader().getResourceAsStream(proFile);
        try {
            properties.load(inputFile);
        } catch (IOException e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            if(inputFile != null) {
//                try {
//                    inputFile.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        String name = properties.getProperty(key);
        return name;
    }
}
