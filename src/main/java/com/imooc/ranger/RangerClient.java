package com.imooc.ranger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.imooc.ranger.api.PolicyApis;
import com.imooc.ranger.api.PolicyFeignClient;
import com.imooc.ranger.api.UserApis;
import com.imooc.ranger.api.UserFeignClient;
import com.imooc.ranger.config.RangerClientConfig;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
public class RangerClient {
    @Getter
    private UserApis userApis;

    @Getter
    private PolicyApis policyApis;

    private RangerClientConfig rangerClientConfig;

    public RangerClient(RangerClientConfig rangerClientConfig) {
        this.rangerClientConfig = rangerClientConfig;
    }

    private final static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final static JacksonEncoder encoder = new JacksonEncoder(mapper);
    private final static JacksonDecoder decoder = new JacksonDecoder(mapper);

    private AtomicBoolean started = new AtomicBoolean(false);

    public void start() {
        if (started.get()) {
            log.info("com.imooc.ranger client is already started");
            return;
        }

        userApis = new UserApis(feignBuilder().target(UserFeignClient.class, rangerClientConfig.getUrl()));
        policyApis = new PolicyApis(feignBuilder().target(PolicyFeignClient.class, rangerClientConfig.getUrl()));


        this.started.set(true);
        log.info("com.imooc.ranger client starting");
    }

    private Feign.Builder feignBuilder() {
        return Feign.builder().logger(new Logger.JavaLogger())
                .logLevel(rangerClientConfig.getLevel())
                .options(new Request.Options(rangerClientConfig.getConnectTimeoutMillis(), rangerClientConfig.getReadTimeoutMillis()))
                .encoder(encoder)
                .decoder(decoder)
                .client(new OkHttpClient())
                .errorDecoder(new RangerErrorDecoder())
                .requestInterceptor(new RangerHeadersInterceptor())
                .requestInterceptor(new BasicAuthRequestInterceptor(rangerClientConfig.getAuthConfig().getUsername(), rangerClientConfig.getAuthConfig().getPassword()));
    }

    public void stop() {
        if (started.get()) {
            this.started.set(false);
        } else {
            log.info("com.imooc.ranger client is not started");
        }
    }
}
