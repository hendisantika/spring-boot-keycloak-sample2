package id.my.hendisantika.keycloaksample2;

import org.redisson.jcache.JCachingProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.util.Iterator;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringBootKeycloakSample2Application {

    public static void main(String[] args) {
        // list all the caching provider
        Iterator<CachingProvider> iterator =
                Caching.getCachingProviders(Caching.getDefaultClassLoader()).iterator();
        while (iterator.hasNext()) {
            CachingProvider provider = iterator.next();
            if (!(provider instanceof JCachingProvider)) {
                iterator.remove();
            }
        }
        SpringApplication.run(SpringBootKeycloakSample2Application.class, args);
    }

}
