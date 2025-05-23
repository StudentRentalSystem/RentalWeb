package studentrentalwedsite.webtest.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import studentrentalwedsite.webtest.JsonTransformer;
import studentrentalwedsite.webtest.RentalDataJsonStruct;

import java.util.List;

@Controller
public class MainPageController {

    @GetMapping("/index")
    public String MainPage(@AuthenticationPrincipal OAuth2User oAuth2User, Model model, HttpSession session) {
        String username = null;
        String picture = null;


        if(oAuth2User != null) {
            username = oAuth2User.getAttribute("name");
            picture = oAuth2User.getAttribute("picture");

            System.out.println(username);
            model.addAttribute("UserName", username);
            model.addAttribute("UserPicture", picture);
        } else {
            // 2. 傳統 Session 登入判斷，只有當 Google OAuth2 未登入時使用
            Object userObj = session.getAttribute("CurrentUser");
            if (userObj != null) {
                username = userObj.toString();
            }

            // 3. 無登入資料，跳轉登入頁
            if (username == null) {
                return "redirect:/loginpage";
            }

            System.out.println("username:" + username);
            model.addAttribute("UserName", username);
            model.addAttribute("UserPicture", null);
        }

        // 讀取貼文資料不變
        JsonTransformer jsonTransformer = new JsonTransformer();
        List<RentalDataJsonStruct> Posts = jsonTransformer.JsonTransform("TestData/PostData.json");
        model.addAttribute("posts", Posts);

        return "MainPage";
    }



    // All controller use same logout GetMapping
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 傳統 Session 登出
        session.invalidate();

        // 如果你要用 Spring Security 提供的 logout，可以在 SecurityConfig 裡設置 logout URL
        // 或者這裡用 SecurityContextLogoutHandler 處理

        return "redirect:/loginpage";
    }

}
