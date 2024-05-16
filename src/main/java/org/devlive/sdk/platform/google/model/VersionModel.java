package org.devlive.sdk.platform.google.model;

import lombok.Getter;

public enum VersionModel
{
    V1BETA("v1beta");

    @Getter
    private final String version;

    VersionModel(String version)
    {
        this.version = version;
    }
}
