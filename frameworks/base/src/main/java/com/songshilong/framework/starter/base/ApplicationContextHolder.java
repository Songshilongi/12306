package com.songshilong.framework.starter.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.base
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  14:44
 * @Description: ApplicationContextHolder 让非SpringBean获取上下文中的SpringBean
 * @Version: 1.0
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    /**
     * Spring上下文
     */
    private static ApplicationContext CONTEXT;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.CONTEXT = applicationContext;
    }


    /**
     * 根据 类型 从Spring上下文获取Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }

    /**
     * 根据 名字 从Spring上下文获取Bean
     */
    public static Object getBean(String name) {
        return CONTEXT.getBean(name);
    }

    /**
     * 根据 名字和类型 从Spring上下文获取Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return CONTEXT.getBean(name, clazz);
    }

    /**
     * 根据 类型 从Spring上下文获取所有该类型的 Bean
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return CONTEXT.getBeansOfType(clazz);
    }

    /**
     * 寻找某个Spring bean上是否存在特定的注解
     */
    public static <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return CONTEXT.findAnnotationOnBean(beanName, annotationType);
    }
    /**
     * 获得完整的 Spring 上下文
     */
    public ApplicationContext getInstance() {
        return CONTEXT;
    }



}
