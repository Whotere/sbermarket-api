package com.github.ynovice.exception;

import com.github.ynovice.model.UnsuccessfulResponseBody;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UnsuccessfulHttpResponseException extends RuntimeException{

    private final int statusCode;
    private final UnsuccessfulResponseBody unsuccessfulResponseBody;

    public UnsuccessfulHttpResponseException(String message,
                                             int statusCode,
                                             UnsuccessfulResponseBody unsuccessfulResponseBody) {
        super(message);
        this.statusCode = statusCode;
        this.unsuccessfulResponseBody = unsuccessfulResponseBody;
    }
}
