package studentrentalwedsite.webtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class JsonTransformer {

    /**
     * 讀取指定路徑下的 JSON 陣列並轉為 List<RentalDataJsonStruct>
     * @param resourcePath resources 資料夾底下的路徑，例如 "TestData/PostData.json"
     * @return List<RentalDataJsonStruct> 或 null（讀不到檔案時）
     */
    public List<RentalDataJsonStruct> JsonTransform(String resourcePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClassLoader classLoader = getClass().getClassLoader();
            try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath)) {

                if (inputStream == null) {
                    System.out.println("找不到檔案: " + resourcePath);
                    return null;
                }

                return mapper.readValue(inputStream, new TypeReference<List<RentalDataJsonStruct>>() {});
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
