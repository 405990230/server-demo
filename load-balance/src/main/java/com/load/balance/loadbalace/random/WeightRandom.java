package com.load.balance.loadbalace.random;

import com.load.balance.loadbalace.ServerIps;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的权重随机算法
 */
public class WeightRandom {
    public static String getServer() {
        // 生成一个随机数作为listd的下标志
        List<String> ips = new ArrayList<String>();
        for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            // 按权重进行复制
            for (int i=0; i<weight; i++) {
                ips.add(ip);
            }
        }
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(ips.size());
        return ips.get(randomPos);
    }
    public static void main(String[] args) {
        // 连续调用10次
        for (int i=0; i<10; i++) {
            System.out.println(getServer());
        }
    }
}
