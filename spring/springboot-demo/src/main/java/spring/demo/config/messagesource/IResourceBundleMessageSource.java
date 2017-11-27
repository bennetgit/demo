package spring.demo.config.messagesource;

import java.util.List;

import spring.demo.dto.ResourceBundleDto;

/**
 * Created by facheng on 17-11-23.
 */
public interface IResourceBundleMessageSource {

    void refresh();

    void update(ResourceBundleDto resourceBundle);

    List<ResourceBundleDto> loadBundles();

}
