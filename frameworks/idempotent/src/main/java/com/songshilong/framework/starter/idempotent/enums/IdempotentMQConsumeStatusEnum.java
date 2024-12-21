package com.songshilong.framework.starter.idempotent.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.idempotent.enums
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:26
 * @Description: 幂等 MQ 消费状态枚举
 * @Version: 1.0
 */
@RequiredArgsConstructor
public enum IdempotentMQConsumeStatusEnum {

    /**
     * 消费中
     */
    CONSUMING("0"),

    /**
     * 已消费
     */
    CONSUMED("1");

    @Getter
    private final String code;

    /**
     * 如果消费状态等于消费中，返回失败<br>
     * 如果 error 为 true，代表需要抛异常让 RocketMQ 重试。<br>
     * 如果 error 为 false，代表消息已经消费过了，不执行业务逻辑，将异常吞掉返回 RocketMQ 消费成功即可。<br>
     * @param consumeStatus 消费状态
     * @return 是否消费失败
     */
    public static boolean isError(String consumeStatus) {
        return Objects.equals(CONSUMING.code, consumeStatus);
    }
}
