package com.issunjh.ClassManagement.service;

import com.issunjh.ClassManagement.bean.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/28 14:25
 */
public interface AdminService {
    List<Admin> getAdminByLogin(@Param("inputName") String name,
                               @Param("userPswdForm") String password);
}
