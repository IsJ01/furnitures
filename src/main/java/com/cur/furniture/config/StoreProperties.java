package com.cur.furniture.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "store")
@Data
public class StoreProperties {
    private String name;
    private String phone;
    private String address;
}
