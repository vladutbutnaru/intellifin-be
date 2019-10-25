package ro.happydevs.intellifin.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.happydevs.intellifin.services.CityService;
import ro.happydevs.intellifin.utils.init.CSVImporter;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest/cities")
public class CityEndpoints {
    @Autowired
    CityService cityService;

    @Autowired
    CSVImporter csvImporter;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {

        return ResponseEntity.ok(cityService.getAll());

    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public ResponseEntity<?> importCities() {


        csvImporter.importCities();
        return ResponseEntity.ok("Import done");
    }

}
