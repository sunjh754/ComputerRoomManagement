package com.issunjh.ClassManagement.service;

import com.issunjh.ClassManagement.bean.Schedule;
import com.issunjh.ClassManagement.dao.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 19:11
 */
@Service
public class ScheduleServiceImp implements ScheduleService{
    @Autowired
    ScheduleMapper scheduleMapper;
    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleMapper.getAllSchedules();
    }
}
