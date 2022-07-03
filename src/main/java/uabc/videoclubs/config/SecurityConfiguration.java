package uabc.videoclubs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import uabc.videoclubs.services.UserService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfiguration {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/css/*", "/js/*", "/assets/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll()
		.and()
		.logout().permitAll();
		
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}