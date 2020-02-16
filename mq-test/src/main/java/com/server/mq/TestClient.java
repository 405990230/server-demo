package com.server.mq;

public class TestClient {
    public static void main(String[] args) throws Exception {
        Producer.sendByExchange("hello");
        Consumer.getMessage();
    }
}
