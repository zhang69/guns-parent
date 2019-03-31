package com.stylefeng.guns.rest.modular.vo;

import lombok.Data;

@Data
public class ResponseVo<M> {
    // 返回状态【0-成功，1-业务失败，999-表示系统异常】
    private int status;
    // 返回信息
    private String msg;
    // 返回数据实体;
    private M data;
    // 图片前缀
    private String imgPre;

    // 分页使用
    private int nowPage;
    private int totalPage;

    private ResponseVo(){}

    public static<M> ResponseVo success(int nowPage,int totalPage,String imgPre,M m){
        ResponseVo ResponseVo = new ResponseVo();
        ResponseVo.setStatus(0);
        ResponseVo.setData(m);
        ResponseVo.setImgPre(imgPre);
        ResponseVo.setTotalPage(totalPage);
        ResponseVo.setNowPage(nowPage);

        return ResponseVo;
    }

    public static<M> ResponseVo success(String imgPre,M m){
        ResponseVo ResponseVo = new ResponseVo();
        ResponseVo.setStatus(0);
        ResponseVo.setData(m);
        ResponseVo.setImgPre(imgPre);

        return ResponseVo;
    }

    public static<M> ResponseVo success(M m){
        ResponseVo ResponseVo = new ResponseVo();
        ResponseVo.setStatus(0);
        ResponseVo.setData(m);

        return ResponseVo;
    }

    public static<M> ResponseVo success(String msg){
        ResponseVo ResponseVo = new ResponseVo();
        ResponseVo.setStatus(0);
        ResponseVo.setMsg(msg);

        return ResponseVo;
    }

    public static<M> ResponseVo serviceFail(String msg){
        ResponseVo ResponseVo = new ResponseVo();
        ResponseVo.setStatus(1);
        ResponseVo.setMsg(msg);

        return ResponseVo;
    }

    public static<M> ResponseVo appFail(String msg){
        ResponseVo ResponseVo = new ResponseVo();
        ResponseVo.setStatus(999);
        ResponseVo.setMsg(msg);

        return ResponseVo;
    }

}
