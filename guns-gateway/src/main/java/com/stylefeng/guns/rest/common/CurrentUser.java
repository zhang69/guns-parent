package com.stylefeng.guns.rest.common;

import com.stylefeng.guns.api.vo.UserModelInfo;
import org.apache.zookeeper.data.Stat;

/**
 * @AUTHOR zhangzhiyuan
 * @CREATE 2019/3/24 14:46
 */
public class CurrentUser {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setUserInfo(String userID) {
        threadLocal.set(userID);
    }
    public static String getUserId() {
        return threadLocal.get();
    }
}
