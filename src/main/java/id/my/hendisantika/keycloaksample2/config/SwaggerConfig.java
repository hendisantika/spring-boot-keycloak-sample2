package id.my.hendisantika.keycloaksample2.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.09
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    private static final String REDIRECT_URL = "/swagger-ui.html";
    private static final String OAUTH_SCHEME_NAME = "yellow-ranger";

    @Value("${keycloak.auth-server-url}")
    String authServerUrl;

    @Value("${keycloak.realm}")
    String realm;

    @Value("${spring.mvc.servlet.path}")
    private String baseUrl;

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", baseUrl.concat(REDIRECT_URL));
        registry.addRedirectViewController("/swagger-ui", baseUrl.concat(REDIRECT_URL));
        registry.addRedirectViewController("/api", baseUrl.concat(REDIRECT_URL));
    }

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Spring Boot 3 Keycloak")
                                .description("Spring Boot 3 Keycloak")
                                .version("0.0.1")
                                .contact(new Contact().name("hendisantika").email("hendisantika@yahoo.co.id")))
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Documentation")
                                .url("https://s.id/hendisantika"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        OAUTH_SCHEME_NAME,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.OAUTH2)
                                                .in(SecurityScheme.In.HEADER)
                                                .flows(new OAuthFlows().password(new OAuthFlow().tokenUrl(authServerUrl)))))
                .addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME_NAME));
    }
}
