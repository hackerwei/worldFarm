package com.hz.world.common.ids;

/**
 * workerId:0~31随机数
 * datacenterId:0~31随机数
 */
public class IDGenerator {

    private static IDWorker idWorker;

    static {
        idWorker = new IDWorker(nextRandom(), nextRandom());
    }

    private static long nextRandom() {
        return (long) (Math.random() * 32);
    }

    public static long getUniqueId() {
        return idWorker.nextId();
    }
}
