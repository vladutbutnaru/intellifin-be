package ro.happydevs.intellifin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.City;
import ro.happydevs.intellifin.repositories.CityRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    IntelliLogger intelliLogger;

    public List<City> getAll() {
        return cityRepository.findAll();


    }

    public void importCities(String path) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] municipalityLine = line.split(cvsSplitBy);
                City city = new City();
                city.setName(municipalityLine[2].replace("\"", ""));
                cityRepository.save(city);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
