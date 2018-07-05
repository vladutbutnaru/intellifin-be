package ro.happydevs.intellifin.utils.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.business.Activity;
import ro.happydevs.intellifin.repositories.ActivityRepository;

import java.util.Date;

@Component
public class ActivityFactory {

    @Autowired
    ActivityRepository activityRepository;

    public void addActivity(String title, String description, String link, String icon, Long userId){
        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setDeleted(false);
        activity.setCreatedAt(new Date());
        activity.setLink(link);
        activity.setTitle(title);
        activity.setDescription(description);

        activityRepository.save(activity);


    }


}
