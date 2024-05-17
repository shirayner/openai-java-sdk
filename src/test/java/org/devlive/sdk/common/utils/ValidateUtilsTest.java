package org.devlive.sdk.common.utils;

import org.devlive.sdk.common.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

public class ValidateUtilsTest
{

    @Test
    public void testValidateHost()
    {
        // host is empty
        Assert.assertThrows(ParamException.class, () -> ValidateUtils.validateHost("", null));
    }
}
