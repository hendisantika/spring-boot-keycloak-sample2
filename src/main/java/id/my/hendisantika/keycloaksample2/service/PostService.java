package id.my.hendisantika.keycloaksample2.service;

import id.my.hendisantika.keycloaksample2.exception.DataNotFoundException;
import id.my.hendisantika.keycloaksample2.model.dto.PostDTO;
import id.my.hendisantika.keycloaksample2.model.entity.Author;
import id.my.hendisantika.keycloaksample2.model.entity.Post;
import id.my.hendisantika.keycloaksample2.repository.AuthorRepository;
import id.my.hendisantika.keycloaksample2.repository.PostRepository;
import id.my.hendisantika.keycloaksample2.repository.TagRepository;
import id.my.hendisantika.keycloaksample2.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
 * Time: 11.00
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final ModelMapper modelMapper;

    private final PostRepository postRepository;

    private final AuthorRepository authorRepository;

    private final TagRepository tagRepository;

    @Cacheable(value = "posts")
    public List<Post> getAllPosts(String title) {
        log.info("Getting posts.");

        Page<Post> postListWithPagination =
                postRepository.findAllPostsWithPagination(PageUtils.pageable(1, 10, "title", "ASC"));

        List<Post> postList;
        if (title == null) {
            postList = postRepository.findAll();
        } else {
            postList = postRepository.findByTitleContaining(title);
        }
        return postList;
    }

    @Cacheable(value = "posts", key = "#id")
    public Post getById(Long id) {
        log.info("Getting post with ID {}.", id);

        return postRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new DataNotFoundException(
                                        MessageFormat.format("Post id {0} not found", String.valueOf(id))));
    }

    @CacheEvict(value = "posts", allEntries = true)
    public Post createOrUpdate(PostDTO postRequest) {
        log.info("Create or update post with id {}", postRequest.getId());

        Optional<Post> existingPost = postRepository.findById(postRequest.getId());

        if (existingPost.isPresent()) {
            Post postUpdate = existingPost.get();

            postUpdate.setTitle(postRequest.getTitle());
            postUpdate.setBody(postRequest.getBody());
            if (postRequest.getAuthorId() != 0) {
                Optional<Author> author = authorRepository.findById(postRequest.getAuthorId());
                author.ifPresent(postUpdate::setAuthor);
            }

            return postRepository.save(postUpdate);
        } else {
            return postRepository.save(modelMapper.map(postRequest, Post.class));
        }
    }
}
