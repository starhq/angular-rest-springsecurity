package net.dontdrinkandroot.example;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.NewsRepository;
import net.dontdrinkandroot.example.angularrestspringsecurity.dao.UserRepository;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import net.dontdrinkandroot.example.angularrestspringsecurity.rest.AuthenticationTokenProcessingFilter;
import net.dontdrinkandroot.example.angularrestspringsecurity.rest.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Date;

/**
 * Created by win7 on 2016/10/3.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;
    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;
    @Autowired
    private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(standardPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(authenticationTokenProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/user/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/news/**").hasAuthority("user")
                .antMatchers(HttpMethod.PUT, "/news/**").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/news/**").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE, "/news/**").hasAuthority("admin");

//                .and()
//                .formLogin()
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                .and()
//                .logout();
    }

    @Bean
    public PasswordEncoder standardPasswordEncoder() {
        return new StandardPasswordEncoder("ThisIsASecretSoChangeMe");
    }

}
