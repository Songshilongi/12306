package com.songshilong.framework.starter.designpattern.straregy;

import com.songshilong.framework.starter.base.ApplicationContextHolder;
import com.songshilong.framework.starter.base.init.ApplicationInitializingEvent;
import com.songshilong.framework.starter.convention.exception.ServiceException;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.designpattern.straregy
 * @Author: Song shi long
 * @CreateTime: 2024-12-15  09:41
 * @Description: 策略选择器
 * @Version: 1.0
 * <p><code>ApplicationListener</code>事件监听器，可以监听某个事件是否发生</p>
 * <p><code>ApplicationInitializingEvent</code>应用初始化事件</p>
 */
public class AbstractStrategyChoose<REQUEST, RESPONSE> implements ApplicationListener<ApplicationInitializingEvent> {


    /**
     * 策略容器
     */
    private final Map<String, AbstractExecuteStrategy<REQUEST, RESPONSE>> abstractExecuteStrategyMap = new HashMap<>();

    @Override
    public void onApplicationEvent(ApplicationInitializingEvent event) {
        Map<String, AbstractExecuteStrategy> actual = ApplicationContextHolder.getBeansOfType(AbstractExecuteStrategy.class);
        actual.forEach((beanName, bean) -> {
            String mark = bean.mark();
            AbstractExecuteStrategy<REQUEST, RESPONSE> abstractExecuteStrategy = abstractExecuteStrategyMap.get(mark);
            if (abstractExecuteStrategy != null) {
                throw new ServiceException(String.format("[%s] Duplicate execution policy", bean.mark()));
            }
            abstractExecuteStrategyMap.put(mark, bean);
        });
    }

    /**
     * 根据 mark 查询具体策略
     *
     * @param mark          策略标识
     * @param predicateFlag 匹配范解析标识
     * @return 实际执行策略
     */
    public AbstractExecuteStrategy<REQUEST, RESPONSE> choose(String mark, Boolean predicateFlag) {
        if (predicateFlag != null && predicateFlag) {
            abstractExecuteStrategyMap.values().stream()
                    .filter(item -> StringUtils.hasText(item.patternMatchMark()))
                    .filter(item -> Pattern.compile(item.patternMatchMark()).matcher(mark).matches())
                    .findFirst()
                    .orElseThrow(() -> new ServiceException("策略未定义"));
        }
        return Optional.ofNullable(abstractExecuteStrategyMap.get(mark))
                .orElseThrow(() -> new ServiceException(String.format("%s 策略未定义", mark)));
    }

    /**
     * 查询具体策略执行 - 无返回结果
     *
     * @param mark         执行策略标识
     * @param requestParam 请求参数
     */
    public void chooseAndExecute(String mark, REQUEST requestParam) {
        AbstractExecuteStrategy<REQUEST, RESPONSE> executeStrategy = this.choose(mark, null);
        executeStrategy.execute(requestParam);
    }

    /**
     * 查询具体策略执行 - 无返回结果
     *
     * @param mark          执行策略标识
     * @param requestParam  请求参数
     * @param predicateFlag 匹配范解析标识
     */
    public void chooseAndExecute(String mark, REQUEST requestParam, Boolean predicateFlag) {
        AbstractExecuteStrategy<REQUEST, RESPONSE> executeStrategy = this.choose(mark, predicateFlag);
        executeStrategy.execute(requestParam);
    }


    /**
     * 查询具体策略执行 - 有返回结果
     *
     * @param mark         执行策略标识
     * @param requestParam 请求参数
     * @return RESPONSE
     */
    public RESPONSE chooseAndExecuteResp(String mark, REQUEST requestParam) {
        AbstractExecuteStrategy<REQUEST, RESPONSE> executeStrategy = this.choose(mark, null);
        return executeStrategy.executeResp(requestParam);
    }

    /**
     * 查询具体策略执行 - 有返回结果
     *
     * @param mark          执行策略标识
     * @param requestParam  请求参数
     * @param predicateFlag 匹配范解析标识
     * @return RESPONSE
     */
    public RESPONSE chooseAndExecuteResp(String mark, REQUEST requestParam, Boolean predicateFlag) {
        AbstractExecuteStrategy<REQUEST, RESPONSE> executeStrategy = this.choose(mark, predicateFlag);
        return executeStrategy.executeResp(requestParam);
    }


}
