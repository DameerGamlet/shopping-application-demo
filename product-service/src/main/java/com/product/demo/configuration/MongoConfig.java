package com.product.demo.configuration;

import com.product.demo.converver.BinaryToUUIDConverter;
import com.product.demo.converver.UUIDToBinaryConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
                Arrays.asList(
                        new UUIDToBinaryConverter(),
                        new BinaryToUUIDConverter()
                )
        );
    }
}
