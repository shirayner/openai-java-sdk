package org.devlive.sdk.platform.google;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.devlive.sdk.ResourceUtils;
import org.devlive.sdk.common.listener.ConsoleEventSourceListener;
import org.devlive.sdk.platform.google.entity.ChatEntity;
import org.devlive.sdk.platform.google.entity.ObjectEntity;
import org.devlive.sdk.platform.google.entity.PartEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class GoogleStreamClientTest
{
    private GoogleClient client;
    private CountDownLatch countDownLatch;
    private String token;

    @Before
    public void before()
    {
        countDownLatch = new CountDownLatch(1);
        ConsoleEventSourceListener listener = ConsoleEventSourceListener.builder()
                .countDownLatch(countDownLatch)
                .build();
        token = ResourceUtils.getValue("google.token");
        client = GoogleClient.builder()
                .apiKey(token)
                .listener(listener)
                .build();
    }

    @Test
    public void testCreateChat()
    {
        List<ObjectEntity> contents = Lists.newArrayList();
        PartEntity part = PartEntity.builder()
                .text("帮我写一万字的作文")
                .build();
        ObjectEntity object = ObjectEntity.builder()
                .parts(Lists.newArrayList(part))
                .build();
        contents.add(object);
        ChatEntity chat = ChatEntity.builder()
                .contents(contents)
                .build();
        client.createChatCompletions(chat);
        try {
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            log.error("Interrupted while waiting", e);
        }
    }
}
