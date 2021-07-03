package com.issunjh.ClassManagement.Controller;

import com.issunjh.ClassManagement.bean.Msg;
import com.issunjh.ClassManagement.bean.Room;
import com.issunjh.ClassManagement.bean.Schedule;
import com.issunjh.ClassManagement.service.RoomService;
import com.issunjh.ClassManagement.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 16:39
 */
@RestController
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/schedule",method = RequestMethod.GET)
    public Msg getALLSchedules(){
        List<Schedule> schedules=scheduleService.getAllSchedules();
        return Msg.success().data("schedule",schedules);
    }
}
