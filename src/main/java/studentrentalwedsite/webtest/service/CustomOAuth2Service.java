package studentrentalwedsite.webtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import studentrentalwedsite.webtest.model.OAuth2UserEntity;
import studentrentalwedsite.webtest.repository.OAuth2UserRepository;

@Service
public class CustomOAuth2Service extends DefaultOAuth2UserService {
    @Autowired
    private OAuth2UserRepository oAuth2UserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");
        OAuth2UserEntity userEntity = oAuth2UserRepository.findByEmail(email);
        if (userEntity == null) {
            userEntity = new OAuth2UserEntity(email, name, picture);
        } else {
            userEntity.setUsername(name);
            userEntity.setPicture(picture);
        }
        oAuth2UserRepository.save(userEntity);
        return oauth2User;
    }
}
