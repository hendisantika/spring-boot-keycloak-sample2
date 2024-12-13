package id.my.hendisantika.keycloaksample2.config;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-keycloak-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/12/24
 * Time: 06.05
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@RequiredArgsConstructor
public class RateLimitConfig {

    private final ProxyManager buckets;

    public Bucket resolveBucket(String key) {
        Supplier<BucketConfiguration> configSupplier = getConfigSupplierForUser();

        // Does not always create a new bucket, but instead returns the existing one if it exists.
        return buckets.builder().build(key, configSupplier);
    }

    private Supplier<BucketConfiguration> getConfigSupplierForUser() {
        Refill refill = Refill.intervally(20, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(20, refill);

        return () -> (BucketConfiguration.builder().addLimit(limit).build());
    }
}
