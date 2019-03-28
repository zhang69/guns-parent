package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.persistence.dao.MoocBannerTMapper;
import com.stylefeng.guns.rest.persistence.dao.MoocFilmInfoTMapper;
import com.stylefeng.guns.rest.persistence.dao.MoocFilmTMapper;
import com.stylefeng.guns.rest.persistence.model.MoocBannerT;
import com.stylefeng.guns.rest.persistence.model.MoocFilmInfoT;
import com.stylefeng.guns.rest.persistence.model.MoocFilmT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19-3-28.
 */
@Component
@Service(interfaceClass = FilmServiceApi.class)
public class FilmServiceImpl implements FilmServiceApi {

    @Autowired
    MoocBannerTMapper moocBannerTMapper;

    @Autowired
    MoocFilmInfoTMapper moocFilmInfoTMapper;

    @Autowired
    MoocFilmTMapper moocFilmTMapper;

    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> bannerVOS = new ArrayList<>();
        //参数为空是搜索全部
        List<MoocBannerT> moocBannerTS = moocBannerTMapper.selectList(null);
        for (MoocBannerT moocBannerT : moocBannerTS) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(String.valueOf(moocBannerT.getUuid()));
            bannerVO.setBannerUrl(moocBannerT.getBannerUrl());
            bannerVO.setBannerAddress(moocBannerT.getBannerAddress());
            bannerVOS.add(bannerVO);
        }
        return bannerVOS;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        //热映条件
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper();
        entityWrapper.eq("film_status","2");
        if (isLimit) {
            Page<MoocFilmT> page = new Page<>(1, nums);
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, entityWrapper);
            List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
            filmVO.setFilmInfo(filmInfos);
            filmVO.setFilmNum(filmInfos.size());
        }else {

        }
        return filmVO;
    }

    private List<FilmInfo> getFilmInfos(List<MoocFilmT> moocFilms){
        List<FilmInfo> filmInfos = new ArrayList<>();
        for(MoocFilmT moocFilmT : moocFilms){
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setScore(moocFilmT.getFilmScore());
            filmInfo.setImgAddress(moocFilmT.getImgAddress());
            filmInfo.setFilmType(moocFilmT.getFilmType());
            filmInfo.setFilmScore(moocFilmT.getFilmScore());
            filmInfo.setFilmName(moocFilmT.getFilmName());
            filmInfo.setFilmId(moocFilmT.getUuid()+"");
            filmInfo.setExpectNum(moocFilmT.getFilmPresalenum());
            filmInfo.setBoxNum(moocFilmT.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(moocFilmT.getFilmTime()));

            // 将转换的对象放入结果集
            filmInfos.add(filmInfo);
        }

        return filmInfos;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
        return null;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        return null;
    }

    @Override
    public List<FilmInfo> getTop100() {
        return null;
    }
}
