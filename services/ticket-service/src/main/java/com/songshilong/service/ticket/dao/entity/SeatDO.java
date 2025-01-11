package com.songshilong.service.ticket.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.framework.starter.database.base.BaseDO;
import lombok.*;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.service.ticket.dao.entity
 * @Author: Shilong Song
 * @CreateTime: 2025-01-11  21:54
 * @Description: SeatDO
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_seat")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 列车id
     */
    private Long trainId;

    /**
     * 车厢号
     */
    private String carriageNumber;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 座位类型
     */
    private Integer seatType;

    /**
     * 起始站
     */
    private String startStation;

    /**
     * 终点站
     */
    private String endStation;

    /**
     * 座位状态
     */
    private Integer seatStatus;

    /**
     * 车票价格
     */
    private Integer price;
}
