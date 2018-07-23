package spring.demo.util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;


public class RedisUtil {
    private final static SecureRandom random = new SecureRandom();

    private RedisTemplate<String, Object> redisTemplate;

    public Map<Object, Object> getHashValue(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public <V> void setHashValue(String key, String hashKey, V value, Date date) {
        if (date == null) {
            date = LocalDate.now().plusDays(1).toDate();
        }
        Date expire = date;
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <String, V> Object execute(RedisOperations<String, V> operations) throws DataAccessException {
                operations.opsForHash().put((String) key, hashKey, value.toString());
                operations.expireAt((String) key, expire);
                return null;
            }
        });
    }

    public void setHashValue(String key, Map<Object, Object> values, Date date) {
        if (date == null) {
            date = LocalDate.now().plusDays(1).toDate();
        }
        Date expire = date;
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <String, V> Object execute(RedisOperations<String, V> operations) throws DataAccessException {
                for (Object hashKey : values.keySet()) {
                    operations.opsForHash().put((String) key, hashKey, values.get(hashKey).toString());
                    operations.expireAt((String) key, expire);
                }
                return null;
            }
        });
    }

    public String acquireLockWithTimeout(String lockName, LocalDateTime timeout, long expireTime, TimeUnit unit)
            throws InterruptedException {
        if (StringUtils.isBlank(lockName) || timeout == null || expireTime <= 0 || unit == null) {
            return null;
        }
        String randomId = newRandomId();
        while (new LocalDateTime().isBefore(timeout)){
            if (redisTemplate.opsForValue().setIfAbsent(lockName, randomId)) {
                redisTemplate.expire(lockName, expireTime, unit);
                return randomId;
            } else if (redisTemplate.getExpire(lockName) == -1) {
                redisTemplate.expire(lockName, expireTime, unit);
            }
            
           Thread.sleep(500);
        }
        return null;
    }

    public void releaesLock(String lockName, String id) {
        if (StringUtils.isBlank(lockName) || StringUtils.isBlank(id)) {
            return;
        }


        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <String, V> Object execute(RedisOperations<String, V> operations) throws DataAccessException {
                operations.watch((String) lockName);
                if (id.equals(operations.opsForValue().get((String) lockName))) {
                    operations.multi();
                    operations.delete((String) lockName);
                    List result = operations.exec();
                }
                operations.unwatch();
                return null;
        }
        });
    }

    public void setStringValue(String key, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    public Object getStringValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }



    private String newRandomId() {
        byte[] randomBytes = new byte[15];
        random.nextBytes(randomBytes);
        return new Base32().encodeAsString(randomBytes);
    }

}
