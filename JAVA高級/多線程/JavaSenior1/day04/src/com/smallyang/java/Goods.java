package com.smallyang.java;

/**
 * 商品類
 *
 * @author USER
 * @date 2024-03-16 上午 08:44
 */
public class Goods implements Comparable {
    private String name;
    private double price;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


    // 指名商品比較大小的方式:按照價格從低到高排序，再按照產品名稱從低到高排序
    //  產品排序多一個-，變成從高排到低
    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods) {
            Goods goods = (Goods) o;
            // 方式一
            if (this.price > goods.price) {
                return 1;
            } else if (this.price < goods.price) {
                return -1;
            } else {
//                return 0;
//                return this.name.compareTo(goods.name);
                return -this.name.compareTo(goods.name);
            }

            // 方式二
//            return Double.compare(this.price, goods.price);
        }
        throw new RuntimeException("傳入的數據類型不一致!");
    }
}
