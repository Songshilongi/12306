package com.songshilong.framework.starter.user.core;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.user.core
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  20:16
 * @Description: UserContext
 * @Version: 1.0
 */
public class UserContext {

    /**
     * 为什么用<code>TransmittableThreadLocal</code>而不是<code>ThreadLocal</code>?
     * <p>在传统的 ThreadLocal 中，每个线程都有一个独立的副本，当我们启动一个新线程时，原始线程中的 ThreadLocal 值不会自动传递给新线程。
     * 这意味着如果我们需要在不同的线程间共享特定的上下文信息，必须手动传递这些信息，这可能会导致代码变得复杂且容易出错。
     * TransmittableThreadLocal（简称 TTL）是一种特殊的 ThreadLocal 实现，它
     * 能够在创建新线程时自动复制当前线程中的 ThreadLocal 值到新线程中。
     * 这样，即使在多线程环境中，也可以轻松地维护和传递上下文信息。</p>
     */
    private static final ThreadLocal<UserInfoDTO> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 从上下文获取userId
     * @return userId
     */
    public static String getUserId() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getUserId).orElse(null);

    }
    /**
     * 从上下文获取 username
     * @return username
     */
    public static String getUsername() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getUsername).orElse(null);

    }
    /**
     * 从上下文获取 realName
     * @return realName
     */
    public static String getRealName() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getRealName).orElse(null);

    }
    /**
     * 从上下文获取 token
     * @return token
     */
    public static String getToken() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getToken).orElse(null);

    }

    public static void setUserInfo(UserInfoDTO userInfoDTO) {
        USER_THREAD_LOCAL.set(userInfoDTO);
    }

    /**
     * 清理用户上下文
     */
    public static void removeUserInfo() {
        USER_THREAD_LOCAL.remove();
    }

}
