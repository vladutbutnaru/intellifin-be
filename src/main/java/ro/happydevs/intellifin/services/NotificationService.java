package ro.happydevs.intellifin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.Notification;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserService userService;

    public List<Notification> getNotificationsForUser(String token) {
        User loggedInUser = userService.getUserForToken(token);
        return notificationRepository.getNotificationsForUser(loggedInUser.getId());


    }

    public void createNotificationForUser(Notification notification) {

        notificationRepository.save(notification);


    }

    public void createNotificationForNewUser(Long userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setViewed(false);
        notification.setText("Completeaza-ti contul cu informatiile complete pentru sistemul <strong>IntelliFin</strong>.");
        notification.setIcon("settings-notification.png");

        createNotificationForUser(notification);


    }
}
