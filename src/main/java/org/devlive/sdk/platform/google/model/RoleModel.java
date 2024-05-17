package org.devlive.sdk.platform.google.model;

import lombok.Getter;

public enum RoleModel
{
    USER("user"),
    MODEL("model");

    @Getter
    private final String value;

    RoleModel(String value)
    {
        this.value = value;
    }
}
