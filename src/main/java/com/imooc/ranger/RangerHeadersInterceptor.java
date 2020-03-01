package com.imooc.ranger;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class RangerHeadersInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Accept", "application/json");
        requestTemplate.header("X-XSRF-HEADER", "\"\"");
        requestTemplate.header("Content-Type", "application/json");
    }
}
