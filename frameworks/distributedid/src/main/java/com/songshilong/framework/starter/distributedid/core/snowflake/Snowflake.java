package com.songshilong.framework.starter.distributedid.core.snowflake;

import cn.hutool.core.date.SystemClock;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.songshilong.framework.starter.distributedid.core.IdGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @BelongsProject: 12306-ssl
 * @BelongsPackage: com.songshilong.framework.starter.distributedid.core.snowflake
 * @Author: Shilong Song
 * @CreateTime: 2024-12-19  17:46
 * @Description: 雪花算法
 * @Version: 1.0
 * <p>
 * Twitter的Snowflake 算法<br>
 * 分布式系统中，有一些需要使用全局唯一ID的场景，有些时候我们希望能使用一种简单一些的ID，并且希望ID能够按照时间有序生成。
 *
 * <p>
 * snowflake的结构如下(每部分用-分开):<br>
 *
 * <pre>
 * 符号位（1bit）- 时间戳相对值（41bit）- 数据中心标志（5bit）- 机器标志（5bit）- 递增序号（12bit）
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * </pre>
 * <p>
 * 第一位为未使用(符号位表示正数)，接下来的41位为毫秒级时间(41位的长度可以使用69年)<br>
 * 然后是5位datacenterId和5位workerId(10位的长度最多支持部署1024个节点）<br>
 * 最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）
 * <p>
 * 并且可以通过生成的id反推出生成时间,datacenterId和workerId
 * <p>
 * <a href="http://www.cnblogs.com/relucent/p/4955340.html">参考：</a><br>
 * <a href="https://blog.csdn.net/unifirst/article/details/80408050">关于长度是18还是19的问题见：</a>
 */
public class Snowflake implements Serializable, IdGenerator {

    @Serial
    private static final long serialVersionUID = 126848618784245L;

    /**
     * 默认的起始时间 2010-10-01 00:00:00 -> 1285862400 (Asia/Shanghai)
     */
    private static final long DEFAULT_TWEPOCH = 1285862400L;
    /**
     * 默认的回拨时间 2000ms = 2s
     */
    private static final long DEFAULT_TIME_OFFSET = 2000L;
    /**
     * 机器标志 5位
     */
    private static final long WORKER_ID_BITS = 5L;
    /**
     * 最大支持的机器数量  -1L = 11111111 11111111  11111111 11111111 11111111 11111111  11111111 11111111 (64bit)<br>
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    /**
     * 数据中心标志 5L
     */
    private static final long DATA_CENTER_ID_BITS = 5L;
    /**
     * 最大支持的数据中心数量
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * 序列号位数
     */
    private static final long SEQUENCE_BITS = 12L;
    /**
     * 机器标志左移 12
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    /**
     * 数据中心左移 12 + 5
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    /**
     * 时间戳左移 12 + 5 + 5
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 序列号掩码 限制最大序列号不超过 2^12 - 1 = 4095
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 初始化时间点
     */
    private final long twepoch;
    /**
     * 机器ID
     */
    private final long workId;
    /**
     * 数据中心ID
     */
    private final long dataCenterId;
    /**
     * 是否使用系统时钟
     */
    private final boolean useSystemClock;
    /**
     * 时钟回拨毫秒数
     */
    private final long timeOffset;

    /**
     * 当在低频模式下时，序号始终为0，导致生成ID始终为偶数<br>
     * 此属性用于限定一个随机上限，在不同毫秒下生成序号时，给定一个随机数，避免偶数问题。<br>
     * 注意次数必须小于{@link #SEQUENCE_MASK}，{@code 0}表示不使用随机数。<br>
     * 这个上限不包括值本身。
     */
    private final long randomSequenceLimit;

    /**
     * 自增序号，当高频模式下时，同一毫秒内生成N个ID，则这个序号在同一毫秒下，自增以避免ID重复。
     */
    private long sequence = 0L;

    /**
     * 上一个时间戳
     */
    private long lastTimestamp = -1L;


    /**
     * 构造，使用自动生成的工作节点ID和数据中心ID
     */
    public Snowflake() {
        this(IdUtil.getWorkerId(IdUtil.getDataCenterId(MAX_DATA_CENTER_ID), MAX_WORKER_ID));
    }

    /**
     * @param workId 终端ID
     */
    public Snowflake(long workId) {
        this(workId, IdUtil.getDataCenterId(MAX_DATA_CENTER_ID));
    }

    /**
     * @param workId     终端ID
     * @param dataCenterId 数据中心ID
     */
    public Snowflake(long workId, long dataCenterId) {
        this(workId, dataCenterId, false);
    }

    /**
     * @param workId         终端ID
     * @param dataCenterId     数据中心ID
     * @param isUseSystemClock 是否使用{@link SystemClock} 获取当前时间戳
     */
    public Snowflake(long workId, long dataCenterId, boolean isUseSystemClock) {
        this(null, workId, dataCenterId, isUseSystemClock);
    }

    /**
     * @param epochDate        初始化时间起点（null表示默认起始日期）,后期修改会导致id重复,如果要修改连workerId dataCenterId，慎用
     * @param workId         工作机器节点id
     * @param dataCenterId     数据中心id
     * @param isUseSystemClock 是否使用{@link SystemClock} 获取当前时间戳
     * @since 5.1.3
     */
    public Snowflake(Date epochDate, long workId, long dataCenterId, boolean isUseSystemClock) {
        this(epochDate, workId, dataCenterId, isUseSystemClock, DEFAULT_TIME_OFFSET);
    }

    /**
     * @param epochDate        初始化时间起点（null表示默认起始日期）,后期修改会导致id重复,如果要修改连workerId dataCenterId，慎用
     * @param workId         工作机器节点id
     * @param dataCenterId     数据中心id
     * @param isUseSystemClock 是否使用{@link SystemClock} 获取当前时间戳
     * @param timeOffset       允许时间回拨的毫秒数
     * @since 5.8.0
     */
    public Snowflake(Date epochDate, long workId, long dataCenterId, boolean isUseSystemClock, long timeOffset) {
        this(epochDate, workId, dataCenterId, isUseSystemClock, timeOffset, 0);
    }

    /**
     * @param epochDate           初始化时间起点（null表示默认起始日期）,后期修改会导致id重复,如果要修改连workerId dataCenterId，慎用
     * @param workId            工作机器节点id
     * @param dataCenterId        数据中心id
     * @param useSystemClock    是否使用{@link SystemClock} 获取当前时间戳
     * @param timeOffset          允许时间回拨的毫秒数
     * @param randomSequenceLimit 限定一个随机上限，在不同毫秒下生成序号时，给定一个随机数，避免偶数问题，0表示无随机，上限不包括值本身。
     * @since 5.8.0
     */
    public Snowflake(Date epochDate, long workId, long dataCenterId, boolean useSystemClock, long timeOffset, long randomSequenceLimit) {
        this.twepoch = (null != epochDate) ? epochDate.getTime() : DEFAULT_TWEPOCH;
        this.workId = Assert.checkBetween(workId, 0, MAX_WORKER_ID);
        this.dataCenterId = Assert.checkBetween(dataCenterId, 0, MAX_DATA_CENTER_ID);
        this.useSystemClock = useSystemClock;
        this.timeOffset = timeOffset;
        this.randomSequenceLimit = Assert.checkBetween(randomSequenceLimit, 0, SEQUENCE_MASK);
    }

    /**
     * 根据Snowflake的ID，获取机器id
     *
     * @param id snowflake算法生成的id
     * @return 所属机器的id
     */
    public long getWorkerId(long id) {
        return id >> WORKER_ID_SHIFT & ~(-1L << WORKER_ID_BITS);
    }

    /**
     * 根据Snowflake的ID，获取数据中心id
     *
     * @param id snowflake算法生成的id
     * @return 所属数据中心
     */
    public long getDataCenterId(long id) {
        return id >> DATA_CENTER_ID_SHIFT & ~(-1L << DATA_CENTER_ID_BITS);
    }

    /**
     * 根据Snowflake的ID，获取生成时间
     *
     * @param id snowflake算法生成的id
     * @return 生成的时间
     */
    public long getGenerateDateTime(long id) {
        return (id >> TIMESTAMP_SHIFT & ~(-1L << 41L)) + twepoch;
    }



    @Override
    public Long nextId() {
        long timestamp = this.genTime();
        if (timestamp < this.lastTimestamp) {
            if (this.lastTimestamp - timestamp < timeOffset) {
                timestamp = this.lastTimestamp;
            } else {
                // 如果服务器时间有问题(时钟后退) 报错。
                throw new IllegalStateException(StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", lastTimestamp - timestamp));
            }
        }
        // 如果当前时间戳 等于 上一个时间戳
        if (timestamp == this.lastTimestamp) {
            final long sequence = (this.sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
            this.sequence = sequence;
        } else {
            // issue#I51EJY
            if (randomSequenceLimit > 1) {
                sequence = RandomUtil.randomLong(randomSequenceLimit);
            } else {
                sequence = 0L;
            }
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << TIMESTAMP_SHIFT) | (dataCenterId << DATA_CENTER_ID_SHIFT) | (workId << WORKER_ID_SHIFT) | sequence;
    }

    @Override
    public String nextIdStr() {
        return Long.toString(nextId());
    }
    /**
     * 循环等待下一个时间
     *
     * @param lastTimestamp 上次记录的时间
     * @return 下一个时间
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = genTime();
        // 循环直到操作系统时间戳变化
        while (timestamp == lastTimestamp) {
            timestamp = genTime();
        }
        if (timestamp < lastTimestamp) {
            // 如果发现新的时间戳比上次记录的时间戳数值小，说明操作系统时间发生了倒退，报错
            throw new IllegalStateException(StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", lastTimestamp - timestamp));
        }
        return timestamp;
    }

    /**
     * 生成时间戳
     *
     * @return 时间戳
     */
    private long genTime() {
        return this.useSystemClock ? SystemClock.now() : System.currentTimeMillis();
    }

    /**
     * 解析雪花算法生成的 ID 为对象
     *
     * @param snowflakeId 雪花算法 ID
     * @return {@link SnowflakeIdInfo}
     */
    public SnowflakeIdInfo parseSnowflakeId(long snowflakeId) {
        return SnowflakeIdInfo.builder().sequence((int) (snowflakeId & ~(-1L << SEQUENCE_BITS))).workerId((int) ((snowflakeId >> WORKER_ID_SHIFT)
                        & ~(-1L << WORKER_ID_BITS))).dataCenterId((int) ((snowflakeId >> DATA_CENTER_ID_SHIFT)
                        & ~(-1L << DATA_CENTER_ID_BITS)))
                .timestamp((snowflakeId >> TIMESTAMP_SHIFT) + twepoch).build();
    }

}
