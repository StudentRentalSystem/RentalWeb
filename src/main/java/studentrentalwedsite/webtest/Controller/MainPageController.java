package studentrentalwedsite.webtest.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studentrentalwedsite.webtest.JsonTransformer;
import studentrentalwedsite.webtest.RentalDataJsonStruct;
import java.util.List;

@Controller
public class MainPageController {



    @RequestMapping("/MainPage")
    public String MainPage(Model model, HttpSession session) {
        System.out.println("MainPage");

        String username = session.getAttribute("CurrentUser").toString();
        model.addAttribute("UserName", username);

        // these codes for show posts
        JsonTransformer jsonTransformer = new JsonTransformer();
        List<RentalDataJsonStruct> Posts =  jsonTransformer.JsonTransform("TestData/PostData.json");
        model.addAttribute("posts", Posts);

        return "MainPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/LoginPage";
    }

}
