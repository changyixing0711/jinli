package com.xinzhi.admin.config.security;

import com.xinzhi.admin.pojo.User;
import com.xinzhi.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JxcAuthenticationSuccessHandler jxcAuthenticationSuccessHandler;

    @Autowired
    private JxcAuthenticationFailedHandle jxcAuthenticationFailedHandle;

    @Resource
    private IUserService iUserService;

    @Resource
    private JxcLogouSuccessHandler logouSuccessHandler;
    /**
     * 放行静态资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/css/**","/error/**","/images/**","/js/**","/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable()
                //允许iframe 页面嵌套
        .headers().frameOptions().disable()
                .and()
                .formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .successHandler(jxcAuthenticationSuccessHandler)
                .failureHandler(jxcAuthenticationFailedHandle)
                .and()
                .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logouSuccessHandler)
                .and()
                .authorizeRequests().antMatchers("/index","/login","/image").permitAll()
                .anyRequest().authenticated();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User userDetails = iUserService.findUserByUserName(username);
                return userDetails;
            }
        };
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
    }
}
