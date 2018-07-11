package ro.happydevs.intellifin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.GroceryList;
import ro.happydevs.intellifin.models.business.GroceryListItem;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.models.security.Token;
import ro.happydevs.intellifin.repositories.GroceryListItemRepository;
import ro.happydevs.intellifin.repositories.GroceryListRepository;

import java.util.List;

@Service
public class GroceryListService {


    @Autowired
    GroceryListRepository groceryListRepository;

    @Autowired
    GroceryListItemRepository groceryListItemRepository;


    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;


    public List<GroceryList> getUserGroceryLists(String token){
        User loggedInUser  = tokenService.getUserByToken(token);

        //get own grocery lists
        return groceryListRepository.getGroceryListsForUser(loggedInUser.getId());

    }


    public List<GroceryListItem> getGroceryListItemsForListId(Long listId){
        return groceryListItemRepository.getItemsForGroceryList(listId);

    }



}
