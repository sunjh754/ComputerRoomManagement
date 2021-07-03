package com.issunjh.ClassManagement.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private int id;
    private String scheduleName;
    private Time beginTime;
    private Time endTime;
}
