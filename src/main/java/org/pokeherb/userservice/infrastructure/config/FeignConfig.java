package org.pokeherb.userservice.infrastructure.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("org.pokeherb.userservice")
public class FeignConfig {
}
