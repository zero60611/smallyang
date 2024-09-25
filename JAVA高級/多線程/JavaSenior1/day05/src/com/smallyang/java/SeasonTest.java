package com.smallyang.java;

/**
 * 一、枚舉類的使用
 * 1. 枚舉類的理解:類的對象只有有限個，確定的，我們稱此類為枚舉類。
 * 2. 當需要定義一組常量時，強烈建議使用枚舉類。
 * 3. 如果枚舉類中只有一個對象，則可以做為單例模式的實現方式。
 *
 *
 * 二、如何定義枚舉類
 *  方式一:jdk5.0前，自定義枚舉類
 *  方式二:jdk5.0後，可以使用enum關鍵字定義枚舉類
 *
 * 三、Enum類中常用的方法
 *  values():返回枚舉類型的對象數組，該方法可以很方便的便歷所有的枚舉類
 *  valueOf(String str): 可以把一個字幅串轉為對應的枚舉類對象，
 *          要求字符串必須是枚舉類對象的"名字"，如果不是會有異常。
 *  toString():返回當前枚舉類對象常量的名稱
 *
 *  四、 使用enum關鍵字定義的枚舉類實現接口的情況
 *     情況一: 實現接口，在enum類中實現抽象方法
 *     情況二: 讓枚舉類的對象分別實現接口中的抽象方法
 *
 * @author USER
 * @date 2024-03-30 下午 05:48
 */
public class SeasonTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        // toString():
        System.out.println(spring.toString());
//        System.out.println(spring.getSeasonName());
//        System.out.println(spring.getSeasonDesc());
        System.out.println("**********************");
        // values():
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
            values[i].show();
        }
        System.out.println("**********************");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i ++) {
            System.out.println(values1[i]);
        }
        System.out.println("**********************");
        // valueOf(String objName):返回枚舉類中對象名是objName的對象
        Season1 winter = Season1.valueOf("WINTER");
//        System.out.println(winter);
        // 如果沒有objName的枚舉類對象，則拋異常:IllegalArgumentException
//        Season1 winter1 = Season1.valueOf("WINTER1");
//        System.out.println(winter1);
//        Season1 winter = Season1.WINTER;
//        System.out.println(winter);
//        winter.show();

    }
}

// 自定義枚舉類
class Season{
    //1. 聲明Season對象的屬性:private final 修飾
    // 顯示賦值 構造器賦值 代碼塊賦值
    private final String seasonName;
    private final String seasonDesc;

    //2. 私有化類的構造器
    private Season(String seasonName,String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3. 提供當前枚舉類的多個對象 : public static final的 不可變的
    public static final Season SPRING = new Season("春天", "春暖花開");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高氣爽");
    public static final Season WINTER = new Season("冬天", "冰天雪地");

    //4. 其他訴求1.:獲取枚舉類對象的屬性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //4. 其他訴求2 .:提供toString
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
