package com.springboot.security.expert_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private SenhaMasterAuthenticationProvider senhaMasterAuthenticationProvider;
    @Autowired
    private CustomFilter customFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //TODO SecurityFilterChain -> é responsável pelas as configurações de autenticação.
        return http
                .csrf(AbstractHttpConfigurer::disable) //TODO: csrf -> para quando estamos com uma aplicação web
                .authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers("/public").permitAll();
                    customizer.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authenticationProvider(senhaMasterAuthenticationProvider)
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //TODO: UserDetailsService -> para definir as configurações para os usuários.
        UserDetails commonUser = User.builder()
                .username("naty2323")
                .password(passwordEncoder().encode("123456"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("alexander1914")
                .password(passwordEncoder().encode("000777"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(commonUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //TODO: PasswordEncoder -> é responsável pela criptografia da senha dos usuários.
        return new BCryptPasswordEncoder();
    }

    //TODO: GrantedAuthorityDefaults -> para definir o prefix de uma forma simplificada,
    // sem passar na classe SimpleGrantedAuthority na classe SenhaMasterAuthenticationProvider.

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}
