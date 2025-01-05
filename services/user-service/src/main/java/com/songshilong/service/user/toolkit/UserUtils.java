package com.songshilong.service.user.toolkit;

import com.songshilong.service.user.constant.UserRegisterShardingCount;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.toolkit
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  21:52
 * @Description: UserUtils
 * @Version: 1.0
 */
public final  class UserUtils {

    /**
     * 计算分片位置
     */
    public static int hashShardingIdx(String username) {
        return Math.abs(username.hashCode() % UserRegisterShardingCount.USER_REGISTER_REUSE_SHARDING_COUNT);
    }






}
