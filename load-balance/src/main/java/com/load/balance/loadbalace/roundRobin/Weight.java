package com.load.balance.loadbalace.roundRobin;

import lombok.Data;

/**
 * 增加一个Weight类，用来保存ip,weight(固定不变的原始权重),currentWeight(当前会变化的权重)
 */
@Data
public class Weight {
    private String ip;
    private Integer weight;
    private Integer currentWeight;

    public Weight(String ip, Integer weight, Integer currentWeight) {
        this.ip = ip;
        this.weight = weight;
        this.currentWeight = currentWeight;
    }
}
