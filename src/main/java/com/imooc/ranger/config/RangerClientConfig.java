package com.imooc.ranger.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feign.Logger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RangerClientConfig {
    private int connectTimeoutMillis = 5 * 1000;
    private int readTimeoutMillis = 30 * 1000;
    private Logger.Level level = Logger.Level.BASIC;

    private String url = "http://10.65.42.129:6080";

    private RangerAuthConfig authConfig = new RangerAuthConfig();
}
