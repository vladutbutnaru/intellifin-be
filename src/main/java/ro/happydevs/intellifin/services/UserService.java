package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.models.dto.business.user.UserConfirmationDTO;
import ro.happydevs.intellifin.models.reporting.LogLine;
import ro.happydevs.intellifin.repositories.UserRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.Date;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    IntelliLogger intelliLogger;

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
            intelliLogger.createLog(new LogLine(loggedInUser.getId(), "[LOGGED IN]"));
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

        User u = getUserForEmail(user.getEmail());

        if (getUserForEmail(user.getEmail()) == null) {
            user = userRepository.save(user);

           // notificationService.createNotificationForNewUser(user.getId());


            return true;
        }
        return false;
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
    public User getUserForEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * Confirms initial configuration for a newly registered user
     *
     * @param token
     * @param userConfirmationDTO
     */
    public void confirmInitialConfig(String token, UserConfirmationDTO userConfirmationDTO) {
        User user = getUserForToken(token);
        user.setSmoker(userConfirmationDTO.isSmoker());
        user.setPhoneNumber(userConfirmationDTO.getPhoneNumber());
        user.setMarried(userConfirmationDTO.isMarried());
        user.setFirstName(userConfirmationDTO.getFirstName());
        user.setLastName(userConfirmationDTO.getLastName());
        user.setEmployed(userConfirmationDTO.isEmployed());
        user.setEmployerName(userConfirmationDTO.getEmployerName());
        user.setStudies(userConfirmationDTO.isHasStudies());
        user.setFacultyName(userConfirmationDTO.getFacultyName());
        user.setNumberOfCigarettesDaily(userConfirmationDTO.getNumberOfCigarettesDaily());
        user.setHeight(userConfirmationDTO.getHeight());
        user.setWeight(userConfirmationDTO.getWeight());
        user.setSubscriptionType(userConfirmationDTO.getSubscriptionType());
        user.setPaidForSubscription(false);
        user.setAccountConfigured(true);
        user.setCreatedAt(new Date());
        if (userConfirmationDTO.getSubscriptionType() != 1)
            user.setPaidForSubscription(true);


        userRepository.save(user);


    }
}
