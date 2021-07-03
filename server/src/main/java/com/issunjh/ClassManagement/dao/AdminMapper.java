package com.issunjh.ClassManagement.dao;

import com.issunjh.ClassManagement.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/28 14:28
 */
@Mapper
public interface AdminMapper {
    List<Admin> getAdminByLogin(@Param("inputName") String inputName,
                               @Param("inputPassWord") String inputPassWord);
}
