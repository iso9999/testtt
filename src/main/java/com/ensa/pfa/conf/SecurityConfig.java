package com.ensa.pfa.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
	@Autowired
	private DataSource datasource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("admin").password("1234").roles("USER","ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER");*/
		auth.jdbcAuthentication()
			.dataSource(datasource)
			.usersByUsernameQuery("select username as principale, password as credentials, active from users where username=?")
			.authoritiesByUsernameQuery("select users_username as principale ,roles_role as role from users_roles where users_username = ?")//spring va connu le role d'utiisateur
			.passwordEncoder(passwordEncoder())
			.rolePrefix("ROLE_");
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login")
        .defaultSuccessUrl("/", true) 
        .and()
        .httpBasic();
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/CLIENT/*").hasRole("CLIENT");
		http.authorizeRequests().antMatchers("/ADMIN/*").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/FREELANCER/*").hasRole("FREELANCER");
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/getAllUsers");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
		return encoder;
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	
	
}
