package com.songshilong.service.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.framework.starter.database.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.dao.entity
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  16:27
 * @Description: UserDO
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 国家/地区
     */
    private String region;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 旅客类型
     */
    private Integer userType;

    /**
     * 审核状态
     */
    private Integer verifyStatus;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
