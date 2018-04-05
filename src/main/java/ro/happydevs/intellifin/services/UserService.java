package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.repositories.UserRepository;


public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository = new UserRepository();
    private TokenRepository tokenRepository = new TokenRepository();
    private NotificationService notificationService = new NotificationService();

    /**
     * User logins with email and password and returns the token
     *
     * @param email
     * @param password
     * @return token
     */
    public String loginUser(String email, String password) {
        int result = userRepository.loginUser(email, password);

        if (result > 0) {
            return tokenRepository.createTokenForUser(result);


        }
        return null;

    }

    public boolean registerUser(User user) {
        userRepository.create(user);

        notificationService.createNotificationForNewUser(userRepository.getAllByStringColumn("email", user.getEmail()).get(0).getId());


        return true;
    }


    public User getUserForToken(String token) {
        return tokenRepository.getUserByToken(token);


    }
}
