package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.UserAPI;
import com.stylefeng.guns.api.vo.UserModel;
import com.stylefeng.guns.api.vo.UserModelInfo;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.persistence.model.MoocUserT;
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
        if (insert > 0) {
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
        // 根据主键查询用户信息 [MoocUserT]
        MoocUserT moocUserT = moocUserTMapper.selectById(uuid);
        // 将MoocUserT转换UserInfoModel
        UserModelInfo userInfoModel = do2UserInfo(moocUserT);
        // 返回UserInfoModel
        return userInfoModel;
    }

    private UserModelInfo do2UserInfo(MoocUserT moocUserT){
        UserModelInfo userInfoModel = new UserModelInfo();

        userInfoModel.setUuid(moocUserT.getUuid());
        userInfoModel.setHeadAddress(moocUserT.getHeadUrl());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setUpdateTime(moocUserT.getUpdateTime().getTime());
        userInfoModel.setEmail(moocUserT.getEmail());
        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState(""+moocUserT.getLifeState());
        userInfoModel.setBirthday(moocUserT.getBirthday());
        userInfoModel.setAddress(moocUserT.getAddress());
        userInfoModel.setSex(moocUserT.getUserSex());
        userInfoModel.setBeginTime(moocUserT.getBeginTime().getTime());
        userInfoModel.setBiography(moocUserT.getBiography());

        return userInfoModel;
    }

    public Long date2Long(Date date) {
        return date.getTime();
    }

    public Date long2Date(Long number) {
        return new Date(number);
    }

    /**
     * 更新用户信息
     * @param userInfoModel
     * @return 更新后的值，要更新的值
     */
    @Override
    public UserModelInfo updateUserModel(UserModelInfo userInfoModel) {
        // 将传入的参数转换为DO 【MoocUserT】
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUuid(userInfoModel.getUuid());
        moocUserT.setNickName(userInfoModel.getNickname());
        moocUserT.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        moocUserT.setBirthday(userInfoModel.getBirthday());
        moocUserT.setBiography(userInfoModel.getBiography());
        moocUserT.setBeginTime(null);
        moocUserT.setHeadUrl(userInfoModel.getHeadAddress());
        moocUserT.setEmail(userInfoModel.getEmail());
        moocUserT.setAddress(userInfoModel.getAddress());
        moocUserT.setUserPhone(userInfoModel.getPhone());
        moocUserT.setUserSex(userInfoModel.getSex());
        moocUserT.setUpdateTime(null);

        // DO存入数据库
        Integer integer = moocUserTMapper.updateById(moocUserT);
        if(integer>0){
            // 将数据从数据库中读取出来
            UserModelInfo userInfo = getModelInfo(String.valueOf(moocUserT.getUuid()));
            // 将结果返回给前端
            return userInfo;
        }else{
            return null;
        }
    }

}
