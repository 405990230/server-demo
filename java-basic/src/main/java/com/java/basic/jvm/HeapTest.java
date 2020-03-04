package com.java.basic.jvm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class HeapTest {
    //-XX:+UseParallelGC -XX:+PrintGCDetails
    byte[] a = new byte[1024*10];
    public static void main(String[] args) throws InterruptedException{
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        System.out.println("MAX_MEMBER:"+runtime.maxMemory()/1024/1024);//最大可用内存
        System.out.println("TOTAL_MEMBER:"+runtime.totalMemory()/1024/1024);//默认的可用内存
        System.out.println("FREE_MEMBER:"+runtime.freeMemory()/1024/1024);//可用的内存中空闲的部分
        while(true){
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
