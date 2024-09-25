package com.smallyang.java1;

import java.io.Serializable;

/**
 * @author USER
 * @date 2024-09-06 上午 07:47
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath() {
        System.out.println("呼吸嚕");
    }

    public void eat() {
        System.out.println("吃飯嚕");
    }

}
