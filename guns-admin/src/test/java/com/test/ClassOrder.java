package com.test;

/**
 * @AUTHOR zhangzhiyuan
 * @CREATE 2019/3/22 15:39
 */
public class ClassOrder extends ClassOrderParent{
    public ClassOrder() {
        System.out.println("ClassOrder init");
    }
    //静态
    private static OrderStatic orderStatic = new OrderStatic();
    //非静态
    private OrderNoStatic orderNoStatic = new OrderNoStatic();
    static {
        System.out.println("ClassOrder is static");
    }
    {
        System.out.println("ClassOrder is non-static");
    }


}
