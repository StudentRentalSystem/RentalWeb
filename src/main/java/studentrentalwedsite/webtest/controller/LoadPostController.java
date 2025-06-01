package studentrentalwedsite.webtest.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studentrentalwedsite.webtest.model.RentalInfo;
import studentrentalwedsite.webtest.repository.RentalInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class LoadPostController {

    @Autowired
    private RentalInfoRepository rentalRepo;

    @GetMapping("/searchposts")
    public List<Map<String, Object>> loadPosts(@RequestParam(value = "keyword", required = false) String keyword) {
        System.out.println("🔍 searchposts 被呼叫");

        List<RentalInfo> data;
        if (keyword != null && !keyword.isBlank()) {
            data = rentalRepo.findByAddressContaining(keyword);
        } else {
            data = rentalRepo.findAll();
        }

        // 將每個 RentalInfo 轉回 Document 格式（保留 "_id": {"$oid": ...}）給前端用
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> result = new ArrayList<>();
        for (RentalInfo rental : data) {
            // 把 Java 物件轉成 Map，並加工 "_id"
            Map<String, Object> map = mapper.convertValue(rental, new TypeReference<>() {});
            Map<String, String> idWrapper = Map.of("$oid", rental.getId());
            map.put("_id", idWrapper);
            result.add(map);
        }

        return result;
    }
}
