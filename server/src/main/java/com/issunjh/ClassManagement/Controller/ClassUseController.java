package com.issunjh.ClassManagement.Controller;

import com.issunjh.ClassManagement.bean.ClassUse;
import com.issunjh.ClassManagement.bean.Msg;
import com.issunjh.ClassManagement.service.ClassUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/30 10:45
 */

@RestController
public class ClassUseController {

    @Autowired
    ClassUseService classUseService;

    @RequestMapping(value = "/setClassUse", method = RequestMethod.POST)
    public Msg setClassUse(@RequestBody ClassUse classUse) {
        Time beginTime = classUse.getBeginTime(); // 开始时间
        Time endTime = classUse.getEndTime(); // 结束时间
        int rid = classUse.getRid();
        int tid = classUse.getTid();
        System.out.println(classUse.toString());
        if (tid==0){return Msg.fail().msg("您不是老师，没有权限申请");}
        // 1. 比较两者时间的大小是否符合实际情况
        if (beginTime.compareTo(endTime) >= 0) {
            return Msg.fail().msg("您输入的时间不正确");
        } else {
            // 2. 从集合中取出日期数据
            List<Date> days = classUse.getDayTime();
            // 2.1 判断list是否为空
            if (days.isEmpty()) {
                // 2.2 为空，返回失败消息
                return Msg.fail().msg("您未输入日期");
            } else {
                for (Date dayTime : days
                ) {
                    // 3. 将此时的教室编号、日期数据和时间数据传入数据库，
                    System.out.println(dayTime);
                    // 与当前时间相比较
                    String applyTime=dayTime+" "+beginTime;
                    String currentTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    int res=applyTime.compareTo(currentTime);
                    if(res>0) {
                        System.out.println("applyTime>currentTime");
                        List<Integer> currentState = classUseService.selectClassState(rid, beginTime, endTime, dayTime);
                        for (int states : currentState) {
                            // 3.1 看是否有值且state的值为1或2
                            if ((states == 1) || (states == 2))
                                return Msg.fail().msg("抱歉！" + dayTime + " " + beginTime + "-" + endTime + " 该机房已被选择");
                        }
                        int result = classUseService.addClassUse(rid, tid, beginTime, endTime, dayTime, classUse.setState(1));
                    }
                    else { return Msg.fail().msg("您申请的日期有已过的日期");}
                }
            }
        }
        return Msg.success();
    }

    @RequestMapping(value = "/displayAll",method = RequestMethod.GET)
    public Msg displayAll(){
        List<ClassUse> classUses=classUseService.displayAll();
        return Msg.success().data("classUse",classUses);
    }

    @RequestMapping(value = "/agreeById",method = RequestMethod.PUT)
    public Msg updateById(@RequestParam("id") int id,@RequestParam("name") String name){
            ClassUse classUse=classUseService.selectStateById(id);
            int state =classUse.getState();
        System.out.println(classUse.toString());
        if (state==0){return Msg.fail().msg("此机房未被申请");}
            else if (state==2){return  Msg.fail().msg("此机房已被申请");}
            else{
                int result = classUseService.updateById(id);
                return Msg.success();
            }
    }

    @RequestMapping(value = "/refuseById",method = RequestMethod.PUT)
    public Msg refuseById(@RequestParam("id") int id,@RequestParam("name") String name){
            int result = classUseService.refuseById(id);
            return Msg.success();
    }

    @RequestMapping(value = "/displayUse",method = RequestMethod.GET)
    public Msg displayUse(){
        List<ClassUse> classUses=classUseService.displayUse();
        return Msg.success().data("classUse",classUses);
    }
}
