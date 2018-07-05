package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.happydevs.intellifin.models.business.Activity;

import java.util.List;

public interface ActivityRepository  extends JpaRepository<Activity,Long>{
    @Query("select a from Activity a where a.userId = :userId")
    List<Activity> getActivitiesForUser(@Param("userId") Long userId);
}
