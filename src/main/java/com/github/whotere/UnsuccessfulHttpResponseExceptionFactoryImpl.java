package com.github.whotere;

import com.github.whotere.exception.UnsuccessfulHttpResponseException;
import com.github.whotere.model.UnsuccessfulResponseBody;

import java.io.IOException;
import java.net.http.HttpResponse;

public class UnsuccessfulHttpResponseExceptionFactoryImpl implements UnsuccessfulHttpResponseExceptionFactory<String> {

    private final ModelMapper modelMapper;

    public UnsuccessfulHttpResponseExceptionFactoryImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UnsuccessfulHttpResponseException nexException(HttpResponse<String> httpResponse) throws IOException {

        String responseBodyJson = httpResponse.body();

        int statusCode = httpResponse.statusCode();
        String message = "The response from the server has the status code " + statusCode;

        UnsuccessfulResponseBody responseBodyObject = modelMapper.map(responseBodyJson, UnsuccessfulResponseBody.class);

        return new UnsuccessfulHttpResponseException(message, statusCode, responseBodyObject);
    }
}
