package com.stylefeng.guns.rest.modular.vo;

/**
 * Created by root on 19-3-21.
 */
public class ResponseVo<M> {
    private int status;
    private String msg;
    private M data;

    private ResponseVo() {

    }
    //todo 泛型方法回顾
    /**
     * 泛型方法是为了解决一个问题
     * 静态方法和静态代码块中不能使用T，泛型静态方法可以
     */

    /**
     *
     * @param msg 错误信息
     * @param <M> 泛型类型
     * @return 封装实例
     */
    public static<M> ResponseVo serviceFailed(String msg){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(0);
        responseVo.setMsg(msg);
        return responseVo;
    }

    public static<M> ResponseVo success(M m){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setStatus(1);
        responseVo.setData(m);
        return responseVo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }
}
