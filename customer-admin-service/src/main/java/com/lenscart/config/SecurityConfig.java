package com.lenscart.config;

import com.lenscart.security.JwtAuthenticationFilter;
import com.lenscart.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final CustomUserDetailsService userDetailsService;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, CustomUserDetailsService userDetailsService) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/customers/products/all").permitAll()
						.requestMatchers("/api/customers/register").permitAll().requestMatchers("/api/admins/**")
						.hasRole("ADMIN").requestMatchers("/api/customers/**").hasRole("CUSTOMER")
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
						.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider()) // ✅ Register authenticationProvider
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //intercept the request 

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean 
	public DaoAuthenticationProvider authenticationProvider() { //checked securely before granting access! 
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 //Fetches user details from the database.
		provider.setUserDetailsService(userDetailsService);
		//Ensures passwords are securely hashed & compared.
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}
