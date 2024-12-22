package com.songshilong.framework.starter.distributedid.core.snowflake;

import cn.hutool.core.collection.CollUtil;
import com.songshilong.framework.starter.base.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core.snowflake
 * @Author: Shilong Song
 * @CreateTime: 2024-12-19  23:18
 * @Description: LocalRedisWorkIdChoose
 * @Version: 1.0
 */
@Slf4j
public class LocalRedisWorkIdChoose extends AbstractWorkIdChooseTemplate implements InitializingBean {

    private final StringRedisTemplate redisTemplate;

    public LocalRedisWorkIdChoose() {
        this.redisTemplate = ApplicationContextHolder.getBean(StringRedisTemplate.class);
    }

    @Override
    protected WorkIdWrapper chooseWorkId() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/chooseWorkIdLua.lua")));
        List<Long> luaResultList = null;
        try {
            redisScript.setResultType(List.class);
            luaResultList = (ArrayList) this.redisTemplate.execute(redisScript, null);
        } catch (Exception ex) {
            log.error("Redis Lua 脚本获取 WorkId 失败", ex);
        }
        return CollUtil.isNotEmpty(luaResultList) ? new WorkIdWrapper(luaResultList.get(0), luaResultList.get(1)) : new RandomWorkIdChoose().chooseWorkId();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.chooseAndInit();
    }
}
