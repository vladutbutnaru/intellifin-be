package ro.happydevs.intellifin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.Shop;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.ShopRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    TokenService tokenService;


    public ArrayList<Shop> getAllShopsFromUserCity(String token){
        ArrayList<Shop> shops = new ArrayList<>();
        User loggedInUser = tokenService.getUserByToken(token);
        for(Shop shop : shopRepository.findAll()){
            if(shop.getCity() == loggedInUser.getCity()){
                shops.add(shop);
            }


        }
        return shops;



    }

    public void createShop(Shop shop){
        shopRepository.save(shop);

    }
}
