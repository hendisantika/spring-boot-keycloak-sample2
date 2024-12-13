package id.my.hendisantika.keycloaksample2.service;

import id.my.hendisantika.keycloaksample2.exception.BadRequestException;
import id.my.hendisantika.keycloaksample2.exception.DataNotFoundException;
import id.my.hendisantika.keycloaksample2.model.entity.Tag;
import id.my.hendisantika.keycloaksample2.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

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

    public Tag getById(Long id) {
        return tagRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new DataNotFoundException(
                                        MessageFormat.format("Tag id {0} not found", String.valueOf(id))));
    }

    public Tag createOrUpdate(Tag tagRequest) {
        Optional<Tag> existingTag = tagRepository.findById(tagRequest.getId());

        if (existingTag.isPresent()) {
            Tag tagUpdate = existingTag.get();

            tagUpdate.setName(tagRequest.getName());

            return tagRepository.save(tagUpdate);
        } else {
            return tagRepository.save(tagRequest);
        }
    }

    public void deleteById(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            tagRepository.deleteById(id);
        } else {
            throw new BadRequestException("Delete error, please check ID and try again");
        }
    }
}
