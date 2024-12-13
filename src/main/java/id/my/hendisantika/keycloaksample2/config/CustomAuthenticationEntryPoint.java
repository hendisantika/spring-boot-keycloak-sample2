package id.my.hendisantika.keycloaksample2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.keycloaksample2.exception.dto.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.01
 * To change this template use File | Settings | File Templates.
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public static final Timestamp TIMESTAMP = new Timestamp(System.currentTimeMillis());

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ErrorResponse errorResponse =
                new ErrorResponse(
                        String.valueOf(HttpStatus.UNAUTHORIZED.value()), authException.getMessage(), TIMESTAMP);
        String json = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(json);
    }
}
