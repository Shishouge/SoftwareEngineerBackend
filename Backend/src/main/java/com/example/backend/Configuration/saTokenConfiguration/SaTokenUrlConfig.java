package com.example.backend.Configuration.saTokenConfiguration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class SaTokenUrlConfig {

    /**
     * 拦截url
     */
    @Value("#{'${sa-token.interceptUrlList}'.split(',')}")
    private List<String> interceptUrlList;

    /**
     * 开放url
     */
    @Value("#{'${sa-token.openUrlList}'.split(',')}")
    private List<String> openUrlList;

}
