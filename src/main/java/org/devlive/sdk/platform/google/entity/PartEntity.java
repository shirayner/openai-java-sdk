package org.devlive.sdk.platform.google.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.common.exception.ParamException;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartEntity
{
    @JsonProperty(value = "text")
    private String text;

    private PartEntity(PartEntityBuilder builder)
    {
        this.text = builder.text;
    }

    public static class PartEntityBuilder
    {
        public PartEntityBuilder text(String text)
        {
            if (StringUtils.isBlank(text)) {
                throw new ParamException("Invalid text: " + text);
            }

            this.text = text;
            return this;
        }

        public PartEntity build()
        {
            return new PartEntity(this);
        }
    }
}
