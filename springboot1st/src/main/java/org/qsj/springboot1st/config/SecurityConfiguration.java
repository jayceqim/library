package org.qsj.springboot1st.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource(){
        //数据源配置
        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/library", "root", "root");
    }

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            //以下是验证请求拦截和放行配置
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/static/**").permitAll();   //将所有的静态资源放行，一定要添加在全部请求拦截之前
                //只有具有以下角色的用户才能访问路径"/"
                auth.requestMatchers("/","books","index","searchbook").hasAnyRole("user", "admin");
                //其他所有路径必须角色为admin才能访问
                auth.anyRequest().hasRole("admin");
//                    auth.anyRequest().authenticated();    //将所有请求全部拦截，一律需要验证
            })
            //以下是表单登录相关配置
            .formLogin(conf -> {
                conf.loginPage("/login");   //将登录页设置为我们自己的登录页面
                conf.loginProcessingUrl("/dologin"); //登录表单提交的地址，可以自定义
                conf.defaultSuccessUrl("/");   //登录成功后跳转的页面
                conf.permitAll();    //将登录相关的地址放行，否则未登录的用户连登录界面都进不去
                //用户名和密码的表单字段名称，不过默认就是这个，可以不配置，除非有特殊需求
                conf.usernameParameter("username");
                conf.passwordParameter("password");
            })
            .logout(conf -> {
                conf.logoutUrl("/doLogout");   //退出登录地址，跟上面一样可自定义
                conf.logoutSuccessUrl("/login");  //退出登录成功后跳转的地址，这里设置为登录界面
                conf.permitAll();
            })
            .csrf(conf -> {
                conf.disable();   //此方法可以直接关闭全部的csrf校验，一步到位
//                    conf.ignoringRequestMatchers("/xxx/**");   //此方法可以根据情况忽略某些地址的csrf校验
            })
            .build();
}

}