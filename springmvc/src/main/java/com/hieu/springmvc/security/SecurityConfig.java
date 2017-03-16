package com.hieu.springmvc.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.hieu.springmvc.service.SpringUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private SpringUserDetailsService springUserDetailsService;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username, password from users where username=?")
			.authoritiesByUsernameQuery("select username, role from user_roles where username=?");*/
		
		auth.userDetailsService(springUserDetailsService);
		
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/add-products-to-stock").hasRole("ADMIN")
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/about", "/login", "/signup", "/auth-signup", "/resources/**").permitAll()
				.antMatchers("/auth-add-products", "/products").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/home", true)
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			.and()
			.csrf().disable();
			
	}
}
