package org.devlive.sdk;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class ResourceUtils
{
    /**
     * Get value from application.properties
     *
     * @param token key
     * @return value
     */
    public static String getValue(String token)
    {
        Properties properties = new Properties();
        try {
            properties.load(ResourceUtils.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties"));
        }
        catch (Exception e) {
            log.error("Load application.properties error", e);
        }
        return properties.getProperty(token);
    }
}
