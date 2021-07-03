package com.issunjh.ClassManagement.service;
import com.issunjh.ClassManagement.bean.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 14:19
 */
public interface TeacherService {
    List<Teacher> getTeacherByLogin(@Param("inputName") String teacherName,
                                    @Param("userPswdForm") String password);
}
