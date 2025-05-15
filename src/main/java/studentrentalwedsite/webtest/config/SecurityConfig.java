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
                        .requestMatchers("/LoginPage**", "/css/**", "/js/**").permitAll()  // ✅ 允許直接進 LoginPage
                        .anyRequest().authenticated()  // 其他都要登入
                )
                .formLogin(form -> form
                        .loginPage("/LoginPage")  // ✅ 告訴 Spring 你的登入頁面在哪裡
                        .loginProcessingUrl("/login")  // ✅ 這是表單 action (method="post") 的路徑
                        .defaultSuccessUrl("/MainPage", true)  // 登入成功後的頁面
                        .failureUrl("/LoginPage?error=1")  // 登入失敗回來帶錯誤參數
                        .permitAll()  // ✅ 允許所有人訪問這個登入頁面
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/LoginPage?logout=true")  // 登出後導回登入頁
                )
                .csrf(csrf -> csrf.disable());  // ✅ 開發用，避免 CSRF 擋住 POST

        return http.build();
    }
}
