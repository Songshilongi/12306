package com.songshilong.service.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.framework.starter.database.base.BaseDO;
import lombok.*;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dao.entity
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  16:25
 * @Description: UserDeletionDO
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_deletion")
public class UserDeletionDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号
     */
    private String idCard;
}
