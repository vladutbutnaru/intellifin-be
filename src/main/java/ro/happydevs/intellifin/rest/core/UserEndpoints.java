package ro.happydevs.intellifin.rest.core;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.models.dto.business.user.UserConfirmationDTO;
import ro.happydevs.intellifin.models.dto.security.UserLoginDTO;
import ro.happydevs.intellifin.services.TokenService;
import ro.happydevs.intellifin.services.UserService;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/users")
public class UserEndpoints {

    private static Logger logger = LoggerFactory.getLogger(UserEndpoints.class);
    private static String LOG_CLASS = "[USER ENDPOINTS] - ";
    @Autowired
    IntelliLogger intelliLogger;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation("Logs in a user based on email and password")
    public ResponseEntity<UserLoginDTO> loginUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password
    ) {
        UserLoginDTO response = new UserLoginDTO();

        response.setToken(userService.loginUser(email, password));
        if (response.getToken() != null)
            response.setMessage("OK");
        else
            response.setMessage("Invalid");

        intelliLogger.createLog(LOG_CLASS + " Login with token: " + response.getToken() + " and message: " + response.getMessage());
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation("Registers a user based on the specific register form params")
    public ResponseEntity<UserLoginDTO> registerUser(
            @RequestBody User user
    ) {
        UserLoginDTO response = new UserLoginDTO();
        if (userService.registerUser(user))
            response.setMessage("OK");
        else
            response.setMessage("Email already exists");

        intelliLogger.createLog(LOG_CLASS + " Register with email: " + user.getEmail() + " and message: " + response.getMessage());
        return ResponseEntity.ok(response);


    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation("Returns information about the user based on the session token")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authentication") String token) {
        intelliLogger.createLog(LOG_CLASS + " User Info with token: " + token);

        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(userService.getUserForToken(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    @RequestMapping(value = "/find/email", method = RequestMethod.GET)
    @ApiOperation("Returns a user based on a given email address")
    public ResponseEntity<?> getUserByEmail(@RequestHeader("Authentication") String token,
                                            @RequestParam("email") String email) {
        intelliLogger.createLog(LOG_CLASS + " Find by email with email: " + email);

        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(userService.getUserForEmail(email));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }


    @RequestMapping(value = "/account/configure", method = RequestMethod.POST)
    @ApiOperation("Confirms a user account after the first login")
    public ResponseEntity<?> confirmAccountConfiguration(@RequestHeader("Authentication") String token,
                                                         @RequestBody UserConfirmationDTO userConfirmationDTO) {
        intelliLogger.createLog(LOG_CLASS + " Account confirmation with token: " + token);

        if (tokenService.verifyToken(token)) {
            userService.confirmInitialConfig(token, userConfirmationDTO);
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
