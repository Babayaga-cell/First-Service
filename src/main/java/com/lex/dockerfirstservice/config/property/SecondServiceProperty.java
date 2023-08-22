package com.lex.dockerfirstservice.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("second.calculate")
@Data
public class SecondServiceProperty {
    String postComputationUrl;
}
