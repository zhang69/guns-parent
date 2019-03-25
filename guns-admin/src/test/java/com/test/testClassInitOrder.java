package com.test;

import org.junit.Test;

/**
 * @AUTHOR zhangzhiyuan
 * @CREATE 2019/3/22 16:11
 */
/**
 * Conclusion
 * execution order
 * first: parent static-method-block or static-field ,executing by order
 * second: son static-method-block or static-field ,executing by order
 * third: parent non-static-method-block or non-static-field,executing by order
 * forth: son non-static-method-block or non-static-field ,executing by order
 * fifth: parent construct-method
 * sixth: son construct-method
 */
public class testClassInitOrder {
    @Test
    public void testOrder() {
        ClassOrder classOrder = new ClassOrder();
    }

}
