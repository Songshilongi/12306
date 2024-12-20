package com.songshilong.framework.starter.database.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.database.base
 * @Author: Shilong Song
 * @CreateTime: 2024-12-20  10:03
 * @Description: BaseDO
 * @Version: 1.0
 */
@Data
public class BaseDO {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 删除标志
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer delFlag;


}
