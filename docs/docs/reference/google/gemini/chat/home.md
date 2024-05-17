---
title: Chat
---

> 支持 Google Gemini，产品地址: https://ai.google.dev/gemini-api

### 简单对话

---

处理纯文字输入。借助此功能，您可以执行自然语言处理 (NLP) 任务，例如文本补全和摘要。

使用示例

```java
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
}
```

### 多轮对话（聊天）

---

打造互动式聊天体验。 借助此 API 的聊天功能，您可以收集多轮问题和回复，让用户能够逐步找到答案或获得有关多部分问题的帮助。

使用示例

```java
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

ObjectEntity newObject = ObjectEntity.builder()
        .parts(Lists.newArrayList(PartEntity.builder()
                .text("我刚刚说了什么")
                .build()))
        .build();
contents.add(newObject);
ChatEntity newChat = ChatEntity.builder()
        .contents(contents)
        .build();
client.createChatCompletions(newChat);
```

### 流式响应

---

以数据流的形式接收。流式响应会在模型生成增量数据时将这些数据发送回您的应用。

使用示例

```java
// 构建客户端
CountDownLatch countDownLatch = new CountDownLatch(1);
ConsoleEventSourceListener listener = ConsoleEventSourceListener.builder()
        .countDownLatch(countDownLatch)
        .build();
GoogleClient client = GoogleClient.builder()
        .apiKey(ResourceUtils.getValue("google.token"))
        .listener(listener)
        .build();

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
```
