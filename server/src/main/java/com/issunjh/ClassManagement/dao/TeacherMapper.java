package com.issunjh.ClassManagement.dao;

import com.issunjh.ClassManagement.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 14:23
 */
@Mapper
public interface TeacherMapper {
    List<Teacher> getTeacherByLogin(@Param("inputName") String inputName,
                                    @Param("inputPassWord") String inputPassWord);
}
