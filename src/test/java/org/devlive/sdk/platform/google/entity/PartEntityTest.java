package org.devlive.sdk.platform.google.entity;

import org.devlive.sdk.common.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

public class PartEntityTest
{
    private String text = "Hello, Open AI Java SDK!";

    @Test
    public void testText()
    {
        // test is empty
        Assert.assertThrows(ParamException.class, () -> PartEntity.builder()
                .text("")
                .build());

        // text is null
        Assert.assertThrows(ParamException.class, () -> PartEntity.builder()
                .text(null)
                .build());

        // test not blank
        Assert.assertEquals(text, PartEntity.builder()
                .text(text)
                .build()
                .getText());
    }
}
