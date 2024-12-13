package id.my.hendisantika.keycloaksample2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.keycloaksample2.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.sql.Timestamp;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.00
 * To change this template use File | Settings | File Templates.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Timestamp TIMESTAMP = new Timestamp(System.currentTimeMillis());

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.access.AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse =
                new ErrorResponse(
                        String.valueOf(HttpStatus.FORBIDDEN.value()),
                        accessDeniedException.getMessage(),
                        TIMESTAMP);
        String json = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(json);
    }
}
