package com.deliveryfood.datasource;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.Name;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource.master")
@RequiredArgsConstructor
public class MasterDatasource {

    String url;
    String username;
    String password;
    String driverName;
}