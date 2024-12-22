package com.songshilong.service.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.framework.starter.database.base.BaseDO;
import lombok.*;

import java.util.Date;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dao
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  16:24
 * @Description: PassengerDO
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("t_passenger")
public class PassengerDO extends BaseDO {


    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号码
     */
    private String idCard;

    /**
     * 优惠类型
     */
    private Integer discountType;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 添加日期
     */
    private Date createDate;

    /**
     * 审核状态
     */
    private Integer verifyStatus;
}
