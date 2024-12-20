package com.songshilong.framework.starter.database.toolkit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songshilong.framework.starter.common.toolkit.BeanUtil;
import com.songshilong.framework.starter.convention.page.PageRequest;
import com.songshilong.framework.starter.convention.page.PageResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.database.toolkit
 * @Author: Shilong Song
 * @CreateTime: 2024-12-20  10:20
 * @Description: 分页工具类
 * @Version: 1.0
 */
public class PageUtil {

    /**
     * {@link PageRequest} to {@link Page}
     */
    public static <T> Page<T> convert(PageRequest pageRequest) {
        return convert(pageRequest.getCurrent(), pageRequest.getSize());
    }

    /**
     * {@link PageRequest} to {@link Page}
     */
    public static <T> Page<T> convert(long current, long size) {
        return new Page<T>(current, size);
    }

    /**
     * {@link IPage} to {@link PageResponse}
     */
    public static <T> PageResponse<T> convert(IPage<T> iPage) {
        return buildConventionPage(iPage);
    }

    /**
     * {@link IPage} to {@link PageResponse}
     * <p>该方法用于将 IPage<ORIGINAL> 对象转换为 PageResponse<TARGET> 对象。</p>
     */
    public static <TARGET, ORIGINAL> PageResponse<TARGET> convert(IPage<ORIGINAL> iPage, Class<TARGET> targetClass) {
        IPage<TARGET> convert = iPage.convert(each -> BeanUtil.convert(each, targetClass));
        return buildConventionPage(convert);
    }

    /**
     * {@link IPage} to {@link PageResponse}
     */
    public static <TARGET, ORIGINAL> PageResponse<TARGET> convert(IPage<ORIGINAL> iPage, Function<? super ORIGINAL, ? extends TARGET> mapper) {
        List<TARGET> targetDataList = iPage.getRecords().stream()
                .map(mapper)
                .collect(Collectors.toList());
        return PageResponse.<TARGET>builder()
                .current(iPage.getCurrent())
                .size(iPage.getSize())
                .records(targetDataList)
                .total(iPage.getTotal())
                .build();
    }

    /**
     * {@link IPage} build to {@link PageResponse}
     */
    private static <T> PageResponse<T> buildConventionPage(IPage<T> iPage) {
        return PageResponse.<T>builder()
                .current(iPage.getCurrent())
                .size(iPage.getSize())
                .records(iPage.getRecords())
                .total(iPage.getTotal())
                .build();
    }



}
