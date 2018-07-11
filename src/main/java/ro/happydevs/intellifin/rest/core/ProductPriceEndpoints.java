package ro.happydevs.intellifin.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.services.ProductPriceService;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/products/prices")
public class ProductPriceEndpoints {
    @Autowired
    TokenService tokenService;

    @Autowired
    ProductPriceService productPriceService;

    @RequestMapping(value = "/get/shop", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestHeader("Authentication") String token,
                                        @RequestParam(value = "shopId") Long shopId,
                                        @RequestParam(value = "productId") Long productId) {

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(productPriceService.findPriceForProductWithGivenShopId(productId, shopId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }
}
