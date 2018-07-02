package ro.happydevs.intellifin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.models.dto.UserLoginDTO;
import ro.happydevs.intellifin.services.TokenService;
import ro.happydevs.intellifin.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/users")
public class UserEndpoints {

    private static Logger logger = LoggerFactory.getLogger(UserEndpoints.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    @RequestMapping(value = "/login")
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

        return ResponseEntity.ok(response);


    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserLoginDTO> registerUser(
            @RequestBody User user
    ) {
        UserLoginDTO response = new UserLoginDTO();


        if (userService.registerUser(user))
            response.setMessage("OK");
        else
            response.setMessage("Email already exists");

        return ResponseEntity.ok(response);


    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(userService.getUserForToken(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    @RequestMapping(value = "/find/email", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByEmail(@RequestHeader("Authentication") String token,
                                            @RequestParam("email") String email) {
        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(userService.getUserForEmail(email));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }
}
