package com.songshilong.service.user.constant;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.constant
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  22:44
 * @Description: RedisConstant -- Redis使用的常量
 * @Version: 1.0
 */
public final class RedisConstant {

    /**
     * 存放用户名 ---------- 使用set进行存储（本身自带去重）且在大数据量的时候是基于hash实现，索引和删除的复杂度都是O1
     */
    public static final String REGISTER_USERNAME = "service:user:register:username:";

    /**
     * 存放证件被注册的次数
     */
    public static final String ID_TYPE_CARD_REGISTER_COUNT = "service:user:register:type_card_count:";

    /**
     * 用户注册锁
     */
    public static final String LOCK_REGISTER_USERNAME = "service:user:register:lock:";


}
