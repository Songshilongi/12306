package com.songshilong.service.ticket.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.framework.starter.database.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.dao.entity
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  21:54
 * @Description: StationDO
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_station")
public class StationDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 车站编码
     */
    private String code;

    /**
     * 车站名称
     */
    private String name;

    /**
     * 拼音
     */
    private String spell;

    /**
     * 地区编号
     */
    private String region;

    /**
     * 地区名称
     */
    private String regionName;
}
