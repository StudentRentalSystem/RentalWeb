package studentrentalwedsite.webtest.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import studentrentalwedsite.webtest.model.OAuth2UserEntity;
import studentrentalwedsite.webtest.repository.OAuth2UserRepository;

@Service
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private OAuth2UserRepository oAuth2UserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");
        OAuth2UserEntity oAuth2UserEntity = oAuth2UserRepository.findByEmail(email);
        if (oAuth2UserEntity == null) {
            OAuth2UserEntity user = new OAuth2UserEntity(email, name, picture);
            oAuth2UserRepository.save(user);
        }

        // add UserName to session
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            HttpSession session = request.getSession(true);
            session.setAttribute("CurrentUser", name);
        }

        return oauth2User;
    }
}
