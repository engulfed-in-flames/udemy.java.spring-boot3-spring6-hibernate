package com.udemy.directory.employee.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsManager userDetailManager(DataSource dataSource) {
		
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		
		manager.setUsersByUsernameQuery(
				"select user_id, pw, active from members where user_id=?"
				);
		
		manager.setAuthoritiesByUsernameQuery(
				"select user_id, role from roles where user_id=?"
				);
		
		return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer ->
						configurer
							.requestMatchers("/").hasRole("EMPLOYEE")
							.requestMatchers("/managers/**").hasRole("MANAGER")
							.requestMatchers("/admins/**").hasRole("ADMIN")
							.anyRequest().authenticated()
				).formLogin(form->
						form
						.loginPage("/showLoginForm")
						.loginProcessingUrl("/authenticate")
						.permitAll()
				).logout(
						logout -> logout.permitAll()
				).exceptionHandling(configurer ->
						configurer.accessDeniedPage("/access-denied")
				);
		
		return http.build();
	}
}
