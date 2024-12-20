package com.songshilong.framework.starter.distributedid.handler;

import com.songshilong.framework.starter.distributedid.core.IdGenerator;
import com.songshilong.framework.starter.distributedid.core.serviceid.DefaultServiceIdGenerator;
import com.songshilong.framework.starter.distributedid.core.serviceid.ServiceIdGenerator;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.handler
 * @Author: Shilong Song
 * @CreateTime: 2024-12-20  09:27
 * @Description: 业务ID生成器管理容器
 * @Version: 1.0
 */
public final class IdGeneratorManager {
    /**
     * container
     */
    private static final Map<String, IdGenerator> MANAGER = new ConcurrentHashMap<>();
    private static final String DEFAULT = "default";
    static {
        MANAGER.put(DEFAULT, new DefaultServiceIdGenerator());
    }

    /**
     * 注册ID生成器
     * @param resource 资源标识
     * @param idGenerator 生成器
     */
    public static void registerGenerator(@NonNull String resource, @NonNull IdGenerator idGenerator) {
        IdGenerator generator = MANAGER.get(resource);
        if (null != generator) {
            return;
        }
        MANAGER.put(resource, idGenerator);
    }

    /**
     * 获取ID生成器
     * @param resource 资源标识
     * @return Id生成器
     */
    public static ServiceIdGenerator getIdGenerator(@NonNull String resource) {
        IdGenerator generator = MANAGER.get(resource);
        return generator == null ? null : (ServiceIdGenerator) generator;
    }

    /**
     * 获取默认的ID生成器
     * @return ID生成器
     */
    public static ServiceIdGenerator getDefaultServiceIdGenerator() {
        return getIdGenerator(DEFAULT);
    }



}
