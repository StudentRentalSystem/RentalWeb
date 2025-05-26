package studentrentalwedsite.webtest.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentrentalwedsite.webtest.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/collect")
public class CollectionController {

    private final UserService userService = new UserService();

    /*
    @GetMapping
    public ResponseEntity<?> getCollection(HttpSession session) {
        String username = getSessionUser(session);
        if (username == null) return unauthorized();

        return ResponseEntity.ok(Map.of("status", "success", "collections"));
    }
    */

    // ✅ 收藏貼文
    @PostMapping("/{postId}")
    public ResponseEntity<?> collect(@PathVariable String postId, HttpSession session) {
        String username = getSessionUser(session);
        if (username == null) return unauthorized();

        userService.addCollection(username, postId);
        return ResponseEntity.ok(Map.of("status", "success", "message", "收藏成功"));
    }

    // ✅ 取消收藏
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> uncollect(@PathVariable String postId, HttpSession session) {
        String username = getSessionUser(session);
        if (username == null) return unauthorized();

        userService.deleteCollection(username, postId);
        return ResponseEntity.ok(Map.of("status", "success", "message", "取消收藏成功"));
    }

    // 🔒 session 驗證封裝
    private String getSessionUser(HttpSession session) {
        return (String) session.getAttribute("CurrentUser");
    }

    // 🔐 未登入處理封裝
    private ResponseEntity<?> unauthorized() {
        return ResponseEntity.status(401).body(Map.of("status", "fail", "message", "尚未登入"));
    }
}
