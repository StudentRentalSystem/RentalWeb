package studentrentalwedsite.webtest.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/collect")
public class CollectionController {

    @PostMapping("{username}/{id}")
    public Map<String, String> collectPost(@PathVariable String username,
                                           @PathVariable String id,
                                           HttpSession session) {
        // Get UserName from Session
        String sessionCurrentUser = (String) session.getAttribute("CurrentUser");
        Map<String, String> response = new HashMap<>();

        //Check UserName Exist and same in session
        if(!sessionCurrentUser.equals(username) || sessionCurrentUser == null) {
            response.put("status", "error");
            response.put("message", "Account error or not logged in");
        }

        //Collect Handle
        System.out.println(sessionCurrentUser + " Collected Post ID: " + id);

        response.put("status", "success");
        response.put("message", "Collected success！Post ID: " + id);

        return response;
    }
}
