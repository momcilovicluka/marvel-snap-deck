
package com.example.marvelSnapDeck.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@SuppressWarnings("deprecation")

@Configuration

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired

	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "**", "css/**").permitAll()
				.antMatchers("/admin/**").access("hasRole('ADMIN')").antMatchers("/user/**")
				.access("hasRole('KORISNIK')").and().formLogin().loginPage("/auth/loginPage").permitAll()
				.defaultSuccessUrl("/auth/index").usernameParameter("username").passwordParameter("password")
				.loginProcessingUrl("/login").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()
				.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/").and().csrf();
	}
}