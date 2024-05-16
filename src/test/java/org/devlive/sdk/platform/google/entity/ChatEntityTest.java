package org.devlive.sdk.platform.google.entity;

import com.google.common.collect.Lists;
import org.devlive.sdk.common.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

public class ChatEntityTest
{
    @Test
    public void testContents()
    {
        // contents is null
        Assert.assertThrows(ParamException.class, () -> ChatEntity.builder()
                .contents(null)
                .build());

        // contents is empty
        Assert.assertThrows(ParamException.class, () -> ChatEntity.builder()
                .contents(Lists.newArrayList())
                .build());

        // contents is not empty
        PartEntity part = PartEntity.builder()
                .text("Hello, Open AI Java SDK!")
                .build();
        ObjectEntity object = ObjectEntity.builder()
                .parts(Lists.newArrayList(part))
                .build();
        Assert.assertEquals(1, ChatEntity.builder()
                .contents(Lists.newArrayList(object))
                .build()
                .getContents()
                .size());
    }
}
