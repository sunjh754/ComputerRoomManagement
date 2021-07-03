package com.issunjh.ClassManagement.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 通用的返回类
 * @Author Sunjh
 * @Date 2021/4/22 19:25
 */
@Data
public class Msg {
    //状态码 200-成功 100-失败
    private int code;
    //提示信息
    private String msg;
    // 是否成功
//    private Boolean success;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    //用户要返回给浏览器的数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理成功！");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理失败！");
        return result;
    }


//    public Msg success(Boolean success){
//        this.setSuccess(success);
//        return this;
//    }
    public Msg msg(String msg){
        this.setMsg(msg);
        return this;
    }
    public Msg code(Integer code) {
        this.setCode(code);
        return this;
    }
    public Msg data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public Msg data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}

