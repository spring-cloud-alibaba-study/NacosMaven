package com.js.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = TestProperties.TEST_PRO_KEY)
//@NacosConfigurationProperties(prefix = TestProperties.TEST_PRO_KEY, dataId = "operation-service",
//        type = ConfigType.YAML, autoRefreshed = true, groupId = "operation")
public class TestProperties {

    public static final String TEST_PRO_KEY = "env.test";
    private String appId;

}