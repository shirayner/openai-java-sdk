package org.devlive.sdk.platform.google.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.common.exception.ParamException;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatEntity
{
    @JsonProperty(value = "contents")
    private List<ObjectEntity> contents;

    private ChatEntity(ChatEntityBuilder builder)
    {
        this.contents = builder.contents;
    }

    public static class ChatEntityBuilder
    {
        public ChatEntityBuilder contents(List<ObjectEntity> contents)
        {
            if (contents == null || contents.isEmpty()) {
                throw new ParamException("Invalid contents: " + contents);
            }

            this.contents = contents;
            return this;
        }

        public ChatEntity build()
        {
            return new ChatEntity(this);
        }
    }
}
