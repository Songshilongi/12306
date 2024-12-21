package com.songshilong.framework.starter.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.cache
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  15:45
 * @Description: Redis key序列化
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class RedisKeySerializer implements InitializingBean, RedisSerializer<String> {
    /**
     * key 前缀
     */
    private final String keyPrefix;
    /**
     * 字符集名字
     */
    private final String charsetName;
    /**
     * 字符集
     */
    private Charset charset;

    @Override
    public byte[] serialize(String s) throws SerializationException {
        return (keyPrefix + s).getBytes(charset);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return new String(bytes, charset);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        charset = Charset.forName(charsetName);
    }
}
