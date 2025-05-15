package studentrentalwedsite.webtest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    /**
     * <p>Some Userdata use for testing login and signup</p>
     *
     *
    */
    private static final Map<String, String> userDatabase = new HashMap<>();
    static {
        userDatabase.put("test", "test");
        userDatabase.put("admin", "admin");
        userDatabase.put("123", "123");
    }


    private boolean ValidateUser(String username, String password) {

        System.out.println("Validating");
        String storedPassword = userDatabase.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    @RequestMapping("/LoginPage")
    public String LoginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String success,
                            Model model) {

        if("1".equals(error)) {
            model.addAttribute("LoginSignupError", "帳號或密碼錯誤");
        } else if("2".equals(error)) {
            model.addAttribute("LoginSignupError", "帳號名已存在");
        } else if("3".equals(error)) {
            model.addAttribute("LoginSignupError", "兩個密碼不匹配");
        }

        if ("true".equals(success)) {
            model.addAttribute("LoginSignupSuccess", "註冊成功！請登入");
        }

        System.out.println("LoginPage");
        return "LoginSignupPage";
    }

    @PostMapping("/login")
    public String LoginAction(@RequestParam String LoginName, @RequestParam String Password, Model model) {

        if(ValidateUser(LoginName, Password)) {
            System.out.println("進入錯誤處理區塊");
            return "MainPage";
        } else {
            System.out.println("進入錯誤處理區塊");
            return "redirect:/LoginPage?error=1";
        }
    }

    @PostMapping("/signup")
    public String signupAction(@RequestParam String signupName,
                               @RequestParam String signupPassword,
                               @RequestParam String signupConfirmPassword) {

        // Account name existed
        if(userDatabase.containsKey(signupName)) {
            return "redirect:/LoginPage?error=2";
        }

        // Input Two Password not equal
        if(!signupPassword.equals(signupConfirmPassword)) {
            return "redirect:/LoginPage?error=3";
        }

        // Put new Account data to Hashmap
        userDatabase.put(signupName, signupPassword);
        return "redirect:/LoginPage?success=true";
    }
}
