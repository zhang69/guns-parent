package com.stylefeng.guns.rest.modular.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.UserAPI;
import com.stylefeng.guns.api.vo.UserModel;
import com.stylefeng.guns.api.vo.UserModelInfo;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.user.UserAPImp;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 19-3-25.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(interfaceClass = UserAPImp.class)
    private UserAPI userAPI;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseVo register(UserModel userModel) {
        if (userModel.getUsername() == null || userModel.getUsername().trim().equals("")) {
            return ResponseVo.serviceFailed("用户名不能为空");
        }
        if (userModel.getPassword() == null || userModel.getPassword().trim().equals("")) {
            return ResponseVo.serviceFailed("密码不能为空");
        }
        boolean success = userAPI.register(userModel);
        if (success) {
            return ResponseVo.success("注册成功");
        }
        return ResponseVo.serviceFailed("注册失败");
    }

    @RequestMapping(value = "getInfo",method = RequestMethod.GET)
    public ResponseVo getUserInfo() {
        String userId = CurrentUser.getUserId();
        if (userId != null && !userId.trim().equals("")) {
            UserModelInfo modelInfo = userAPI.getModelInfo(userId);
            if (modelInfo != null) {
                return ResponseVo.success(modelInfo);
            } else {
                return ResponseVo.serviceFailed("用户信息不存在");
            }
        }else {
            return ResponseVo.serviceFailed("用户未登陆");
        }
    }
    @RequestMapping(value = "getInfo",method = RequestMethod.POST)
    public ResponseVo updateUserInfo(UserModelInfo userModelInfo) {
        String userId = CurrentUser.getUserId();
        if (userId != null && !userId.trim().equals("")) {
            if (userId.equals(userModelInfo.getUuid())) {
                UserModelInfo userModel = userAPI.updateUserModel(userModelInfo);
                if (userModel != null) {
                    return ResponseVo.success(userModel);
                } else {
                    return ResponseVo.serviceFailed("更新失败");
                }
            } else {
                return ResponseVo.serviceFailed("当前用户id与修改用户id不一致");
            }
        }else {
            return ResponseVo.serviceFailed("用户未登陆");
        }
    }
}
