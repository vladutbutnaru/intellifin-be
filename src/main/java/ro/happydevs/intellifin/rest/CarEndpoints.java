package ro.happydevs.intellifin.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.Car;
import ro.happydevs.intellifin.services.CarService;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(value ="*")
@RequestMapping(value ="/rest/car")
public class CarEndpoints {

    private static Logger logger = LoggerFactory.getLogger(HouseholdEndpoints.class);
    private CarService carService = new CarService();
    private TokenService tokenService = new TokenService();



    @RequestMapping(value ="/create")
    public ResponseEntity<?> createCar(@RequestBody Car car,
                                             @RequestHeader("Authentication") String token) {

        if (tokenService.verifyToken(token)) {

            carService.createCar(car);
            return ResponseEntity.ok(car);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @RequestMapping(value ="/delete")
    public ResponseEntity<?> deleteCar(@RequestParam (value = "id") int id){

            return ResponseEntity.ok(carService.deleteCar(id));

    }





}
