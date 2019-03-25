package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.UserAPI;
import com.stylefeng.guns.api.vo.UserModel;
import com.stylefeng.guns.api.vo.UserModelInfo;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.persistence.model.MoocUserT;
import com.stylefeng.guns.rest.persistence.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by root on 19-3-20.
 */
@Component
@Service(interfaceClass = UserAPI.class)
public class UserAPImp implements UserAPI {

    @Autowired
    MoocUserTMapper moocUserTMapper;
    /**
     * 登陆
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public int login(String userName, String passWord) {
        //根据用户名查密码，比对加密后的密码
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userName);
        MoocUserT moocUser = moocUserTMapper.selectOne(moocUserT);
        String userPwd = moocUser.getUserPwd();
        if (MD5Util.encrypt(passWord).equals(userPwd)) {
            return 1;
        }
        return 0;
    }

    /**
     * 注册用户信息
     * @param userModel
     * @return
     */
    @Override
    public boolean register(UserModel userModel) {
        MoocUserT user = new MoocUserT();
        user.setUserName(userModel.getUsername());
        user.setAddress(userModel.getAddress());
        user.setEmail(userModel.getEmail());
        user.setUserPhone(userModel.getPhone());
        //用密文存储，防止密码被窃取
        user.setUserPwd(MD5Util.encrypt(userModel.getPassword()));
        Integer insert = moocUserTMapper.insert(user);
        if (insert > 1) {
            return true;
        }
        return false;
    }

    /**
     * 检查用户名是否存在
     * @param userName
     * @return
     */
    @Override
    public boolean checkUserName(String userName) {
        EntityWrapper<MoocUserT> wrapper = new EntityWrapper();
        wrapper.eq("userName", userName);
        //这里用到wrapper
        Integer integer = moocUserTMapper.selectCount(wrapper);
        if (integer > 0) {
            return true;
        }
        return false;
    }

    /**
     * 得到用户信息
     * @param uuid
     * @return
     */
    @Override
    public UserModelInfo getModelInfo(String uuid) {
        UserModelInfo userModelInfo = new UserModelInfo();
        MoocUserT moocUserT = moocUserTMapper.selectById(uuid);
        BeanUtils.copyProperties(moocUserT,userModelInfo);
        userModelInfo.setBeginTime(date2Long(moocUserT.getBeginTime()));
        userModelInfo.setUpdateTime(date2Long(moocUserT.getUpdateTime()));
        return userModelInfo;
    }

    public Long date2Long(Date date) {
        return date.getTime();
    }

    public Date long2Date(Long number) {
        return new Date(number);
    }

    /**
     * 更新用户信息
     * @param userModelInfo
     * @return 更新后的值，要更新的值
     */
    @Override
    public UserModelInfo updateUserModel(UserModelInfo userModelInfo) {
        MoocUserT moocUserT = new MoocUserT();
        BeanUtils.copyProperties(userModelInfo, moocUserT);
        //额外不同类型的属性之间的转化
        moocUserT.setBeginTime(long2Date(userModelInfo.getBeginTime()));
        moocUserT.setUpdateTime(long2Date(System.currentTimeMillis()));
        Integer integer = moocUserTMapper.updateById(moocUserT);
        if (integer > 0) {
            return getModelInfo(String.valueOf(moocUserT.getUuid()));
        }
        return userModelInfo;
    }
}
