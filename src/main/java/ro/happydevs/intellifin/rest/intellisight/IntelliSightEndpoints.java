package ro.happydevs.intellifin.rest.intellisight;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.intellisight.compute.IntelliSightWorker;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/intellisight")
public class IntelliSightEndpoints {

    @Autowired
    TokenService tokenService;

    @Autowired
    IntelliSightWorker intelliSightWorker;

    @RequestMapping(value = "/compute/total/product", method = RequestMethod.GET)
    @ApiOperation("Get total amount of money spent on a specific product")
    public ResponseEntity<?> computeTransactionsForSingleProduct(@RequestHeader("Authentication") String token,
                                                                 @RequestParam("product_id") Long productId
    ) {
        if (tokenService.verifyToken(token)) {
            // return ResponseEntity.ok(intelliSightWorker.computeTransactionsForSingleProduct(productId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value = "/compute/total/product/shop", method = RequestMethod.GET)
    @ApiOperation("Get total amount of money spent on a specific product inside a shop")
    public ResponseEntity<?> computeTransactionsForSingleProduct(@RequestHeader("Authentication") String token,
                                                                 @RequestParam("product_id") Long productId,
                                                                 @RequestParam("shop_id") Long shopId
    ) {
        if (tokenService.verifyToken(token)) {
            // return ResponseEntity.ok(intelliSightWorker.computeTransactionsForSingleProductInsideShop(productId, shopId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value = "/compute/total/shop", method = RequestMethod.GET)
    @ApiOperation("Get total amount of money spent inside a shop")
    public ResponseEntity<?> computeTransactionsForSingleShop(@RequestHeader("Authentication") String token,
                                                              @RequestParam("shop_id") Long shopId
    ) {
        if (tokenService.verifyToken(token)) {
            // return ResponseEntity.ok(intelliSightWorker.computeTransactionsForSingleShop(shopId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value = "/compute/total/city", method = RequestMethod.GET)
    @ApiOperation("Get total amount of money spent inside a city")
    public ResponseEntity<?> computeTransactionsForCity(@RequestHeader("Authentication") String token,
                                                        @RequestParam("city_id") Long cityId
    ) {
        if (tokenService.verifyToken(token)) {
            // return ResponseEntity.ok(intelliSightWorker.computeTransactionsForCity(cityId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value = "/compute/total/city/product", method = RequestMethod.GET)
    @ApiOperation("Get total amount of money spent inside a city on a product")
    public ResponseEntity<?> computeTransactionsForCityOnProduct(@RequestHeader("Authentication") String token,
                                                                 @RequestParam("city_id") Long cityId,
                                                                 @RequestParam("product_id") Long productId
    ) {
        if (tokenService.verifyToken(token)) {
            // return ResponseEntity.ok(intelliSightWorker.computeTransactionsForCityOnProduct(cityId, productId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
