package com.abhiraj.blog.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig(){
        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                .info(
                        new Info().title("Blog Platform API")
                                .description("API documentation for the secured blog platform created by Abhiraj Singh")
                )
                .tags(List.of(
                        new Tag().name("Authentication").description("Endpoints for user registration and authentication"),
                        new Tag().name("Users").description("User profile and account endpoints"),
                        new Tag().name("Posts").description("Blog post endpoints"),
                        new Tag().name("Comments").description("Comment endpoints for posts"),
                        new Tag().name("Admin").description("Admin-only endpoints")
                ))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

}
