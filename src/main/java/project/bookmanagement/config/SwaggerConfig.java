package project.bookmanagement.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "LIBRARY MANAGEMENT REST API"

        ),
        security = {@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerToken")},
        externalDocs = @ExternalDocumentation(
                description = "Access the full API documentation",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
@SecuritySchemes({
        @io.swagger.v3.oas.annotations.security.SecurityScheme(
                name = "bearerToken",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT",
                description = "JWT-based authentication scheme for securing API endpoints." +
                        " Pass the token in the Authorization header as 'Bearer <token>'"
        )
})
public class SwaggerConfig {


}
