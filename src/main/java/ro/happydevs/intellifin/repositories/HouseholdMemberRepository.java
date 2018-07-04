package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.business.HouseholdMember;

import java.util.List;

@Repository
public interface HouseholdMemberRepository  extends JpaRepository<HouseholdMember,Long>{

    @Query("select hm from HouseholdMember hm where hm.userId = :userId")
    HouseholdMember findHouseholdMemberByUserId(@Param("userId") Long userId);

    @Query("select hm from HouseholdMember hm where hm.householdId = :householdId")
    List<HouseholdMember> findHouseholdMembersForHouseholdId(@Param("householdId") Long householdId);


}
