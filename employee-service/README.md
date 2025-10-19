## 📌 Steps to implement Redis Cache:

1) Add @EnableCaching in the Configuration class or in Springboot Main class for to enable chaching
2) Use @Cacheable, @CachePut and @CacheEvict annotations in the Service class (you can use these annotations in any layer)
3) Test using postman when you hit api firt time it will query to the database and from next time you will not see query in intellij console because it will return data from cache.
4) Till now we used Spring boot Cache. (Spring boot provides a Cache Abstraction API that allow us to use different cache providers to cache objects.)
5) Now to use Redis cache first setup Redis using docker as steps given below or directly install redis in windows (you can follow any youtube video to install redis in windows)
6) Add spring-boot-starter-data-redis dependency in pom.xml
7) Now you need to implement Serializable interface in DTO class which is returned by our Rest API. (After Redis configuration, we can skip this step)
8) Add Redis properties in application.properties file
9) In configuration class we will do Redis configuration like prefixCacheName, cache expiry time (entryTtl), SerializeKey, SerializeValue, etc.
   After this Serialization configuration, we can skip the 7th step, i.e. remove Serializable implementation from DTO class.

---
## 📚 References

- [Youtube video to follow 1](https://youtu.be/uVP0nXEGqRg?si=fqWcazcELeWITmoU)
- [Youtube video to follow 2](https://youtu.be/eLo__fbGKUc?si=VwXB9HTHJcafZsRc)
- [Spring Boot Caching Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-caching)
- [Spring Data Redis Documentation](https://docs.spring.io/spring-data/redis/docs/current/reference/html/)

---

## 📌 Cache Annotations Overview

### 1️⃣ `@Cacheable` – Read from cache, store if absent
**✅ Purpose:**
- Retrieves data from the cache if it exists
- If not, executes the method, stores the result in cache, and returns it

---

### 2️⃣ `@CachePut` – Always update cache
**✅ Purpose:**
- Method always executes
- Cache is updated with the latest result, even if it already exists
- Useful for update operations (`save`, `update`)

---

### 3️⃣ `@CacheEvict` – Remove entry from cache
**✅ Purpose:**
- Removes one or more entries from the cache
- Ensures cache does not return stale data

---

## 🔹 Flow Example in a CRUD App

| Operation       | Annotation       | What happens in cache                          |
|-----------------|-----------------|-----------------------------------------------|
| Get employee    | `@Cacheable`    | If cache exists → return it, else fetch from DB and cache |
| Update employee | `@CachePut`     | Updates DB → refresh cache with new value     |
| Delete employee | `@CacheEvict`   | Deletes DB → removes cache entry              |

---

## ⚙️ How It Works

1. **Get operation**: Checks cache first. If missing, fetches from DB and stores in cache.  
2. **Update operation**: Always updates DB and refreshes the cached value.  
3. **Delete operation**: Removes the corresponding cache entry to prevent stale data.

---

## 🔧 Redis Configuration (Example)

```
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.cache.type=redis
```



## Redis Setup using Docker:

# Pull redis image - 

```
docker pull redis
```

# Run Redis container -

```
docker run -d --name redis -p 6379:6379 redis
```

# Connect to Redis CLI -

```
docker exec -it redis redis-cli
```
here you can run some commands..

To ckeck all keys:
```
 keys *
```

The value is stored in Redis with the key: (for example, key is 2 here)
```
 get my-redis-employees::2
```
