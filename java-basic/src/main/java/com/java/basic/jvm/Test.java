package com.java.basic.jvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(Arrays.asList("dddd", "dsad", "dsawe", "11111"));
        strings.add("a");
        List strings1 = Arrays.asList("457", "999");
        strings.addAll(strings1);
        System.out.println(strings.toString());
    }
}
