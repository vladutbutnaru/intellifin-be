package ro.happydevs.intellifin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private UserService userService = new UserService();
    private TokenService tokenService = new TokenService();


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

    @RequestMapping(value = "/register")
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

    @RequestMapping(value = "/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(userService.getUserForToken(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }

    @RequestMapping(value = "/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authentication") String token,
                                        @RequestParam (value = "id") int id) {
        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(userService.deleteUser(id, token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestBody User newUser,
                                           @RequestHeader("Authentication") String token) {

        if (tokenService.verifyToken(token)) {

            return ResponseEntity.ok(userService.updateUser(newUser,token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

}
