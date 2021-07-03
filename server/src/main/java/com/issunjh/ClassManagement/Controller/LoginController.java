package com.issunjh.ClassManagement.Controller;

import com.issunjh.ClassManagement.bean.Admin;
import com.issunjh.ClassManagement.bean.Msg;
import com.issunjh.ClassManagement.bean.Teacher;
import com.issunjh.ClassManagement.service.AdminService;
import com.issunjh.ClassManagement.service.TeacherService;
import com.issunjh.ClassManagement.utils.CrowdConstant;
import com.issunjh.ClassManagement.utils.CrowdUtil;
import com.issunjh.ClassManagement.utils.JwtUtils;
import com.issunjh.ClassManagement.utils.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/28 14:02
 */
@RestController
public class LoginController {
    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public Msg Login(
            @RequestParam("inputName")String inputName,
            @RequestParam("inputPassWord")String inputPassWord,
            @RequestParam("type")int type
    ){
        //将传来的表单数据用md5加密
        String userPswdForm = CrowdUtil.md5(inputPassWord);
        System.out.println("用户名："+inputName+"\n 用户密码："+userPswdForm+"\n button的id值"+type);
        if (type==2){
            List<Admin> admins = adminService.getAdminByLogin(inputName,userPswdForm);
            if (admins==null||admins.size()==0) {
                throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
            }
            else {
                Admin admin = admins.get(0);
                // 生成Token
                admin.setToken(JwtUtils.getJwtToken(admin.getName(), admin.getPassword()));
                return Msg.success().data("admin", admin);
            }
        }
        else if(type==1) {
            List<Teacher> teachers = teacherService.getTeacherByLogin(inputName,userPswdForm);
            if (teachers==null||teachers.size()==0) {
                throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
            }
            else {
                Teacher teacher = teachers.get(0);
                // 生成Token
                teacher.setToken(JwtUtils.getJwtToken(teacher.getTeacherName(), teacher.getPassword()));
                return Msg.success().data("teacher", teacher);
            }
        }else{return Msg.fail();}
    }

}
