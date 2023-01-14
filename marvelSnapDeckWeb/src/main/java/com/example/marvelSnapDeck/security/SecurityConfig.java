/*
 * package com.example.marvelSnapDeck.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * 
 * @SuppressWarnings("deprecation")
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter{
 * 
 * @Autowired
 * 
 * @Qualifier("customUserDetailsService") UserDetailsService userDetailsService;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.userDetailsService(userDetailsService).passwordEncoder(new
 * BCryptPasswordEncoder());
 * 
 * }
 * 
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests() // .antMatchers("/","/Predstave/loginPage",
 * "/Predstave/registerUser", "/Predstave/register").permitAll()
 * .antMatchers("/auth/**").permitAll() .anyRequest().authenticated() .and()
 * .formLogin() .loginPage("/auth/loginPage") .permitAll()
 * .loginProcessingUrl("/login") // .defaultSuccessUrl("/Predstave/pocetna") //
 * .failureForwardUrl("/auth/greska") .and() .csrf(); //
 * .and().exceptionHandling() // .accessDeniedPage("/access_denied"); }
 * 
 * 
 * 
 * }
 */