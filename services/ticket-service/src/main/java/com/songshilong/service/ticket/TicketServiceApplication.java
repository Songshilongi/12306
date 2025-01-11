package com.songshilong.service.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  21:49
 * @Description: TicketServiceApplication
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.songshilong.service.ticket.dao.mapper")
public class TicketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApplication.class);
    }
}
