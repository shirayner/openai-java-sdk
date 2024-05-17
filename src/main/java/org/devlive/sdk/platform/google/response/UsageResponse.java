package org.devlive.sdk.platform.google.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageResponse
{
    @JsonProperty(value = "promptTokenCount")
    private Integer promptTokenCount;

    @JsonProperty(value = "candidatesTokenCount")
    private Integer candidatesTokenCount;

    @JsonProperty(value = "totalTokenCount")
    private Integer totalTokenCount;
}
