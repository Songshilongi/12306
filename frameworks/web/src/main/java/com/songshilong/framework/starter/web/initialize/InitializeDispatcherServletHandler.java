package com.songshilong.framework.starter.web.initialize;

import com.songshilong.framework.starter.web.config.WebAutoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.web.initialize
 * @Author: Shilong Song
 * @CreateTime: 2024-12-21  16:42
 * @Description: InitializeDispatcherServletHandler
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class InitializeDispatcherServletHandler implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final ConfigurableEnvironment configurableEnvironment;

    @Override
    public void run(String... args) throws Exception {
        String url = String.format("http://127.0.0.1:%s%s",
                configurableEnvironment.getProperty("server.port", "8080") + configurableEnvironment.getProperty("server.servlet.context-path", ""),
                WebAutoConfiguration.INITIALIZE_PATH);
        try {
            restTemplate.execute(url, HttpMethod.GET, null, null);
        } catch (Throwable ignored) {
        }
    }
}
