package ro.happydevs.intellifin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.dto.GenericMessageDTO;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getAllForUser(@RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            return ResponseEntity.ok(notificationService.getNotificationsForUser(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    @RequestMapping(value = "/viewed", method = RequestMethod.GET)
    public ResponseEntity<?> getAllForUser(@RequestHeader("Authentication") String token,
                                           @RequestParam("id") Long id) {
        if (tokenService.verifyToken(token)) {
            notificationService.markNotificationAsRead(id);
            return ResponseEntity.ok(new GenericMessageDTO(1, "Marked as read", true));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }
}
