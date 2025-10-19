package com.javaguides.employee_service.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("my-redis-") //Adds a prefix to all cache names stored in Redis.
                .entryTtl(Duration.ofSeconds(60)) //TTL = Time To Live (cache expiry time). Each cache entry will expire automatically after 60 seconds.
                .enableTimeToIdle() //This enables “Time to Idle” expiration in addition to TTL. If an entry isn’t accessed within its TTL window, it’s automatically expired even before the full TTL elapses.
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) //Defines how keys are serialized before storing in Redis. Keys will be stored as readable strings (e.g., "my-redis-patients::123") instead of raw binary bytes.
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); //Defines how values are serialized before storing in Redis. Converts Java objects into JSON before saving to Redis, and deserializes them back when fetched. Easier to debug and inspect data in Redis CLI.

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
}
