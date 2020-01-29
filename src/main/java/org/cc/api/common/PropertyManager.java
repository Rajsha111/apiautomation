package org.cc.api.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {

    private static PropertyManager instance;
    private final String path = System.getProperty("user.dir") + "/projectConfig.properties";
    private Properties prop;

    private PropertyManager() {
        FileInputStream input = null;
        try {
            File config = new File(path);
            input = new FileInputStream(config.getAbsolutePath());
            prop = new Properties();
            prop.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    private Map<String, String> getServerConfigMAP() {
        Map<String, String> properties = new HashMap<String, String>();
        for (Object key : prop.keySet()) {
            properties.put(key.toString(), prop.getProperty(key.toString()));
        }
        return properties;
    }

    public String getValue(String key) {
        return System.getenv(key) != null ? System.getenv(key)
                : getServerConfigMAP().get(key);
    }

    public String getAccessKey() {
        return System.getenv("key") != null ? System.getenv("key")
                : getServerConfigMAP().get("key");
    }

    public String getBaseUrl() {
        return System.getenv("baseUrl") != null ? System.getenv("baseUrl")
                : getServerConfigMAP().get("baseUrl");
    }
}
