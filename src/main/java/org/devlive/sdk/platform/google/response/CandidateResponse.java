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
public class CandidateResponse
{
    @JsonProperty(value = "content")
    private ContentResponse content;

    @JsonProperty(value = "finishReason")
    private String finishReason;

    @JsonProperty(value = "index")
    private Integer index;

    @JsonProperty(value = "safetyRatings")
    private List<SafetyRatingResponse> safetyRatings;

    @JsonProperty(value = "citationMetadata")
    private CitationMetadataResponse metadata;
}
