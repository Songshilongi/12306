package com.songshilong.framework.starter.distributedid.core.snowflake;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core.snowflake
 * @Author: Shilong Song
 * @CreateTime: 2024-12-19  22:59
 * @Description: 雪花算法组成部分，通常用来反解析使用
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SnowflakeIdInfo {
    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 工作机器节点 ID
     */
    private Integer workerId;

    /**
     * 数据中心 ID
     */
    private Integer dataCenterId;

    /**
     * 自增序号，当高频模式下时，同一毫秒内生成 N 个 ID，则这个序号在同一毫秒下，自增以避免 ID 重复
     */
    private Integer sequence;

    /**
     * 通过基因法生成的序号，会和 {@link SnowflakeIdInfo#sequence} 共占 12 bit
     */
    private Integer gene;
}
