package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.business.GroceryListItem;

import java.util.List;

@Repository
public interface GroceryListItemRepository extends JpaRepository<GroceryListItem, Long> {

    @Query("select i from GroceryListItem i where i.groceryListId = :listId")
    List<GroceryListItem> getItemsForGroceryList(@Param("listId") Long listId);


}
