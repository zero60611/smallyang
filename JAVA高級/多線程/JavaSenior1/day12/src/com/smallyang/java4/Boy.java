package com.smallyang.java4;

/**
 * @author USER
 * @date 2024-10-07 上午 05:42
 */
public class Boy {
    private Girl girl;

    public Boy(Girl girl) {
        this.girl = girl;
    }

    public Boy() {
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}
