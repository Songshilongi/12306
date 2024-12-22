package com.songshilong.service.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.user
 * @Author: Shilong Song
 * @CreateTime: 2024-12-22  15:15
 * @Description: UserServiceApplication
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.songshilong.service.user.dao.mapper")
public class UserServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }
}
