package studentrentalwedsite.webtest.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CollectionPageController {

    @RequestMapping("/collection")
    public String CollectionPage(@AuthenticationPrincipal OAuth2User oAuth2User, Model model, HttpSession session) {
        System.out.println("CollectionPage");

        String username = null;
        String picture = null;

        if (oAuth2User != null) {
            username = oAuth2User.getAttribute("name");
            picture = oAuth2User.getAttribute("picture");

            model.addAttribute("UserName", username);
            model.addAttribute("UserPicture", picture);
        } else {
            Object userObj = session.getAttribute("CurrentUser");
            if (userObj != null) {
                username = userObj.toString();
            }
            if (username == null) {
                return "redirect:/loginpage";
            }
            model.addAttribute("UserName", username);
            model.addAttribute("UserPicture", null); // 沒圖片
        }

        return "CollectionPage";
    }
}
