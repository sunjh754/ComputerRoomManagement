package com.issunjh.ClassManagement.dao;

import com.issunjh.ClassManagement.bean.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 11:44
 */
@Mapper
public interface RoomMapper {
    List<Room> getAllRooms();
}
