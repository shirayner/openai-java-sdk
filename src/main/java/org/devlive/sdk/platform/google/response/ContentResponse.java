package org.devlive.sdk.platform.google.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.platform.google.entity.PartEntity;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentResponse
{
    @JsonProperty(value = "parts")
    private List<PartEntity> parts;

    @JsonProperty(value = "role")
    private String role;
}
