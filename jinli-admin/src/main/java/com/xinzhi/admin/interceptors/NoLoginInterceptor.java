package com.xinzhi.admin.interceptors;

import com.xinzhi.admin.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User)request.getSession().getAttribute("user");

        if (null == user){
            /**
             * 用户未登录和 session过期
             */
            response.sendRedirect("index");
            return false;
        }

        return true;
    }
}
