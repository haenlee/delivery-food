package com.deliveryfood.datasource;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource.slave")
@RequiredArgsConstructor
public class SlaveDatasource {

    String url;
    String username;
    String password;
    String driverName;
}