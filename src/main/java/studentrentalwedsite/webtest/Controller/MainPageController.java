package studentrentalwedsite.webtest.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studentrentalwedsite.webtest.JsonTransformer;
import studentrentalwedsite.webtest.RentalDataJsonStruct;
import studentrentalwedsite.webtest.service.UserService;

import java.util.List;

@Controller
public class MainPageController {

    @RequestMapping("/index")
    public String MainPage(Model model, HttpSession session) {
        System.out.println("MainPage");

        Object userObj = session.getAttribute("CurrentUser");

        // 沒有登入就導回登入頁
        if (userObj == null) {
            return "redirect:/loginpage";
        }

        String username = userObj.toString();
        System.out.println("username:" + username);
        model.addAttribute("UserName", username);


        // these codes for show posts
        JsonTransformer jsonTransformer = new JsonTransformer();
        List<RentalDataJsonStruct> Posts =  jsonTransformer.JsonTransform("TestData/PostData.json");
        model.addAttribute("posts", Posts);


        return "MainPage";
    }



    // All controller use same logout GetMapping
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginpage";
    }

}
