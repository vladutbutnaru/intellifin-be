package ro.happydevs.intellifin.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.happydevs.intellifin.services.CityService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/cities")
public class CityEndpoints {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {

        return ResponseEntity.ok(cityService.getAll());

    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public ResponseEntity<?> importCities(@RequestParam(value = "path") String path) {


        cityService.importCities(path);
        return ResponseEntity.ok("Import done");
    }
}
