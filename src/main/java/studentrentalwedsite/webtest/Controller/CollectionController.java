package studentrentalwedsite.webtest.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import studentrentalwedsite.webtest.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/collect")
public class CollectionController {

    UserService userService = new UserService();

    @GetMapping("/{username}")
    public Map<String, String> getCollection(@PathVariable String username,
                                             HttpSession httpSession) {
        Map<String, String> respone = new HashMap<>();

        System.out.println("get collection");
        userService.getCollection(username);

        respone.put("status", "success");
        respone.put("message", "success");

        return respone;
    }


    @PostMapping("/{username}/{id}")
    public Map<String, String> collectPost(@PathVariable String username,
                                           @PathVariable String id,
                                           HttpSession session) {

        System.out.println("collecting post");
        return handleCollectAction(username, id, session, true);
    }

    @DeleteMapping("/{username}/{id}")
    public Map<String, String> uncollect(@PathVariable String username,
                                         @PathVariable String id,
                                         HttpSession session) {

        System.out.println("uncollecting post");
        return handleCollectAction(username, id, session, false);
    }


    private Map<String, String> handleCollectAction(String username,
                                                    String postId,
                                                    HttpSession session,
                                                    boolean isCollect) {
        Map<String, String> response = new HashMap<>();
        String currentUser = (String) session.getAttribute("CurrentUser");

        if (currentUser == null || !currentUser.equals(username)) {
            response.put("status", "error");
            response.put("message", "帳號錯誤或未登入");
            return response;
        }

        // 👉 在這裡操作資料庫的收藏邏輯（你之後可以補 DB 呼叫）
        if (isCollect) {
            System.out.println(currentUser + " 收藏了貼文 " + postId);
            userService.addCollection(username, postId);
        } else {
            System.out.println(currentUser + " 取消收藏貼文 " + postId);
            userService.deleteCollection(username, postId);
        }

        response.put("status", "success");
        response.put("message", isCollect ? "收藏成功" : "取消收藏成功");
        return response;
    }
}
