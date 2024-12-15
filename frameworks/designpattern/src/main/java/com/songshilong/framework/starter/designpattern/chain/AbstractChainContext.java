package com.songshilong.framework.starter.designpattern.chain;

import com.songshilong.framework.starter.base.ApplicationContextHolder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.designpattern.chain
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  21:41
 * @Description:   抽象责任链上下文
<p><code>org.springframework.boot.CommandLineRunner</code>实现该接口的类可以在项目启动之后立即执行，
并且可以添加注解<code>@Order(value = ?)</code>实现控制执行顺序</p>
 * @Version: 1.0
 */
public final class AbstractChainContext<T> implements CommandLineRunner {

    /**
     * <p>key---组织标识</p>
     * <p>value---对应的抽象责任链</p>
     */
    private final Map<String, List<AbstractChainHandler<T>>> abstractChainHandlerContainer = new HashMap<>();


    /**
     * 责任链组件执行
     *
     * @param mark         责任链组件标识
     * @param requestParam 请求参数
     */
    public void handler(String mark, T requestParam) {
        List<AbstractChainHandler<T>> abstractChainHandlerList = abstractChainHandlerContainer.get(mark);
        if (CollectionUtils.isEmpty(abstractChainHandlerList)) {
            throw new RuntimeException(String.format("[%s] Chain of Responsibility ID is undefined.", mark));
        }
        abstractChainHandlerList.forEach(item -> item.handler(requestParam));
    }


    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractChainHandler> chainFilterMap = ApplicationContextHolder.getBeansOfType(AbstractChainHandler.class);
        chainFilterMap.forEach((name, bean) -> {
            String mark = bean.mark();
            List<AbstractChainHandler<T>> abstractChainHandlerList = abstractChainHandlerContainer.get(mark);
            if (CollectionUtils.isEmpty(abstractChainHandlerList)) {
                abstractChainHandlerList = new ArrayList<>();
            }
            abstractChainHandlerList.add(bean);
            List<AbstractChainHandler<T>> actualAbstractChainHandlerList = abstractChainHandlerList
                    .stream()
                    .sorted(Comparator.comparing(Ordered::getOrder))
                    .toList();
            abstractChainHandlerContainer.put(mark, actualAbstractChainHandlerList);
        });
    }
}
