package org.devlive.sdk.platform.google.model;

import lombok.Getter;

public enum GenerativeModel
{
    GEMINI_PRO("gemini-pro"),
    GEMINI_1_0_PRO("gemini-1.0-pro"),
    GEMINI_1_5_PRO_LATEST("gemini-1.5-pro-latest"),
    GEMINI_1_5_FLASH_LATEST("gemini-1.5-flash-latest");

    @Getter
    private final String name;

    GenerativeModel(String name)
    {
        this.name = name;
    }
}
