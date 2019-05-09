package ro.happydevs.intellifin.utils.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.business.Activity;
import ro.happydevs.intellifin.repositories.ActivityRepository;

import java.util.Date;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 1
 * @Title: Spring Factory Component for Activity Bean
 * @Description: Provides automatic generation of instantiating and managing Activity beans inside IntelliFin
 * @Links: none
 */

@Component
public class ActivityFactory {

    @Autowired
    ActivityRepository activityRepository;

    public void addActivity(String title, String description, String link, String icon, Long userId) {
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
