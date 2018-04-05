package ro.happydevs.intellifin.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.happydevs.intellifin.services.CityService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/cities")
public class CityEndpoints {

    private CityService cityService = new CityService();

    @RequestMapping(value = "/list")
    public ResponseEntity<?> getList() {

        return ResponseEntity.ok(cityService.getAll());

    }

    @RequestMapping(value = "/import")
    public ResponseEntity<?> importCities(@RequestParam(value = "path") String path) {

        cityService.importCities(path);
        return ResponseEntity.ok("Import done");
    }
}
