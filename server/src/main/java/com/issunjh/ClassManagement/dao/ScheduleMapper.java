package com.issunjh.ClassManagement.dao;

import com.issunjh.ClassManagement.bean.Schedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 19:12
 */
@Mapper
public interface ScheduleMapper {
    List<Schedule> getAllSchedules();
}
