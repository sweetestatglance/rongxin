//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

public class LLTest {
    public LLTest() {
    }

    public static void main(String[] args) {
        double beginX = 5.0D;
        double beginY = 9.0D;
        double endX = 12.0D;
        double endY = 17.0D;
        double xieLv = (endY - beginY) * 1.0D / (endX - beginX);
        double x = 10.0D;
        double y = xieLv * (x - beginX) + beginY;
        System.out.println("y=============" + y);
    }
}
