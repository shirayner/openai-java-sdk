package org.devlive.sdk.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.common.exception.ParamException;

public class ValidateUtils
{
    private ValidateUtils()
    {
    }

    /**
     * Validate host
     *
     * @param host Original host
     * @param defaultHost Default host
     * @return host
     */
    public static String validateHost(String host, String defaultHost)
    {
        if (StringUtils.isEmpty(host)) {
            return defaultHost;
        }
        else {
            boolean flag = host.startsWith("http") || host.startsWith("https");
            if (!flag) {
                throw new ParamException(String.format("Invalid apiHost <%s> must start with http or https", host));
            }
        }
        return host;
    }
}
