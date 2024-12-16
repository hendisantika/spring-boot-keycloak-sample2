package id.my.hendisantika.keycloaksample2.config;

import com.giffing.bucket4j.spring.boot.starter.config.cache.SyncCacheResolver;
import com.giffing.bucket4j.spring.boot.starter.config.cache.jcache.JCacheCacheResolver;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.cache.CacheManager;
import javax.cache.Caching;

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
                .setAddress("redis://" + redisHost + ":" + redisPort);
//                .setPassword(redisPassword);
        return config;
    }

    @Bean
    public CacheManager cacheManagerRateLimiting(Config config) {
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        manager.createCache("cache", RedissonConfiguration.fromConfig(config));
        return manager;
    }

    @Bean
    ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache("cache"));
    }

    /**
     * reference: https://github.com/MarcGiffing/bucket4j-spring-boot-starter/issues/73
     */
    @Bean
    @Primary
    public SyncCacheResolver bucket4jCacheResolver(CacheManager cacheManager) {
        return new JCacheCacheResolver(cacheManager);
    }
}
