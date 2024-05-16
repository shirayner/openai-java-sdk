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
public class ObjectEntity
{
    @JsonProperty(value = "parts")
    private List<PartEntity> parts;

    private ObjectEntity(ObjectEntityBuilder builder)
    {
        this.parts = builder.parts;
    }

    public static class ObjectEntityBuilder
    {
        public ObjectEntityBuilder parts(List<PartEntity> parts)
        {
            if (parts == null || parts.isEmpty()) {
                throw new ParamException("Invalid parts: " + parts);
            }

            this.parts = parts;
            return this;
        }

        public ObjectEntity build()
        {
            return new ObjectEntity(this);
        }
    }
}
