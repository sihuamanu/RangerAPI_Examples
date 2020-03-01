package com.imooc.ranger;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class RangerErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new RangerClientException(response.status(), errorMessage(methodKey, response));
    }

    public String errorMessage(String methodKey, Response response) {
        String message = String.format("status %s reading %s", response.status(), methodKey);

        try {
            if (response.body() != null) {
                message += "content:\n" + Util.toString(response.body().asReader());
            }
        } catch (IOException e) {

        }
        return message;
    }
}
