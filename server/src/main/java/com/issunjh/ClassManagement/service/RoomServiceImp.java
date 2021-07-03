package com.issunjh.ClassManagement.service;

import com.issunjh.ClassManagement.bean.Room;
import com.issunjh.ClassManagement.dao.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 11:38
 */
@Service
public class RoomServiceImp implements RoomService{

    @Autowired
    RoomMapper roomMapper;
    @Override
    public List<Room> getAllRooms() {
        return roomMapper.getAllRooms();
    }
}
