package com.songshilong.framework.starter.database.handler;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.songshilong.framework.starter.distributedid.toolkit.SnowflakeIdUtil;

/**
*@BelongsProject: 12306-ssl
*@BelongsPackage: com.songshilong.framework.starter.database.handler
*@Author: Shilong Song
*@CreateTime: 2024-12-20  10:12
*@Description: CustomIdGenerator
*@Version: 1.0
*/
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return SnowflakeIdUtil.nextId();
    }
}
