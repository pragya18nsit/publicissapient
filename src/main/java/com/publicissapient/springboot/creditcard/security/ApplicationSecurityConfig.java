package com.publicissapient.springboot.creditcard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.cert.Extension;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user.name}")
    private String userName;

    @Value("${spring.security.user.password}")
    private String userPassword;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication)
            throws Exception
    {
        authentication.inMemoryAuthentication()
                .withUser(userName)
                .password(passwordEncoder().encode(userPassword))
                .authorities("ROLE_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}