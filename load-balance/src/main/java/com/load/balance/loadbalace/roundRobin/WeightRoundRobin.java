package com.load.balance.loadbalace.roundRobin;

import com.load.balance.loadbalace.ServerIps;

/**
 * 权重加轮询算法
 */
public class WeightRoundRobin {
    public static Integer num = 0;
    public static String getServer() {
        int totalWeight = 0;
        boolean sameWeight = true; // 如果所以权重都相等，那么随机一个ip就好了
        Object[] weights = ServerIps.WEIGHT_LIST.values().toArray();
        for (int i = 0; i < weights.length; i++) {
            Integer weight = (Integer) weights[i];
            totalWeight += weight;
            if (sameWeight && i > 0 && !weight.equals(weights[i - 1])) {
                sameWeight = false;
            }
        }
        Integer sequenceNum = getAndIncrement();
        Integer offset = sequenceNum % totalWeight;
        offset = offset == 0 ? totalWeight : offset;
        if (!sameWeight) {
            for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
                Integer weight = ServerIps.WEIGHT_LIST.get(ip);
                if (offset <= weight) {
                    return ip;
                }
                offset = offset - weight;
            }
        }
        return null;
    }

    public static Integer getAndIncrement() {
        return ++num;
    }

    public static void main(String[] args) {
        //连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
