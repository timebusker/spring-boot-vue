package com.timebusker.utils;

/**
 * @DESC:SequenceIdUtil：唯一ID序列生产器
 * @author:timebusker
 * @date:2019/3/15
 */
public class SequenceIdUtil {

    /**
     * 起始的时间戳
     */
    private final static long START_TIMESTAMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    // 序列号占用的位数
    private final static long SEQUENCE_BIT = 12;
    // 机器标识占用的位数
    private final static long MACHINE_BIT = 5;
    // 数据中心占用的位数
    private final static long CENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_CENTER_NUM = -1L ^ (-1L << CENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = CENTER_LEFT + CENTER_BIT;
    // 数据中心
    private long centerId;
    // 机器标识
    private long machineId;
    // 序列号
    private long sequence = 0L;
    // 上一次时间戳
    private long lastTmp = -1L;

    public SequenceIdUtil(long centerId, long machineId) {
        if (centerId > MAX_CENTER_NUM || centerId < 0) {
            throw new IllegalArgumentException("centerId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.centerId = centerId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currTmp = getNewTimeStamp();
        if (currTmp < lastTmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currTmp == lastTmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currTmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        lastTmp = currTmp;
        // 时间戳部分 - 数据中心部分 - 机器标识部分 - 机器标识部分
        return (currTmp - START_TIMESTAMP) << TIMESTAMP_LEFT | centerId << CENTER_LEFT | machineId << MACHINE_LEFT | sequence;
    }

    private long getNextMill() {
        long mill = getNewTimeStamp();
        while (mill <= lastTmp) {
            mill = getNewTimeStamp();
        }
        return mill;
    }

    private long getNewTimeStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SequenceIdUtil sequence = new SequenceIdUtil(0, 1);
        for (int i = 0; i < (1 << 12); i++) {
            System.out.println(sequence.nextId());
        }
    }
}
