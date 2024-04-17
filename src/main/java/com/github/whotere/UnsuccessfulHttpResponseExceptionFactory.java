package com.github.whotere;

import com.github.whotere.exception.UnsuccessfulHttpResponseException;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface UnsuccessfulHttpResponseExceptionFactory <T> {

    UnsuccessfulHttpResponseException nexException(HttpResponse<T> httpResponse) throws IOException;
}
