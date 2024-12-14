package com.songshilong.framework.starter.base.init;

import org.springframework.context.ApplicationEvent;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.base.init
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  15:12
 * @Description: 自定义事件类型，在Spring上下文初始化的初期发布。通过自定义事件查看业务系统的所有初始化行为
 * @Version: 1.0
 */
public class ApplicationInitializingEvent extends ApplicationEvent {

    /**
     * 构造器
     * @param source 时间来源
     */
    public ApplicationInitializingEvent(Object source) {
        super(source);
    }


}
