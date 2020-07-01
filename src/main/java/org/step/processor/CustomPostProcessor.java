package org.step.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.step.repository.Example;

@Component
public class CustomPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAssignableFrom(Example.class)) {
            System.out.println(beanName + " " + bean.getClass().getSimpleName() + " before initialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAssignableFrom(Example.class)) {
            System.out.println(beanName + " " + bean.getClass().getSimpleName() + " after initialization");
        }
        return bean;
    }
}
