package com.seals.camplanner.commons.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
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
        return new EmbeddedDatabaseBuilder()
               .setType(EmbeddedDatabaseType.H2)
               .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
               .build();
    }

    @Bean
    public UserDetailsManager users() {
        UserDetails user = User.builder()
                               .passwordEncoder((password) -> passwordEncoder().encode(password))
                               .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource());
        users.createUser(user);
        return users;
    }


}
