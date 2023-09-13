package com.get.hyphenbackendinquiry.global.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Hyphen Inquiry API Doc",
                description = "This is Hyphen inquiry API document.",
                version = "v1.0.0",
//                license = @License(
//                        name = "Apache License Version 2.0",
//                        url = "http://www.apache.org/licenses/LICENSE-2.0"
//                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                ),
                contact = @Contact(
                        name = "GET team"
                )
        )
//        tags = {
//                @Tag(name = "InquiryController", description = "<b>[문의]</b> 문의 API")
//        }
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi adminGroup() {
        List<io.swagger.v3.oas.models.tags.Tag> tags = List.of(
                new io.swagger.v3.oas.models.tags.Tag().name("InquiryController").description("<b>[문의]</b> 문의 API")
        );
        String[] pathsToMatch = {"/api/inquiry/inquirys/all", "/api/inquiry/inquirys/**/answer"};
        String[] pathsToExclude = {};
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch(pathsToMatch)
                .pathsToExclude(pathsToExclude)
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }

    @Bean
    public GroupedOpenApi managerGroup() {
        List<io.swagger.v3.oas.models.tags.Tag> tags = List.of();
        String[] pathsToMatch = {};
        String[] pathsToExclude = {"/api/inquiry/**"};
        return GroupedOpenApi.builder()
                .group("manager")
                .pathsToMatch(pathsToMatch)
                .pathsToExclude(pathsToExclude)
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }

    @Bean
    public GroupedOpenApi memberGroup() {
        List<io.swagger.v3.oas.models.tags.Tag> tags = List.of(
                new io.swagger.v3.oas.models.tags.Tag().name("InquiryController").description("<b>[문의]</b> 문의 API")
        );
        String[] pathsToMatch = {"/api/inquiry/**"};
        String[] pathsToExclude = {"/api/inquiry/inquirys/all", "/api/inquiry/inquirys/{id}/answer"};
        return GroupedOpenApi.builder()
                .group("member")
                .pathsToMatch(pathsToMatch)
                .pathsToExclude(pathsToExclude)
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }
}
