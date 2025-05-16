package studentrentalwedsite.webtest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import studentrentalwedsite.webtest.RentalDataStruct;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {


    @RequestMapping("/MainPage")
    public String MainPage( Model model) {

        System.out.println("MainPage");

        List<RentalDataStruct> Posts= new ArrayList<>();
        Posts.add(new RentalDataStruct());
        Posts.add(new RentalDataStruct());
        Posts.add(new RentalDataStruct());
        model.addAttribute("posts", Posts);


        return "MainPage";
    }

}
