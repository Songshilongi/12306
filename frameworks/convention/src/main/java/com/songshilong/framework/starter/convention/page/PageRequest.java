package com.songshilong.framework.starter.convention.page;

import lombok.Data;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.common.page
 * @Author: Song shi long
 * @CreateTime: 2024-12-14  16:25
 * @Description: PageRequest
 * @Version: 1.0
 */

@Data
public class PageRequest {

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页显示条数
     */
    private Long size;


}
