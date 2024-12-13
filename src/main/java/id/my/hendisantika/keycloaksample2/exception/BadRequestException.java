package id.my.hendisantika.keycloaksample2.exception;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/24
 * Time: 10.44
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.service.spi.ServiceException;

/**
 * trigger for bad request exception
 */
public class BadRequestException extends ServiceException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
