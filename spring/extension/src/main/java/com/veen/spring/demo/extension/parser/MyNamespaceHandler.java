package com.veen.spring.demo.extension.parser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by facheng on 18-5-4.
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
    }
}
