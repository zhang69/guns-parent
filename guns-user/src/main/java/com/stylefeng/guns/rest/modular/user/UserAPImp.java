package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.UserAPI;
import org.springframework.stereotype.Component;

/**
 * Created by root on 19-3-20.
 */
@Component
@Service(interfaceClass = UserAPI.class)
public class UserAPImp implements UserAPI {
    @Override
    public int login(String userName, String passWord) {
        System.out.println("this is userService");
        return 1;
    }
}
