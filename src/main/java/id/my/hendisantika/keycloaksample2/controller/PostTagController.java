package id.my.hendisantika.keycloaksample2.controller;

import id.my.hendisantika.keycloaksample2.model.entity.Tag;
import id.my.hendisantika.keycloaksample2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.15
 * To change this template use File | Settings | File Templates.
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PostTagController {

    private final PostService service;

    @GetMapping("/v1/posts/{id}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByPostId(@PathVariable(value = "id") Long id) {
        List<Tag> tagList = service.getAllTagsByPostId(id);
        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    @PostMapping("/v1/posts/{id}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable("id") Long id, @RequestBody Tag tagRequest) {
        Tag updated = service.addTag(id, tagRequest);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}