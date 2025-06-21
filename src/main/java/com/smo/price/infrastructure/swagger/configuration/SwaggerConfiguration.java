package com.smo.price.infrastructure.swagger.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_GROUP;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_CONTACT_EMAIL;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_CONTACT_NAME;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_CONTACT_URL;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_DESCRIPTION;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_LICENCE_NAME;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_LICENCE_URL;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_TERMS_OF_SERVICE;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_TITLE;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_INFO_VERSION;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_PATHS_TO_MATCH;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_PATHS_TO_MATCH_ORIGINAL;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_SCAN_PACKAGE;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_SERVER_DESCRIPTION;
import static com.smo.price.infrastructure.swagger.common.SwaggerConstants.UTILITY_OPEN_API_SERVER_URL;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group(UTILITY_OPEN_API_GROUP)
                .packagesToScan(UTILITY_OPEN_API_SCAN_PACKAGE)
                .pathsToMatch(UTILITY_OPEN_API_PATHS_TO_MATCH)
                .addOpenApiCustomizer(openApiCustomizer())
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().servers(buildApiServers());
    }

    private List<Server> buildApiServers() {
        return List.of(
                new Server().url(UTILITY_OPEN_API_SERVER_URL)
                        .description(UTILITY_OPEN_API_SERVER_DESCRIPTION)
        );
    }

    private OpenApiCustomizer openApiCustomizer() {
        return openAPI -> {
            openAPI.info(buildApiInfo());

            if (openAPI.getPaths() != null) {
                openAPI.setPaths(sanitizePaths(openAPI));
            }
        };
    }

    private Info buildApiInfo() {
        return new Info()
                .title(UTILITY_OPEN_API_INFO_TITLE)
                .description(UTILITY_OPEN_API_INFO_DESCRIPTION)
                .version(UTILITY_OPEN_API_INFO_VERSION)
                .termsOfService(UTILITY_OPEN_API_INFO_TERMS_OF_SERVICE)
                .contact(buildContact())
                .license(buildLicense());
    }

    private Contact buildContact() {
        return new Contact()
                .name(UTILITY_OPEN_API_INFO_CONTACT_NAME)
                .url(UTILITY_OPEN_API_INFO_CONTACT_URL)
                .email(UTILITY_OPEN_API_INFO_CONTACT_EMAIL);
    }

    private License buildLicense() {
        return new License()
                .name(UTILITY_OPEN_API_INFO_LICENCE_NAME)
                .url(UTILITY_OPEN_API_INFO_LICENCE_URL);
    }

    private Paths sanitizePaths(OpenAPI openAPI) {
        Paths originalPaths = openAPI.getPaths();
        Paths updatedPaths = new Paths();

        originalPaths.forEach((originalPath, pathItem) -> {
            if (originalPath.startsWith(UTILITY_OPEN_API_PATHS_TO_MATCH_ORIGINAL)) {
                String newPath = originalPath.replaceFirst(UTILITY_OPEN_API_PATHS_TO_MATCH_ORIGINAL, "");
                updatedPaths.addPathItem(newPath, pathItem);
            } else {
                updatedPaths.addPathItem(originalPath, pathItem);
            }
        });

        return updatedPaths;
    }

}
