package com.master.formation.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.master.formation.DAO.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService userDetailsService;

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/index").permitAll()
		.antMatchers("/signup").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/formation").permitAll()
		.antMatchers("/myformations","/**/formations").hasAnyRole("formateur","beneficier")
		.antMatchers("/css/**","/js/**","/images/**","/scss/**","/fonts/**").permitAll()
		.antMatchers("/formation/add","/formation/**/update","/formation/**/delete").hasRole("formateur")
		.antMatchers("/formateur/**").hasRole("formateur")
		.antMatchers("/**/myFormation").hasAnyRole("beneficier","BENEFICIER")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/index")
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/index")
		.and()
		.oauth2Login().loginPage("/login").defaultSuccessUrl("/index");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
