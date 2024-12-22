package com.songshilong.service.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.gateway.test
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  15:05
 * @Description: NacosTest
 * @Version: 1.0
 */
@SpringBootTest
public class NacosTest {

    @Value("${user.name}")
    private String name;

    @Test
    public void test(){
        System.out.println(name);
    }



}
