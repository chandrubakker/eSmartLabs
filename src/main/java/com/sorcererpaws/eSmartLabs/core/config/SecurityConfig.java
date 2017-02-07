package com.sorcererpaws.eSmartLabs.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sorcererpaws.eSmartLabs.core.secure.CustomUserDetailsService;
import com.sorcererpaws.eSmartLabs.core.secure.handler.CustomAuthenticationFailureHandler;
import com.sorcererpaws.eSmartLabs.core.secure.handler.CustomAuthenticationSuccessHandler;
import com.sorcererpaws.eSmartLabs.core.secure.handler.CustomLogoutSuccessHandler;


@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordencoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		
		.antMatchers("/company/**").access("hasRole('ROLE_ADMIN')")
		
		.antMatchers("/labs").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/labs.json").access("hasRole('ROLE_ADMIN')")
		
		.antMatchers("/lab/**").access("hasRole('ROLE_TECHNICIAN') or hasRole('ROLE_ADMIN')")
		
		.antMatchers("/departments").access("isAuthenticated()")
		.antMatchers("/department/**").access("hasRole('ROLE_TECHNICIAN') or hasRole('ROLE_ADMIN')")
		
		.antMatchers("/technician/**").access("hasRole('ROLE_TECHNICIAN')")
		
		.antMatchers("/doctors").access("isAuthenticated()")
		.antMatchers("/doctor/**").access("hasRole('ROLE_TECHNICIAN') or hasRole('ROLE_ADMIN')")
		
		.antMatchers("/patients").access("isAuthenticated()")
		.antMatchers("/patient/**").access("hasRole('ROLE_TECHNICIAN') or hasRole('ROLE_ADMIN')")
		
		.antMatchers("/clinics").access("isAuthenticated()")
		.antMatchers("/clinic/**").access("hasRole('ROLE_TECHNICIAN') or hasRole('ROLE_ADMIN')")
		
		.antMatchers("/tests").access("isAuthenticated()")
		.antMatchers("/test/**").access("hasRole('ROLE_TECHNICIAN') or hasRole('ROLE_ADMIN')")
		
		.antMatchers("/users").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/user/**").access("isAuthenticated()")
		
		.anyRequest().permitAll()
		.and()
		.formLogin().loginPage("/login?sessionExp").loginProcessingUrl("/processLogin")
		.failureHandler(customAuthenticationFailureHandler)
		.successHandler(customAuthenticationSuccessHandler)
		.usernameParameter("email").passwordParameter("password")
		.and()
		.logout().logoutSuccessUrl("/login?logout")
		.logoutSuccessHandler(customLogoutSuccessHandler)
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied")
		.and()
		.csrf();
	}

	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordencoder(){
		return new BCryptPasswordEncoder();
	}
}
