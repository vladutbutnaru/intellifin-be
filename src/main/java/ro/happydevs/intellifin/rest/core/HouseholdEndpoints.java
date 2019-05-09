package ro.happydevs.intellifin.rest.core;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.models.business.Household;
import ro.happydevs.intellifin.services.HouseholdService;
import ro.happydevs.intellifin.services.TokenService;
import ro.happydevs.intellifin.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/notifications")
public class HouseholdEndpoints {

    @Autowired
    HouseholdService householdService;

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;


    @RequestMapping(value = "/household/create", method = RequestMethod.POST)
    @ApiOperation("Create a new household")
    public ResponseEntity<?> createHousehold(@RequestHeader("Authentication") String token,
                                             @RequestBody Household household) {

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(householdService.createHousehold(token, household));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    @RequestMapping(value = "/household/invite", method = RequestMethod.POST)
    @ApiOperation("Invite a user to own household")
    public ResponseEntity<?> createHousehold(@RequestHeader("Authentication") String token,
                                             @RequestParam Long userToInviteId) {

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(householdService.inviteMemberToHousehold(token, userToInviteId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    @RequestMapping(value = "/household/invite/accept", method = RequestMethod.POST)
    @ApiOperation("Accept invitation to household")
    public ResponseEntity<?> acceptHouseholdInvitation(@RequestHeader("Authentication") String token,
                                                       @RequestParam Long householdId) {

        if (tokenService.verifyToken(token)) {


            return ResponseEntity.ok(householdService.acceptInviteToHousehold(token, householdId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");


    }

    @RequestMapping(value = "/household/own", method = RequestMethod.GET)
    @ApiOperation("Get own household")
    public ResponseEntity<?> listAllHouseholds(@RequestHeader("Authentication") String token
    ) {

        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(householdService.getOwnHousehold(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }


    @RequestMapping(value = "/household/members/list", method = RequestMethod.GET)
    @ApiOperation("Get a list of users in a household")
    public ResponseEntity<?> listAllUsersInHousehold(@RequestHeader("Authentication") String token,
                                                     @RequestParam("id") Long householdId
    ) {

        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(householdService.getAllMembersOfHouseHold(householdId, token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }


}
