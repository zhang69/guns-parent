package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 19-3-28.
 */
@RestController
@RequestMapping("/film")
public class FileController {
    @RequestMapping(value = "getIndex",method = RequestMethod.GET)
    public ResponseVo getIndex() {
        //zzy lombok 自动set get
        BannerVO bannerVO = new BannerVO();

        return null;
    }
}
