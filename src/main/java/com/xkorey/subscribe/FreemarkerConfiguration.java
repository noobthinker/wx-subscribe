package com.xkorey.subscribe;

import com.xkorey.subscribe.service.IService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreemarkerConfiguration implements BeanPostProcessor {

    @Autowired
    IService service;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof FreeMarkerConfigurer) {
            FreeMarkerConfigurer configurer = (FreeMarkerConfigurer) bean;
            configurer.setFreemarkerVariables(service.commonProp());
        }
        return bean;
    }


}
