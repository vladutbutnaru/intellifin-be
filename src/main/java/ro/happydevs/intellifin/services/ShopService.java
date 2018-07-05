package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Shop;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.repositories.ShopRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.ArrayList;

@Service
public class ShopService {

    private static Logger logger = LoggerFactory.getLogger(ShopService.class);
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    IntelliLogger intelliLogger;


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
