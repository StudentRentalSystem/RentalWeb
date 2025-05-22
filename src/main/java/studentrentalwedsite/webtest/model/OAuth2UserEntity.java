package studentrentalwedsite.webtest.model;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection="users")
public class OAuth2UserEntity {
    @Id
    private String id;

    private String email;
    private String username;
    private String picture;

    public OAuth2UserEntity(String email, String username, String picture) {
        this.email = email;
        this.username = username;
        this.picture = picture;
    }
}
