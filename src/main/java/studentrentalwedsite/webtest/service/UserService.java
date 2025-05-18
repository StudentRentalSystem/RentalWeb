package studentrentalwedsite.webtest.service;

import studentrentalwedsite.webtest.model.User;
import studentrentalwedsite.webtest.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUserName(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean registerUser(String username, String password) {

        // Prevent same UserName exist
        if (userRepository.findByUserName(username) != null) {
            return false; // return false for failure
        }

        // Put New User into datalist
        User newUser = new User(username, password);
        userRepository.save(newUser);
        return true; // return true for success
    }
}
