package spring.demo.service;

/**
 * Created by facheng on 17-11-17.
 */
public interface ICacheService<K, V> {
    V get(K key);

    V add(K key, V value);

    void delete(K key);

    V update(K key, V value);
}
