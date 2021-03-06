package spring.jpa.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Gestion Formation", description = "Application de gestion pour un Center de formations", version = "${build.version}", contact = @Contact(name = "Mohamed Ayadi", email = "mamayadi@gmail.com")))
@SecurityScheme(name = "JwtAuthentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SpringFoxConfig {
}