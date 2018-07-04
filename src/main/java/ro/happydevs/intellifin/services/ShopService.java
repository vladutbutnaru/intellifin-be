package ro.happydevs.intellifin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Shop;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.repositories.ShopRepository;

import java.util.ArrayList;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    TokenService tokenService;


    public ArrayList<Shop> getAllShopsFromUserCity(String token) {
        ArrayList<Shop> shops = new ArrayList<>();
        User loggedInUser = tokenService.getUserByToken(token);
        for (Shop shop : shopRepository.findAll()) {
            if (shop.getCity() == loggedInUser.getCity()) {
                shops.add(shop);
            }


        }
        return shops;


    }

    public void createShop(Shop shop) {
        shopRepository.save(shop);

    }
}
