package org.devlive.sdk.platform.google.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponse
{
    @JsonProperty(value = "candidates")
    private List<CandidateResponse> candidates;

    @JsonProperty(value = "usageMetadata")
    private UsageResponse usage;
}
