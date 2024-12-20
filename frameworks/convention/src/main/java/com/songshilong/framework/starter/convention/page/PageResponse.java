package com.songshilong.framework.starter.convention.page;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.page
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  16:26
 * @Description: PageResponse
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页显示条数
     */
    private Long size;

    /**
     * 总数
     */
    private Long total;

    /**
     * 查询数据列表
     */
    private List<T> records;


    public PageResponse(long current, long size) {
        this(current, size, 0);
    }

    /**
     * 默认的分页结果数据都是空的list
     */
    public PageResponse(long current, long size, long total) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
        this.records = Collections.emptyList();
    }

    public PageResponse<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }


    /**
     * 转换
     *
     * @param mapper T或者T的父类映射成R或者R的子类
     * @param <R>    结果类型
     * @return PageResponse<R>
     */
    public <R> PageResponse<R> convert(Function<? super T, ? extends R> mapper) {
        List<R> result = this.getRecords().stream().map(mapper).collect(Collectors.toList());
        return ((PageResponse<R>) this).setRecords(result);
    }


}
