package id.my.hendisantika.keycloaksample2.controller;

import id.my.hendisantika.keycloaksample2.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.17
 * To change this template use File | Settings | File Templates.
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TagController {

    private final TagService service;
}
