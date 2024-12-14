package com.songshilong.framework.starter.base.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.base.init
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  15:17
 * @Description: Spring应用初始化后置处理器，防止Spring事件被多次执行。在Spring上下文初始化之后触发。ApplicationListener可以监听某个特定的事件
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class ApplicationContentPostProcessor implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationContext applicationContext;

    private boolean executeOnlyOne = true;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        synchronized (ApplicationContentPostProcessor.class) {
            if (executeOnlyOne) {
                applicationContext.publishEvent(new ApplicationInitializingEvent(this));
                executeOnlyOne = false;
            }
        }
    }
}
