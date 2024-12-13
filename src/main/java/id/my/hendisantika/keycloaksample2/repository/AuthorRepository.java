package id.my.hendisantika.keycloaksample2.repository;

import id.my.hendisantika.keycloaksample2.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/12/24
 * Time: 10.42
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
