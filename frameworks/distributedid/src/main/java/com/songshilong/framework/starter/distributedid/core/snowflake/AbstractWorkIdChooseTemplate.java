package com.songshilong.framework.starter.distributedid.core.snowflake;

import com.songshilong.framework.starter.distributedid.toolkit.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core.snowflake
 * @Author: Shilong Song
 * @CreateTime: 2024-12-19  23:05
 * @Description: wordID生成模版
 * @Version: 1.0
 */
@Slf4j
public abstract class AbstractWorkIdChooseTemplate {


    @Value("${framework.distributed.id.snowflake.use-system-clock:false}")
    private Boolean useSystemClock;

    /**
     * 选择选择 WorkId 并初始化雪花
     */
    public void chooseAndInit() {
        WorkIdWrapper workIdWrapper = this.chooseWorkId();
        Long workId = workIdWrapper.getWorkId();
        Long dataCenterId = workIdWrapper.getDataCenterId();
        Snowflake snowflake = new Snowflake(workId, dataCenterId, useSystemClock);
        log.info("Snowflake type: {}, workId: {}, dataCenterId: {}", this.getClass().getSimpleName(), workId, dataCenterId);
        SnowflakeIdUtil.initSnowflake(snowflake);
    }

    /**
     * 子类实现如何去选择如何初始化 workId 和 dataCenterId
     * @return {@link WorkIdWrapper}
     */
    protected abstract WorkIdWrapper chooseWorkId();




}
