package com.load.balance.loadbalace.roundRobin;

import com.load.balance.loadbalace.ServerIps;

/**
 * 简单轮询算法
 */
public class RoundRobin {

    //当前循环的位置
    private static Integer pos = 0;
    public static String getServer() {
        String ip = null;
        // pos同步
        synchronized (pos) {
            if (pos >= ServerIps.LIST.size()) {
                pos = 0;
            }
            ip = ServerIps.LIST.get(pos);
            pos++;
        }
        return ip;
    }
    public static void main(String[] args) {
        //连续调用10次
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
