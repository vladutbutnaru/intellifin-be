package ro.happydevs.intellifin.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.Household;
import ro.happydevs.intellifin.services.HouseholdService;
import ro.happydevs.intellifin.services.TokenService;

@RestController
@CrossOrigin(value ="*")
@RequestMapping(value ="/rest/households")
public class HouseholdEndpoints {

    private static Logger logger = LoggerFactory.getLogger(HouseholdEndpoints.class);
    private HouseholdService householdService = new HouseholdService();
    private TokenService tokenService = new TokenService();

    //creates Household
    @RequestMapping(value ="/create")
    public ResponseEntity<?> createHousehold(@RequestBody Household household,
                                             @RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            householdService.createHousehold(household);
                return ResponseEntity.ok(household);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    //adds individual member
    @RequestMapping(value ="/addmember")
    public ResponseEntity<?> addHouseholdMember(@RequestBody Household household,
                                             @RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            householdService.addMembertoHousehold(household);
            return ResponseEntity.ok(household);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    //deletes individual member
    @RequestMapping(value ="/deletemember")
    public ResponseEntity<?> deleteMemberFromHousehold(@RequestBody Household household,
                                                @RequestHeader("Authentication") String token) {
        if (tokenService.verifyToken(token)) {

            householdService.deleteMemberFromHousehold(household);
            return ResponseEntity.ok(household);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    //deletes Household
    @RequestMapping(value ="/delete")
    public ResponseEntity<?> deleteHousehold(@RequestHeader("Authentication") String token,
                                             @RequestParam (value = "id") int id){
        if(tokenService.verifyToken(token)){
            return ResponseEntity.ok(householdService.deleteHousehold(token,id));
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    //list of members that belong to a household
    @RequestMapping(value ="/getmembers")
    public ResponseEntity<?> getHouseholdMembers(@RequestHeader("Authentication") String token,
                                                 @RequestParam (value = "house_id") int id){

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(householdService.getHouseholdMembers(id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    //list of all household members in the system
    @RequestMapping(value ="/getall")
    public ResponseEntity<?> getAll(@RequestHeader("Authentication") String token){

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(householdService.getAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

    }



}
