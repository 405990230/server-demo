package com.java.basic.jvm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkedDemo {

        public static void main(String[] args) {
                Set set = new HashSet();
                List<String> list = new ArrayList();
                list.add("a");
                String[] str = {"a","b","c","d"};

                System.out.println(str[2]);
        }
}
