package org.devlive.sdk;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class ResourceUtilsTest
{
    @Test
    public void testGetValue()
    {
        String value = ResourceUtils.getValue("key");
        assertNull(value);
    }
}
