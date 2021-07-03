package com.issunjh.ClassManagement.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/30 9:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassUse {
    private int id;
    private int rid;// 关联的教室的编号
    private String roomName;// 关联的教室名称
    private int tid;// 老师的编号
    private  String teacherName;// 关联的老师姓名
    private Time beginTime; // 起始时间
    private Time endTime; // 结束时间
    private List<Date> dayTime; // 日期的集合
    private Date day;// 日期
    private int state; // 教室的状态：0空闲，1锁定，2使用

    public int setState(int state) {
        this.state = state;
        return state;
    }
}
