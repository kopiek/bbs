package com.top.sample.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * twitter 雪花算法
 * 用于生成全局唯一ID
 *
 * @author wangzhikang
 */
@Component
public class SnowFlake {

    //起始时间戳
    private final static long START_STMP = 1480166465631L;
    //序列号占用位数
    private final static long SEQUENCE_BIT = 12;
    //机器标识占用位数
    private final static long MACHINE_BIT = 5;
    //数据中心占用位数
    private final static long DATACENTER_BIT = 5;
    //每一部分最大值
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    //每一部分向左的移位
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    //数据中心
    @Value("${snow.flake.datacent.id}")
    private long datacentId;
    //机器标识
    @Value("${snow.flake.machine.id}")
    private long machineId;
    //序列号
    private long sequence = 0L;
    //上一次时间戳
    private long lastStmp = -1L;

    public synchronized long nextId () {
        long currStmp = getNewstamp();
        if (currStmp < lastStmp) {
            throw new IllegalArgumentException("clock moved backwards,refusing to generate id");
        }

        if (currStmp == lastStmp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTAMP_LEFT
                | datacentId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewstamp();
        if (mill <= lastStmp) {
            mill = getNextMill();
        }
        return mill;
    }

    private long getNewstamp() {
        return System.currentTimeMillis();
    }
}
