package com.harghar.library.geosearchservice.exception;

import lombok.Getter;

@Getter
public class UpstreamServiceException extends RuntimeException {

    private final int statusCode;

    public UpstreamServiceException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
