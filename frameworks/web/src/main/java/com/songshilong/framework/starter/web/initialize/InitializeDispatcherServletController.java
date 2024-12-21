package com.songshilong.framework.starter.web.initialize;

import com.songshilong.framework.starter.web.config.WebAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.web.initialize
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  16:45
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
public class InitializeDispatcherServletController {

    @GetMapping(WebAutoConfiguration.INITIALIZE_PATH)
    public void initializeDispatcherServlet() {
        log.info("初始化dispatchServlet优化接口调用速度");
        /*
        这里可以写具体的操作。
         */
    }

}
