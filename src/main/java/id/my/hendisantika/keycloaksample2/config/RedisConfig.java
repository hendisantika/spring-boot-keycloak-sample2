package id.my.hendisantika.keycloaksample2.config;

import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.07
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    String redisHost;

    @Value("${spring.data.redis.port}")
    String redisPort;

    @Value("${spring.data.redis.password}")
    String redisPassword;

    @Bean
    public Config config() {
        Config config = new Config();
        config
                .useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort)
                .setPassword(redisPassword);
        return config;
    }
}
