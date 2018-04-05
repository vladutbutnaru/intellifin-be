package ro.intellifin.backend.ws;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.intellifin.backend.database.UserController;
import ro.intellifin.backend.models.User;

@CrossOrigin
@RestController
@RequestMapping(value="/v1/auth")
public class AuthenticationWS {

    @RequestMapping(value="/sign-up", method= RequestMethod.POST)
    public ResponseEntity createUser(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("city") int city,
                                     @RequestParam("gender") int gender,
                                     @RequestParam("age") int age,
                                     @RequestParam("driverLicense") int driverLicense,
                                     @RequestParam("hasCar") int hasCar,
                                     @RequestParam("smoker") int smoker,
                                     @RequestParam("married") int married,
                                     @RequestParam("numberOfKids") int numberOfKids,
                                     @RequestParam("phoneNumber") String phoneNumber,
                                     @RequestParam("address") String address){
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        u.setCity(city);
        u.setGender(gender);
        u.setAge(age);
        u.setDriverLicense(driverLicense==1);
        u.setOwnsCar(hasCar==1);
        u.setSmoker(smoker==1);
        u.setMarried(married==1);
        u.setNumberOfKids(numberOfKids);
        u.setPhoneNumber(phoneNumber);
        u.setAddress(address);
        u.setSubscriptionType(1);
        u.setRentApartment(false);
        u.setOwnsApartment(false);

        if(UserController.saveUser(u))
        return ResponseEntity.ok("OK");

        return ResponseEntity.ok("NOK");





    }

    @RequestMapping(value="/sign-in", method= RequestMethod.POST)
    public ResponseEntity signIn(@RequestParam("email") String email,
                                     @RequestParam("password") String password ){




            return ResponseEntity.ok(UserController.login(email,password));

    }

    @RequestMapping(value="/get-info", method= RequestMethod.POST, produces="application/json")
    public ResponseEntity signIn(@RequestParam("id") int id){

        return ResponseEntity.ok(UserController.getUserEmailById(id));

    }


}
