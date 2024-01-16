package com.seals.camplanner.commons.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages="com.seals.camplanner.*")
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests((authz) -> authz
                                          .anyRequest().authenticated())
        .formLogin((loginFormCostumizer) -> loginFormCostumizer
                                            .loginProcessingUrl("/api/login")
                                            .permitAll())
        .logout((logoutCostumizer) -> logoutCostumizer
                                      .logoutUrl("/api/logout")
                                      .permitAll())
        .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                                .username("postgres")
                                .password("postgres")
                                .url("jdbc:postgresql://localhost:5432/camplanner1")
                                .build();
    }
/*
    @Bean
    public UserDetailsManager users() {
        UserDetails user = User.builder()
                               .passwordEncoder((password) -> passwordEncoder().encode(password))
                               .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource());
        users.createUser(user);
        return users;
    }
*/

}
