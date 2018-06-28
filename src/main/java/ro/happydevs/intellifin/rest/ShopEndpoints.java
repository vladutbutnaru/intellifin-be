package ro.happydevs.intellifin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.Product;
import ro.happydevs.intellifin.models.Shop;
import ro.happydevs.intellifin.services.ShopService;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/shops")
public class ShopEndpoints {

@Autowired
    TokenService tokenService;

@Autowired
    ShopService shopService;

    @RequestMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createShop(@RequestBody Shop shop,
                                           @RequestHeader("Authentication") String token) {

        if (tokenService.verifyToken(token)) {

           shopService.createShop(shop);
            return ResponseEntity.ok(shop);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    @RequestMapping(value = "/list")
    public ResponseEntity<?> createShop(@RequestHeader("Authentication") String token) {

        if (tokenService.verifyToken(token)) {

            return ResponseEntity.ok(shopService.getAllShopsFromUserCity(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }


}
