package ro.happydevs.intellifin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.Product;
import ro.happydevs.intellifin.services.ProductService;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/products")
public class ProductEndpoints {
    @Autowired
    private ProductService productService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody Product product,
                                           @RequestHeader("Authentication") String token) {

        if (tokenService.verifyToken(token)) {

            productService.createProduct(product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }


    @RequestMapping(value = "/list")
    public ResponseEntity<?> listProducts(@RequestHeader("Authentication") String token) {

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(productService.getAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }


    @RequestMapping(value = "/get")
    public ResponseEntity<?> getProduct(@RequestHeader("Authentication") String token,
                                        @RequestParam(value = "id") Long id) {

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(productService.getById(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

}
