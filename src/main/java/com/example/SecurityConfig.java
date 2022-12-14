package com.example;

import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.controller.JWT.jwt.AuthEntryPoinJwt;
import com.example.controller.JWT.jwt.AuthTokenFilter;
import com.example.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.entity.Account;
import com.example.service.AccountService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig {
  //		extends WebSecurityConfigurerAdapter {
  @Autowired
  BCryptPasswordEncoder pe;

  @Autowired
  UserDetailServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPoinJwt unauthorizedHandler;


  // M?? h??a m???t kh???u
  @Bean
  public static BCryptPasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(getPasswordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  // Cung c???p ngu???n d??? li???u ????ng nh???p
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(username -> {
//			try {
//				Account user = accountService.findById(username);
//				String password = pe.encode(user.getPassword());
//				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId())
//						.collect(Collectors.toList()).toArray(new String[0]);
//				System.out.println(User.withUsername(username).password(password).roles(roles).build());
//				return User.withUsername(username).password(password).roles(roles).build();
//			} catch (NoSuchElementException e) {
//				throw new UsernameNotFoundException(username + "not found!");
//			}
//		});
//	}
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
            .authorizeRequests().antMatchers("/admin/**").hasAnyRole( "ROLE_ADMIN")
            .antMatchers("/home/**").permitAll()
//				.antMatchers("/js/**","/css/**","/images/**","assets/images/**","hinhanhcustom/**","fints.quicksand/**","plugins/**").permitAll()
            .antMatchers("/order/**").authenticated()


//				.antMatchers("/rest/authorities","/rest/accounts/create").hasRole("DIRE").anyRequest().permitAll()
            .antMatchers("/security/**").permitAll();
//    http.formLogin()
//        // Khi g???p ?????a ch??? URL n??y
//        .loginPage("/security/login/form")
//        // ????a v??o ?????a ch??? n??y ????? x??? l?? URL
//        .loginProcessingUrl("/security/login")
//        // False ????? tr??? l???i trang ng?????i d??ng v???a y??u c???u
//        // True s??? ti???p t???c quay tr??? l???i ?????a ch??? URL c???a trang ????ng nh???p
//        .defaultSuccessUrl("/security/home", true).failureUrl("/security/login/error");
    http.authenticationProvider(authenticationProvider());
//    http.rememberMe().tokenValiditySeconds(86400);
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");
    return http.build();
  }
//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//    auth.inMemoryAuthentication()
//        .withUser("user").password("password").roles("USER")
//        .and()
//        .withUser("admin").password("password").roles("ADMIN");
//  }

  // Cho ph??p truy xu???t REST API t??? b??n ngo??i (domain kh??c)
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//	}

  // Ph??n quy???n s??? d???ng v?? h??nh th???c ????ng nh???p
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// CSRF,CORS
//		http.csrf().disable();
//
////		Ph??n quy???n
//		http.authorizeRequests()
//		.antMatchers("/order/**").authenticated()
//		.antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
//		.antMatchers("/rest/authorities","/rest/accounts/create").hasRole("DIRE").anyRequest().permitAll();
//
//		http.formLogin()
//				// Khi g???p ?????a ch??? URL n??y
//				.loginPage("/security/login/form")
//				// ????a v??o ?????a ch??? n??y ????? x??? l?? URL
//				.loginProcessingUrl("/security/login")
//				// False ????? tr??? l???i trang ng?????i d??ng v???a y??u c???u
//				// True s??? ti???p t???c quay tr??? l???i ?????a ch??? URL c???a trang ????ng nh???p
//				.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");
//
//		// Check remember
//		http.rememberMe().tokenValiditySeconds(86400);
//
//		http.exceptionHandling().accessDeniedPage("/security/unauthoried");// error
//
//		// ????ng xu???t
//
//	}
}