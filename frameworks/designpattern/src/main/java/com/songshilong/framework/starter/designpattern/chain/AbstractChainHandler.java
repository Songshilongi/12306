package com.songshilong.framework.starter.designpattern.chain;


import org.springframework.core.Ordered;

/**
 * @author Songshilong
 */
public interface AbstractChainHandler<T> extends Ordered {

    /**
     * 责任链当前元素的处理逻辑
     */
    void handler(T requestParam);

    /**
     * @return 责任链标识
     */
    String mark();
}
