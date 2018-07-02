package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import ro.happydevs.intellifin.models.Household;
import ro.happydevs.intellifin.models.HouseholdMember;

@Repository
public interface HouseholdRepository extends JpaRepository<Household,Long> {


}
