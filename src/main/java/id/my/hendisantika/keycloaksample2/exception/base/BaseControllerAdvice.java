package id.my.hendisantika.keycloaksample2.exception.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/24
 * Time: 10.51
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestControllerAdvice
public class BaseControllerAdvice {

    public static final Timestamp TIMESTAMP = new Timestamp(System.currentTimeMillis());
}
