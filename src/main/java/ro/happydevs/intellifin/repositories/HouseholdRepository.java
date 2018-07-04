package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.business.Household;

@Repository
public interface HouseholdRepository extends JpaRepository<Household,Long> {


}
