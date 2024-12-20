package com.songshilong.framework.starter.distributedid.core.serviceid;

import com.songshilong.framework.starter.distributedid.core.IdGenerator;
import com.songshilong.framework.starter.distributedid.core.snowflake.SnowflakeIdInfo;

import java.util.zip.Deflater;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core.serviceid
 * @Author: Shilong Song
 * @CreateTime: 2024-12-20  09:12
 * @Description: ServiceIdGenerator
 * @Version: 1.0
 */
public interface ServiceIdGenerator extends IdGenerator {

    /**
     * 根据serviceId生成下一个id
     * @param serviceId 上一个ID
     * @return nextId
     */
    default Long nextId(Long serviceId) {
        return 0L;
    }

    /**
     * 根据serviceId(String)生成下一个id
     * @param serviceId 上一个ID
     * @return nextId
     */
    default Long nextId(String serviceId) {
        return 0L;
    }
    /**
     * 根据serviceId生成下一个id(String)
     * @param serviceId 上一个ID
     * @return nextId
     */
    default String nextIdStr(Long serviceId) {
        return null;
    }
    /**
     * 根据serviceId(String)生成下一个id(String)
     * @param serviceId 上一个ID
     * @return nextId
     */
    default String nextIdStr(String serviceId) {
        return null;
    }

    /**
     * 解析雪花算法
     */
    SnowflakeIdInfo parseSnowflakeId(long snowflakeId);




}
