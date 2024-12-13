package id.my.hendisantika.keycloaksample2.exception;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/24
 * Time: 10.47
 * To change this template use File | Settings | File Templates.
 */

import id.my.hendisantika.keycloaksample2.exception.base.ServiceException;

/**
 * trigger for unauthorized exception
 */
public class UnauthorizedException extends ServiceException {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
