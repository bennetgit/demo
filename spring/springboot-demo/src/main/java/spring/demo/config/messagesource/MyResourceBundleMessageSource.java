package spring.demo.config.messagesource;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.transaction.annotation.Transactional;

import spring.demo.dto.ResourceBundleDto;
import spring.demo.persistence.primary.jpa.IResourceBundleRepository;

/**
 * Created by facheng on 17-11-23.
 */

public class MyResourceBundleMessageSource extends AbstractMessageSource implements IResourceBundleMessageSource {

    // handler zh and en
    private static final int cacheSize = 2;

    @Resource
    private IResourceBundleRepository resourceBundleRepository;

    private Map<String, Map<String, String>> cachedBundles;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {

        if (!cachedBundles.containsKey(locale.getLanguage())) {
            locale = Locale.CHINESE;
        }

        if (!cachedBundles.containsKey(locale.getLanguage())
                || !cachedBundles.get(locale.getLanguage()).containsKey(code)) {
            return new MessageFormat(code, locale);
        }

        return new MessageFormat(cachedBundles.get(locale.getLanguage()).get(code), locale);
    }

    public void init() {
        Map<String, Map<String, String>> resourceBundles = new HashMap<>(cacheSize);

        List<ResourceBundleDto> bundleList = loadBundles();

        for (ResourceBundleDto bundle : bundleList) {
            if (!resourceBundles.containsKey(bundle.getLocale().getLanguage())) {
                resourceBundles.put(bundle.getLocale().getLanguage(), new HashMap<>(bundleList.size()));
            }
            resourceBundles.get(bundle.getLocale().getLanguage()).put(bundle.getKey(), bundle.getValue());
        }

        cachedBundles = resourceBundles;
    }

    @Override
    @Transactional
    public void refresh() {
        init();
    }

    @Override
    @Transactional
    public void update(ResourceBundleDto resourceBundle) {
        resourceBundleRepository.findOne(resourceBundle.getId()).setValue(resourceBundle.getValue());
    }

    @Override
    @Transactional
    public List<ResourceBundleDto> loadBundles() {
        return resourceBundleRepository.findAll().stream().map(r -> ResourceBundleDto.of(r.getId(), r.getKey(),
                r.getValue(), r.getLocale(), r.getCreatedOn(), r.getUpdatedOn())).collect(Collectors.toList());
    }

}
