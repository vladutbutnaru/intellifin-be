package ro.happydevs.intellifin.rest.core;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.services.TokenService;
import ro.happydevs.intellifin.services.business.StatisticsService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/statistics")
public class StatisticsEndpoints {

    @Autowired
    TokenService tokenService;

    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(value = "/dashboard/get", method = RequestMethod.GET)
    @ApiOperation("Get dashboard statistics for a user")
    public ResponseEntity<?> getAccount(@RequestHeader("Authentication") String token
                                       ) {
        if (tokenService.verifyToken(token)) {
            return ResponseEntity.ok(statisticsService.getUserStatisticsDashboard(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

}
