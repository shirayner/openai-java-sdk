package org.devlive.sdk.common.exception;

import lombok.Getter;

public class DefaultException
        extends RuntimeException
{
    @Getter
    private final String message;

    public DefaultException(String message)
    {
        this.message = message;
    }
}
