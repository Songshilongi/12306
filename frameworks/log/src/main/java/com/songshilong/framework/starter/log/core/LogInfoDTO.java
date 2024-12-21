package com.songshilong.framework.starter.log.core;

import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.log.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  14:16
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class LogInfoDTO {
    /**
     * 调用时间
     */
    private String startTime;

    /**
     * 入参
     */
    private Object[] inputParams;

    /**
     * 出参
     */
    private Object outputParams;




}
