package id.my.hendisantika.keycloaksample2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.11
 * To change this template use File | Settings | File Templates.
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/**")
                .authorizeHttpRequests(
                        rmr ->
                                rmr.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/actuator/**")
                                        .permitAll());

        // h2-console
        http.securityMatcher(toH2Console())
                .authorizeHttpRequests(rmr -> rmr.requestMatchers(toH2Console()).permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(
                        httpSecurityHeadersConfigurer ->
                                httpSecurityHeadersConfigurer.frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::disable));

        http.securityMatcher("/v1/**")
                .authorizeHttpRequests(
                        rmr ->
                                rmr.requestMatchers("/v1/authors/**", "/v1/tags/**")
                                        .hasRole(ROLE_ADMIN)
                                        .requestMatchers("/v1/authors/**", "/v1/tags/**")
                                        .hasAnyRole(ROLE_ADMIN, ROLE_USER)
                                        .requestMatchers("/v1/**")
                                        .authenticated());

        http.oauth2ResourceServer(
                oauth2ResourceServer ->
                        oauth2ResourceServer
                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                                .accessDeniedHandler(new CustomAccessDeniedHandler())
                                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(STATELESS));

        return http.build();
    }
}
