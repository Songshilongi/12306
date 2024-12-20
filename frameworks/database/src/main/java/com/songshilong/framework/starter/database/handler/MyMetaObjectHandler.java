package com.songshilong.framework.starter.database.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.songshilong.framework.starter.common.enums.DelEnum;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.database.handler
 * @Author: Shilong Song
 * @CreateTime: 2024-12-20  10:06
 * @Description: 定义字段填充的规则
 * @Version: 1.0
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "delFlag", Integer.class, DelEnum.NORMAL.code());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }
}
