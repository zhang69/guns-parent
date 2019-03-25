package com.stylefeng.guns.api;

import com.stylefeng.guns.api.vo.UserModel;
import com.stylefeng.guns.api.vo.UserModelInfo;

/**
 * Created by root on 19-3-20.
 */
public interface UserAPI {
    public int login(String userName, String passWord);

    public boolean register(UserModel userModel);

    public boolean checkUserName(String userName);

    public UserModelInfo getModelInfo(String uuid);

    public UserModelInfo updateUserModel(UserModelInfo userModelInfo);

}
