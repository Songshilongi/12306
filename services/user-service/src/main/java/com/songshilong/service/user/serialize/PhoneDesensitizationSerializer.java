package com.songshilong.service.user.serialize;


import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user.serialize
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  22:39
 * @Description: PhoneDesensitizationSerializer <br>
 * Jackson 自定义序列化器
 * @Version: 1.0
 */
public class PhoneDesensitizationSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String phoneDesensitization = DesensitizedUtil.mobilePhone(s);
        jsonGenerator.writeString(phoneDesensitization);
    }
}
