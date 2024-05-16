package org.devlive.sdk.platform.google;

import com.google.common.collect.Lists;
import org.devlive.sdk.ResourceUtils;
import org.devlive.sdk.platform.google.entity.ChatEntity;
import org.devlive.sdk.platform.google.entity.ObjectEntity;
import org.devlive.sdk.platform.google.entity.PartEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GoogleClientTest
{
    private GoogleClient client;
    private String token;

    @Before
    public void before()
    {
        token = ResourceUtils.getValue("google.token");
        client = GoogleClient.builder()
                .apiKey(token)
                .build();
    }

    @Test
    public void testBuilder()
    {
        Assert.assertNotNull(client);
    }

    @Test
    public void testCreateChat()
    {
        PartEntity part = PartEntity.builder()
                .text("Hello, Open AI Java SDK!")
                .build();
        ObjectEntity object = ObjectEntity.builder()
                .parts(Lists.newArrayList(part))
                .build();
        ChatEntity chat = ChatEntity.builder()
                .contents(Lists.newArrayList(object))
                .build();

        Assert.assertNotNull(client.createChatCompletions(chat));
    }
}
