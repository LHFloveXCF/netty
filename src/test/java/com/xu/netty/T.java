package com.xu.netty;

import java.util.HashMap;
import java.util.Map;

/**
 * netty
 */
public class T {
    public static void main(String[] args) {
        Map<Integer, Integer> voteDetail = new HashMap<>();
        for (int integer = 1; integer < 10; integer++) {
            Integer nowValue = voteDetail.computeIfPresent(integer, (k, j) -> j + 1);
            if (null == nowValue) {
                voteDetail.put(integer, 1);
            }
        }

        for (int integer = 1; integer < 10; integer++) {
            Integer nowValue = voteDetail.computeIfPresent(integer, (k, j) -> j + 1);
            if (null == nowValue) {
                voteDetail.put(integer, 1);
            }
        }
        System.out.println(voteDetail.toString());
    }
}
