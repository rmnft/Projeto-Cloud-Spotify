package com.example.projetoBigCloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("IBMEC - CLOUD")
                        .description("Open API Spotify")
                        .version("1.0")
                        .contact(new Contact()
                                .email("raphaelmeres@gmail.com")
                                .name("Raphael Meres")
                        )
                );
    }
}
