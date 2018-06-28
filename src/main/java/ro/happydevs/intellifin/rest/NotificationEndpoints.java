package ro.happydevs.intellifin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.happydevs.intellifin.services.NotificationService;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/notifications")
public class NotificationEndpoints {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/list")
    public ResponseEntity<?> getAllForUser(@RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            return ResponseEntity.ok(notificationService.getNotificationsForUser(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }
}
