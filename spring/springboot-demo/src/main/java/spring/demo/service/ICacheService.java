package spring.demo.service;

/**
 * Created by facheng on 17-11-17.
 */
public interface ICacheService<K, V> {
    V get(K key);
}
