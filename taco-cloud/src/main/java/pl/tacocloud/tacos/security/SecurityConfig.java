package pl.tacocloud.tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
        http.csrf().disable().authorizeHttpRequests((authorize)
                -> authorize.requestMatchers("/register/**")
                .permitAll())

                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/design")
                .permitAll()
                .and().authorizeHttpRequests().
                    requestMatchers("/design","/orders").authenticated().
                    requestMatchers("/design","/orders").hasRole("ROLE_USER")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf().disable().authorizeHttpRequests()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .headers()
                    .frameOptions()
                    .sameOrigin();;
        return http.build();
//        http
//                .csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/design","/orders")
//                .hasRole("ROLE_USER")
//                .requestMatchers("/","/**").permitAll()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                .and()
//                    .logout()
//                    .logoutSuccessUrl("/")
//                .and()
//                .csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/h2-console/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                    .headers()
//                    .frameOptions()
//                    .sameOrigin();

    }
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }*/
}
