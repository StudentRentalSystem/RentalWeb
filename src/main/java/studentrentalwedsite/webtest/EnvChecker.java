package studentrentalwedsite.webtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EnvChecker {

    @Value("${GOOGLE_CLIENT_ID}")
    private String clientId;

    @Value("${GOOGLE_CLIENT_SECRET}")
    private String clientSecret;

    @PostConstruct
    public void checkEnv() {
        System.out.println("✅ GOOGLE_CLIENT_ID: " + clientId);
        System.out.println("✅ GOOGLE_CLIENT_SECRET: " + clientSecret);
    }
}

