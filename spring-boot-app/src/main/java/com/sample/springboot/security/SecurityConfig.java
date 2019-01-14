package com.sample.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LocalAuthenticationEntryPoint authEntryPoint;
	
	// @formatter:off
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info(">>>(LOCAL Config) Enter configureGlobal");
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);

		// add user based on the Roles for local tests
		UserDetails master = userBuilder.username("admin").password("admin").roles("ADMIN").build();
		UserDetails user = userBuilder.username("user").password("user").roles("USER").build();

		// configure the user for authentication
		auth.inMemoryAuthentication().withUser(master).withUser(user);
	}
	// @formatter:on		
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info(">>>(LOCAL Config) Enter configure ApiWebSecurityConfigurationAdapter");
		
		http
			.csrf().disable() 
			.requestMatchers()
		    	.antMatchers("/**")
		    	.and()
		    .authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
		    	.and().httpBasic().authenticationEntryPoint(authEntryPoint);			
	}	
}
