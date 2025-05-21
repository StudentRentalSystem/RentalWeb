package studentrentalwedsite.webtest.config;

import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/loginpage**", "/login", "/signup", "/logout", "/", "/collection**",
                                         "/index**", "/collect/**","/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")                     // 處理登出請求
                        .logoutSuccessUrl("/loginpage?logout")    // 登出成功導向
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}


