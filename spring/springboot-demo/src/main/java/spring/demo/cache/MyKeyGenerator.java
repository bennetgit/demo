package spring.demo.cache;

/**
 * Created by feng on 17/11/18.
 */
public interface MyKeyGenerator<T extends Cached> {

    Object generate(T cache);
}
