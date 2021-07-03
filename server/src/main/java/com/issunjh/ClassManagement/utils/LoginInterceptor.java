package com.issunjh.ClassManagement.utils;

import com.issunjh.ClassManagement.bean.Admin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author Sunjh
 * @Date 2021/6/8 15:56
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 1.通过request对象获取Session对象
        HttpSession session=request.getSession();
        // 2.尝试从Session域中User对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        // 3.判断User对象是否为空
        if (admin==null){
            // 4.抛出异常
            throw new LoginFailException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        return true;
    }
}
