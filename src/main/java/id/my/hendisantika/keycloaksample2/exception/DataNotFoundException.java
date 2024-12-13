package id.my.hendisantika.keycloaksample2.exception;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/24
 * Time: 10.46
 * To change this template use File | Settings | File Templates.
 */

import id.my.hendisantika.keycloaksample2.exception.base.ServiceException;

/**
 * trigger for data not found exception
 */
public class DataNotFoundException extends ServiceException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
