package studentrentalwedsite.webtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import studentrentalwedsite.webtest.service.CustomOAuth2Service;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomOAuth2Service customOAuth2Service) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/loginpage**", "/login", "/oauth2/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2->oauth2
                        .loginPage("/loginpage")
                        .userInfoEndpoint(userInfo->userInfo
                                .userService(customOAuth2Service)
                        )
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                // Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")                     // 處理登出請求
                        .logoutSuccessUrl("/loginpage?logout")    // 登出成功導向
                        .permitAll()
                );

        return http.build();
    }

}


