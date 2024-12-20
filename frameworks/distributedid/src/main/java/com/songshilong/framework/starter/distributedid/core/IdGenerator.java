package com.songshilong.framework.starter.distributedid.core;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core
 * @Author: Shilong Song
 * @CreateTime: 2024-12-19  17:42
 * @Description: ID 生成接口
 * @Version: 1.0
 */
public interface IdGenerator {

    /**
     * 下一个Id
     *
     * @return nextID 默认0
     */
    default Long nextId() {
        return 0L;
    }

    /**
     * 下一个ID Str形式
     * @return nextId (String) 默认 "0"
     */
    default String nextIdStr() {
        return String.valueOf(0);
    }


}
