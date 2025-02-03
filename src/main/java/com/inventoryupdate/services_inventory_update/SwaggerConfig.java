package com.inventoryupdate.services_inventory_update;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Update API")
                        .version("1.0")
                        .description("Inventory Update API Documentation"))
                .servers(Collections.emptyList());
    }
}

