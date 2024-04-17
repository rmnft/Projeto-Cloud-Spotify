package com.example.projetoBigCloud;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("IBMEC - CLOUD")
                        .description("Open API do trabalho de cloud do Spotify")
                        .version("1.0")
                        .contact(new Contact()
                                .email("1805martinez@gmail.com")
                                .name("Gabriel Martinez")
                        )
                );
    }
}
