package com.issunjh.ClassManagement.service;

import com.issunjh.ClassManagement.bean.Admin;
import com.issunjh.ClassManagement.bean.Teacher;
import com.issunjh.ClassManagement.dao.TeacherMapper;
import com.issunjh.ClassManagement.utils.CrowdConstant;
import com.issunjh.ClassManagement.utils.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/29 14:21
 */
@Service
public class TeacherServiceImp implements TeacherService{
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getTeacherByLogin(String inputName, String inputPassWord) {
        // 1.根据登陆账号查询User对象
        List<Teacher> list = teacherMapper.getTeacherByLogin(inputName, inputPassWord);
        // 2.判断User对象是否为null
        if (list==null||list.size()==0) {
            throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (list.size()>1){
            throw new LoginFailException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Teacher teacher=list.get(0);
        if (teacher == null) {
            // 3.如果User对象为null就抛出异常
            throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 4.如果User对象不为null就将数据库密码从User对象中取出
        String userPswDB=teacher.getPassword();
        // 5.将提交过来的表单明文密码进行加密
//        String userPswdForm = CrowdUtil.md5(inputPassWord);
        // 6.对密码进行比较
        if (!Objects.equals(userPswDB,inputPassWord)) {
            // 7.如果比较结果不一致则会抛出异常
            throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
//        // 8.如果一致则返回User对象
        return teacherMapper.getTeacherByLogin(inputName,inputPassWord);
    }
}
