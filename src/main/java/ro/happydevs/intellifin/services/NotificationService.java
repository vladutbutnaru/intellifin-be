package ro.happydevs.intellifin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.Notification;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.HouseholdRepository;
import ro.happydevs.intellifin.repositories.NotificationRepository;
import ro.happydevs.intellifin.repositories.UserRepository;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HouseholdRepository householdRepository;


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

    public boolean isUserAlreadyInvitedToHousehold(String token, Long invitingMemberId){

        for(Notification n : getNotificationsForUser(token)){
            if(n.getType().equals(CONSTANTS.NOTIFICATION_TYPE_HOUSEHOLD_INVITE) && n.getInvitingMemberId() == invitingMemberId)
                return true;

        }
        return false;



    }

    public void createInvitationToHouseholdNotification(Long userToInviteId, User invitingUser, Long houseHoldId){


        Notification notification = new Notification();
        notification.setType(CONSTANTS.NOTIFICATION_TYPE_HOUSEHOLD_INVITE);
        notification.setViewed(false);
        notification.setUserId(userToInviteId);
        notification.setText(invitingUser.getEmail() + " te-a invitat in Household-ul " + householdRepository.findById(houseHoldId).get().getName());
        notification.setIcon("settings-notification.png");
        notification.setInviteId(houseHoldId);
        notification.setInvitingMemberId(invitingUser.getId());


        createNotificationForUser(notification);



    }

    public void deleteHouseholdInviteNotification(String token, Long householdId){
        for(Notification n : getNotificationsForUser(token)){
            if(n.getInviteId() == householdId){
                notificationRepository.delete(n);
                return;
            }

        }


    }
}
