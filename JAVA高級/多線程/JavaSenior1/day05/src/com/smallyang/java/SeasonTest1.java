package com.smallyang.java;

/**
 * 使用enum關鍵字定義枚舉類
 * 說明:定義的枚舉類預設繼承於class java.lang.Enum類
 *
 * @author USER
 * @date 2024-04-08 上午 05:53
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 spring = Season1.SPRING;
        System.out.println(spring);
        System.out.println(spring.getSeasonName());
        System.out.println(spring.getSeasonDesc());
        System.out.println(Season1.class.getSuperclass());

    }
}

interface Info {
    void show();
}

// 使用enum關鍵字枚舉類
enum Season1 implements Info {
    // 1. 提供當前枚舉類的對象，多個對象之間用逗號","隔開，末尾對象";"結束
    SPRING("春天", "春暖花開") {
        @Override //情況二: 讓枚舉類的對象分別實現接口中的抽象方法
        public void show() {
            System.out.println("春心蕩漾");
        }
    },
    SUMMER("夏天", "夏日炎炎") {
        @Override
        public void show() {
            System.out.println("夏天泳衣");
        }
    },
    AUTUMN("秋天", "秋高氣爽") {
        @Override
        public void show() {
            System.out.println("秋老虎");
        }
    },
    WINTER("冬天", "冰天雪地") {
        @Override
        public void show() {
            System.out.println("冬雨綿綿");
        }
    };


    //1. 聲明Season對象的屬性:private final 修飾
    // 顯示賦值 構造器賦值 代碼塊賦值
    private final String seasonName;
    private final String seasonDesc;

    //2. 私有化類的構造器
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //4. 其他訴求1.:獲取枚舉類對象的屬性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

//4. 其他訴求2 .:提供toString
// 如果打開註解，就相當於重寫toString，而不是用enum
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


//    @Override 情況一: 實現接口，在enum類中實現抽象方法
//    public void show() {
//        System.out.println("這是一個季節");
//    }
}
