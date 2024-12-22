package com.songshilong.service.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.framework.starter.database.base.BaseDO;
import lombok.*;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dao.entity
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  16:28
 * @Description: UserMailDO
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("t_user_mail")
public class UserMailDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
