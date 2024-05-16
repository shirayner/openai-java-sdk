package org.devlive.sdk.platform.google.entity;

import com.google.common.collect.Lists;
import org.devlive.sdk.common.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

public class ObjectEntityTest
{
    @Test
    public void testParts()
    {
        // parts is null
        Assert.assertThrows(ParamException.class, () -> ObjectEntity.builder()
                .parts(null)
                .build());

        // parts is empty
        Assert.assertThrows(ParamException.class, () -> ObjectEntity.builder()
                .parts(Lists.newArrayList())
                .build());

        // parts is not empty
        PartEntity entity = PartEntity.builder()
                .text("Hello, Open AI Java SDK!")
                .build();
        Assert.assertEquals(1, ObjectEntity.builder()
                .parts(Lists.newArrayList(entity))
                .build()
                .getParts()
                .size());
    }
}
