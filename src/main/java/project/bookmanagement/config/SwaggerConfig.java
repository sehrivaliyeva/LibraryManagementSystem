package project.bookmanagement.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "BookManagement REST API",
                version = "1.0",
                description = "Comprehensive documentation for the BookWave REST API," +
                        " including endpoint descriptions and usage instructions."
        ),
        security = {@SecurityRequirement(name = "bearerToken")},
        externalDocs = @ExternalDocumentation(
                description = "Access the full API documentation",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class SwaggerConfig {
}
