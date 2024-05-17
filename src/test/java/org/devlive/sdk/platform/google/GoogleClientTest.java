package org.devlive.sdk.platform.google;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.devlive.sdk.ResourceUtils;
import org.devlive.sdk.platform.google.entity.ChatEntity;
import org.devlive.sdk.platform.google.entity.ObjectEntity;
import org.devlive.sdk.platform.google.entity.PartEntity;
import org.devlive.sdk.platform.google.model.RoleModel;
import org.devlive.sdk.platform.google.response.ChatResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

@Slf4j
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

    @Test
    public void testAutoClose()
    {
        try (GoogleClient client = GoogleClient.builder()
                .apiKey(token)
                .build()) {
            PartEntity part = PartEntity.builder()
                    .text("Hello, Open AI Java SDK!")
                    .build();
            ObjectEntity object = ObjectEntity.builder()
                    .parts(Lists.newArrayList(part))
                    .build();
            ChatEntity chat = ChatEntity.builder()
                    .contents(Lists.newArrayList(object))
                    .build();

            ChatResponse response = client.createChatCompletions(chat);
            response.getCandidates()
                    .forEach(item -> item.getContent()
                            .getParts()
                            .forEach(value -> log.info(value.getText())));

            Assert.assertNotNull(response);
        }
    }

    @Test
    public void testContinuousChat()
    {
        List<ObjectEntity> contents = Lists.newArrayList();
        PartEntity part = PartEntity.builder()
                .text("你好，我叫小明")
                .build();
        ObjectEntity object = ObjectEntity.builder()
                .parts(Lists.newArrayList(part))
                .build();
        contents.add(object);
        ChatEntity chat = ChatEntity.builder()
                .contents(contents)
                .build();
        ChatResponse response = client.createChatCompletions(chat);
        print(response, contents);

        ObjectEntity newObject = ObjectEntity.builder()
                .parts(Lists.newArrayList(PartEntity.builder()
                        .text("我刚刚说了什么")
                        .build()))
                .build();
        contents.add(newObject);
        ChatEntity newChat = ChatEntity.builder()
                .contents(contents)
                .build();
        ChatResponse newResponse = client.createChatCompletions(newChat);
        print(response, contents);
        Assert.assertNotNull(newResponse.getCandidates());
    }

    private void print(ChatResponse response, List<ObjectEntity> contents)
    {
        response.getCandidates()
                .forEach(item -> item.getContent()
                        .getParts()
                        .forEach(value -> {
                            log.info(value.getText());

                            contents.add(ObjectEntity.builder()
                                    .role(RoleModel.MODEL)
                                    .parts(Lists.newArrayList(PartEntity.builder()
                                            .text(value.getText())
                                            .build()))
                                    .build());
                        }));
    }
}
