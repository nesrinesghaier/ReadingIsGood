package com.getir.readingisgood.config;


import com.getir.readingisgood.security.auth.AuthEntryPointJwt;
import com.getir.readingisgood.security.auth.AuthTokenFilter;
import com.getir.readingisgood.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private AuthEntryPointJwt authEntryPointJwt;


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();

    }

    @Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/api/auth/**").permitAll().and()
//                .authorizeRequests().antMatchers("/test/**").permitAll()
////                .antMatchers("/api/test/**").permitAll()
//                .anyRequest().authenticated();
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
    }
}
