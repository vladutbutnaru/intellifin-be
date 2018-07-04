package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.repositories.UserRepository;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    NotificationService notificationService;

    /**
     * User logins with email and password and returns the token
     *
     * @param email
     * @param password
     * @return token
     */
    public String loginUser(String email, String password) {

        User loggedInUser = userRepository.findByEmailAndPassword(email, password);
        if (loggedInUser != null) {
            return tokenService.createToken(loggedInUser.getId());
        }

        return null;

    }

    /**
     * Registers a new user and generates a default notification
     * that redirects to user configuration page
     *
     * @param user
     * @return boolean
     */
    public boolean registerUser(User user) {
        user = userRepository.save(user);

        notificationService.createNotificationForNewUser(user.getId());


        return true;
    }

    /**
     * Returns user information based on a token received
     * from front-end
     *
     * @param token
     * @return User
     */
    public User getUserForToken(String token) {
        return tokenService.getUserByToken(token);


    }



    /**
     * Returns user information based on a email received
     * from front-end
     *
     * @param email
     * @return User
     */
    public User getUserForEmail(String email){
        return userRepository.findByEmail(email);
    }
}
