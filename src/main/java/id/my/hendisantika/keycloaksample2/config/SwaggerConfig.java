package id.my.hendisantika.keycloaksample2.config;

import org.springframework.beans.factory.annotation.Value;
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
    private static final String OAUTH_SCHEME_NAME = "client-sangkhim";

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
}
