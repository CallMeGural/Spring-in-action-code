package pl.tacocloud.tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.csrf().disable();
        /*http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register","/h2console","/").permitAll()
                        .requestMatchers("/design","/orders").hasAnyAuthority("ROLE_USER"));
        http.formLogin().loginPage("/login").successForwardUrl("/").permitAll();
        http.logout().logoutSuccessUrl("/");*/
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/design/**","/orders/**").hasAuthority("USER")
                .requestMatchers("/h2console/**","/register","/").permitAll()
                .anyRequest().authenticated()
        )
                .formLogin().loginPage("/login").permitAll().and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true) ;
        return http.build();
    }
}
