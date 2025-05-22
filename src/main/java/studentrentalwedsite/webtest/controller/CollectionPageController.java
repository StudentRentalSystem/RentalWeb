package studentrentalwedsite.webtest.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CollectionPageController {

    @RequestMapping("/collection")
    public String CollectionPage(Model model, HttpSession session) {
        System.out.println("CollectionPage");

        return "CollectionPage";
    }
}
