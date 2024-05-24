package com.vip.codingclasseditor.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vip.codingclasseditor.component.BasicAuthenticationPoint;
import com.vip.codingclasseditor.model.Roles;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService; 
	@Autowired
	private BasicAuthenticationPoint basicAuthenticationPoint;
//	@Autowired
//	private PersistentTokenService persistentTokenService;
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login","/registerMaster","/verify-otp",
				"/registerStudent","/registerStudentData","/verify-otp-std").permitAll()
		.antMatchers("/random-otp-std","/addClass","/del/subject/{subId}","/del/practical/{pracId}","/del/assignment/{pracId}").hasAnyAuthority(Roles.MASTER.toString())
		.anyRequest().authenticated()
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll()
		.and().httpBasic().authenticationEntryPoint(basicAuthenticationPoint)
		.and().rememberMe().rememberMeParameter("rememeber-user")
		//.tokenRepository(persistentTokenService)
		.userDetailsService(userDetailsService);
	}
	@Bean
	public AuthenticationManager custAuthenticationManager() throws Exception{
		return authenticationManager();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
