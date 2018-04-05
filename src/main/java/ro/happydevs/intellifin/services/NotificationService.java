package ro.happydevs.intellifin.services;

import ro.happydevs.intellifin.models.Notification;
import ro.happydevs.intellifin.repositories.NotificationRepository;

import java.util.ArrayList;

public class NotificationService {

    private NotificationRepository notificationRepository = new NotificationRepository();


    public ArrayList<Notification> getNotificationsForUser(String token) {
        UserService userService = new UserService();
        return (ArrayList<Notification>) notificationRepository.getAllByNumericColumn("user_id", userService.getUserForToken(token).getId());


    }

    public void createNotificationForUser(Notification notification) {

        notificationRepository.create(notification);


    }

    public void createNotificationForNewUser(int userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setRead(false);
        notification.setText("Completeaza-ti contul cu informatiile complete pentru sistemul <strong>IntelliFin</strong>.");
        notification.setIcon("settings-notification.png");

        createNotificationForUser(notification);


    }
}
