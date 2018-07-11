package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.business.GroceryList;

import java.util.List;

@Repository
public interface GroceryListRepository extends JpaRepository<GroceryList,Long> {

    @Query("select g from GroceryList g where g.userId = :userId")
    List<GroceryList> getGroceryListsForUser(@Param("userId") Long userId);
}
