package com.songshilong.framework.starter.distributedid.core.snowflake;

import org.springframework.beans.factory.InitializingBean;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core.snowflake
 * @Author: Shilong Song
 * @CreateTime: 2024-12-19  23:16
 * @Description: RandomWorkIdChoose
 * @Version: 1.0
 */
public class RandomWorkIdChoose extends AbstractWorkIdChooseTemplate implements InitializingBean {


    @Override
    protected WorkIdWrapper chooseWorkId() {
        int start = 0, end = 31;
        return new WorkIdWrapper(getRandom(start, end), getRandom(start, end));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.chooseAndInit();
    }

    /**
     * 生成一个随机数 start ~ end
     */
    private static long getRandom(int start, int end) {
        return (long) (Math.random() * (end - start + 1) + start);
    }
}
