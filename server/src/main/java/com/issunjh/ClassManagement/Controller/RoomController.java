package com.issunjh.ClassManagement.Controller;

import com.issunjh.ClassManagement.bean.Msg;
import com.issunjh.ClassManagement.bean.Room;
import com.issunjh.ClassManagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 11:33
 */
@RestController
@RequestMapping(value = "/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/allRoom",method = RequestMethod.GET)
    public Msg getALLBlogs(){
        List<Room> rooms=roomService.getAllRooms();
        return Msg.success().data("room",rooms);
    }
}
