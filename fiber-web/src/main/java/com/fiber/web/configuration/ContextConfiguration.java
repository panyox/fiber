package com.fiber.web.configuration;

import com.fiber.filter.api.utils.SpringBeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author panyox
 */
public class ContextConfiguration {

    @Bean
    public ApplicationContextAware applicationContextAware() {
        return new FiberApplicationContextAware();
    }

    private static class FiberApplicationContextAware implements ApplicationContextAware {

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            SpringBeanUtils.getInstance().setCfgContext((ConfigurableApplicationContext) applicationContext);
        }
    }
}
