package com.test;

/**
 * @AUTHOR zhangzhiyuan
 * @CREATE 2019/3/22 15:44
 */
public class ClassOrderParent {
    static {
        System.out.println("ClassOrderParent is static");
    }
    //静态
    private static OrderParentStatic orderParentStatic = new OrderParentStatic();
    //非静态
    private OrderParentNoStatic orderParentNoStatic = new OrderParentNoStatic();

    {
        System.out.println("ClassOrderParent is non-static");
    }

    public ClassOrderParent() {
        System.out.println("ClassOrderParent init");
    }
}
