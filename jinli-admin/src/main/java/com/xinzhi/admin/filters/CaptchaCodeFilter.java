package com.xinzhi.admin.filters;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinzhi.admin.model.CaptchaImageModel;
import com.xinzhi.admin.model.RespBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
public class CaptchaCodeFilter extends OncePerRequestFilter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //只有登录请求时才有验证码操作
        if (StringUtils.equals("/login",request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
            //校验验证码是否正确
            try {
                this.validate(new ServletWebRequest(request));
            }//捕捉成认证异常
            catch (AuthenticationException e) {
                response.setContentType("application/json;charset=Utf-8");
                response.getWriter().write(objectMapper.writeValueAsString(
                        RespBean.error("验证码错误")
                ));
                return;
            }
        }

        filterChain.doFilter(request,response);
    }

    //验证码校验方法
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();
        //获取请求中参数值
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"captchaCode");

        if (StringUtils.isEmpty(codeInRequest)){
            throw new SessionAuthenticationException("验证码不能为空");
        }
        CaptchaImageModel codeInSession = (CaptchaImageModel) session.getAttribute("captcha_key");

        if (Objects.isNull(codeInSession)){
            throw new SessionAuthenticationException("验证码不存在");
        }

        if (codeInSession.isExpried()){
            throw new SessionAuthenticationException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new SessionAuthenticationException("验证码不匹配");
        }
    }

}
