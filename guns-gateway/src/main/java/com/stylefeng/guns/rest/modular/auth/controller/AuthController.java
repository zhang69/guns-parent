package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.UserAPI;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {
    //zzy 取消啓動檢查
    @Reference(interfaceClass = UserAPI.class,check = false)
    private UserAPI userAPI;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //todo jwt源码
//    @Resource(name = "simpleValidator")
//    private IReqValidator reqValidator;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseVo createAuthenticationToken(AuthRequest authRequest) {
        //这个地方用userId的目的是为了生成token
        int useId = userAPI.login(authRequest.getUserName(), authRequest.getPassword());

        if (useId != 0) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(String.valueOf(useId), randomKey);
            return ResponseVo.success(new AuthResponse(token, randomKey));
        } else {
            return ResponseVo.serviceFailed("用户不存在");
        }
    }

    public static void main(String[] args) {
        /**
         * 1 String.valueOf() 对于 null 返回 "null",不会出现空指针
         */
        Integer i = null;
        String s = null;
        String m = String.valueOf(s);
        System.out.println(m.toString());

        /**
         * 2 StringUtils------apache下面
         */
        boolean b1 = StringUtils.isEmpty("");
        boolean b2 = StringUtils.isEmpty(null);
        System.out.println(b1);
        System.out.println(b2);

        /**
         * todo 3考虑guava的使用
         */

    }
}
