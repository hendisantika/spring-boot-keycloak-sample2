package id.my.hendisantika.keycloaksample2.service;

import id.my.hendisantika.keycloaksample2.model.entity.Tag;
import id.my.hendisantika.keycloaksample2.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/24
 * Time: 11.02
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        List<Tag> tagList = tagRepository.findAll();
        return tagList;
    }
}
