package studentrentalwedsite.webtest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studentrentalwedsite.webtest.JsonTransformer;
import studentrentalwedsite.webtest.RentalDataJsonStruct;

import java.util.List;

@RestController
public class LoadPostController {

    @GetMapping("/searchposts")
    public List<RentalDataJsonStruct> loadPosts() {
        JsonTransformer jsonTransformer = new JsonTransformer();
        System.out.println("searchposts 被呼叫");
        return jsonTransformer.JsonTransform("TestData/PostData.json");
    }
}
