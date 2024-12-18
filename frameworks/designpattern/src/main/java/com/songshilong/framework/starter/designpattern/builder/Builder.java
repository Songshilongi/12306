package com.songshilong.framework.starter.designpattern.builder;

import java.io.Serializable;

/**
 * @author Songshilong
 */
public interface Builder<T> extends Serializable {

    /**
     * 构建对象
     * @return 构建后的对象
     */
    T build();
}
