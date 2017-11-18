package spring.demo.dto.common;

import java.io.Serializable;

import spring.demo.cache.Cached;

/**
 * Created by feng on 17/11/18.
 */
public abstract class BaseDto<K> implements Serializable, Cached<K> {
}
