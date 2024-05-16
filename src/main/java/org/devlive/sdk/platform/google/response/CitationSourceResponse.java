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
public class CitationSourceResponse
{
    @JsonProperty(value = "startIndex")
    private Integer start;

    @JsonProperty(value = "endIndex")
    private Integer end;

    @JsonProperty(value = "uri")
    private String uri;

    @JsonProperty(value = "license")
    private String license;
}
