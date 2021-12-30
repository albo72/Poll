package com.albo.util;

import com.albo.exception.PropertyManagerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {
    private Properties properties;

    public PropertyManager(String fileName) throws PropertyManagerException {
        properties = new Properties();
        try (InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) throw new PropertyManagerException("Error, i can't load:" + fileName);
            properties.load(is);
        } catch (IOException e) {
            throw new PropertyManagerException("Error, i can't load:" + fileName, e);
        }
    }

    public Map<String, String> getHashMap() {
        Map <String, String> map = new HashMap<>();
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()){
            String key = (String) enumeration.nextElement();
            String value = properties.getProperty(key);
            map.put(key,value);
        }
        return map;
    }

}
