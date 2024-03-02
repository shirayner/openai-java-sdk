package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.beta.AssistantsEntity;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AssistantsTest
{
    private OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .extra("assistants=v1")
                .build();
    }

    @Test
    public void testAssistants()
    {
        AssistantsEntity entity = AssistantsEntity.builder()
                .name("Math Tutor")
                .model(CompletionModel.GPT_35_TURBO)
                .instructions("You are a personal math tutor. When asked a question, write and run Python code to answer the question.")
                .build();
        Assert.assertNotNull(client.createAssistants(entity));
    }

    @Test
    public void testCreateAssistantsFile()
    {
        Assert.assertNotNull(client.createAssistantsFile("file-jNuKdx61rNQ0FUhuPFpMNmGZ", "asst_xv9N9dNXstuV8OVLElLqgV7U"));
    }
}
