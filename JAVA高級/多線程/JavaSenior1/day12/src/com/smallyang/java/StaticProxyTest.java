package com.smallyang.java;

/**
 * 靜態代理舉例
 *  特點：代理類、被代理類在編譯期間就確定下來 都被寫死
 * @author USER
 * @date 2024-09-18 上午 06:21
 */
//
interface ClothFactory{
    void produceCloth();
}

// 代理類
class ProxyClothFactory implements  ClothFactory {
    // 就拿被代理類對象進行實例化
    private ClothFactory clothFactory;

    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工廠做一些準備工作");
        clothFactory.produceCloth();
        System.out.println("代理工廠做作一些後續的收尾工作");
    }
}

// 被代理類
class NikeClothFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("耐吉工廠生產一批運動服");
    }
}

// 靜態代理：代理類、被代理類在編譯期間就確定下來 都被寫死
public class StaticProxyTest {
    public static void main(String[] args) {
        // 創建被代理類的對象
        NikeClothFactory nike = new NikeClothFactory();
        // 創建代理類的對象
        ClothFactory proxyClothFactory = new ProxyClothFactory(nike);

        proxyClothFactory.produceCloth();
    }
}
