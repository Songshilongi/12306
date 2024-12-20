package com.songshilong.framework.starter.distributedid.core.serviceid;

import com.songshilong.framework.starter.distributedid.core.IdGenerator;
import com.songshilong.framework.starter.distributedid.core.snowflake.SnowflakeIdInfo;
import com.songshilong.framework.starter.distributedid.toolkit.SnowflakeIdUtil;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core.serviceid
 * @Author: Shilong Song
 * @CreateTime: 2024-12-20  09:17
 * @Description: 业务ID生成器
 * @Version: 1.0
 */
public final class DefaultServiceIdGenerator implements ServiceIdGenerator{

    /**
     * 工作 ID 5 bit
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * 数据中心 ID 5 bit
     */
    private static final long DATA_CENTER_ID_BITS = 5L;

    /**
     * 序列号 12 位，表示只允许 workerId 的范围为 0-4095
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 真实序列号 bit
     */
    private static final long SEQUENCE_ACTUAL_BITS = 8L;

    /**
     * 基因 bit
     */
    private static final long SEQUENCE_BIZ_BITS = 4L;

    /**
     * 机器节点左移12位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 默认开始时间
     */
    private static final long DEFAULT_TWEPOCH = 1288834974657L;

    /**
     * 数据中心节点左移 17 位
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间毫秒数左移 22 位
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * ID 生成器
     */
    private final IdGenerator idGenerator;

    /**
     * 业务ID最大长度
     */
    private final Long maxBizLengthBit;

    public DefaultServiceIdGenerator() {
        this(SEQUENCE_BIZ_BITS);
    }

    public DefaultServiceIdGenerator(Long maxBizLength) {
        this.idGenerator = SnowflakeIdUtil.getInstance();
        this.maxBizLengthBit = (long) Math.pow(2, maxBizLength);
    }

    @Override
    public Long nextId(Long serviceId) {
        long id = Math.abs(serviceId.hashCode()) % (this.maxBizLengthBit);
        return idGenerator.nextId() | id;
    }

    @Override
    public String nextIdStr(Long serviceId) {
        return idGenerator.nextIdStr();
    }

    @Override
    public SnowflakeIdInfo parseSnowflakeId(long snowflakeId) {
        return SnowflakeIdInfo.builder()
                .workerId((int) ((snowflakeId >> WORKER_ID_SHIFT) & ~(-1L << WORKER_ID_BITS)))
                .dataCenterId((int) ((snowflakeId >> DATA_CENTER_ID_SHIFT) & ~(-1L << DATA_CENTER_ID_BITS)))
                .timestamp((snowflakeId >> TIMESTAMP_LEFT_SHIFT) + DEFAULT_TWEPOCH)
                .sequence((int) ((snowflakeId >> SEQUENCE_BIZ_BITS) & ~(-1L << SEQUENCE_ACTUAL_BITS)))
                .gene((int) (snowflakeId & ~(-1L << SEQUENCE_BIZ_BITS)))
                .build();
    }
}
