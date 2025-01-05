package com.songshilong.service.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.enums
 * @Author: Shilong Song
 * @CreateTime: 2024-12-23  21:41
 * @Description: VerifyStatusEnum
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum VerifyStatusEnum {

    /**
     * 未审核
     */
    UNREVIEWED(0),

    /**
     * 已审核
     */
    REVIEWED(1);

    private final int code;
}
