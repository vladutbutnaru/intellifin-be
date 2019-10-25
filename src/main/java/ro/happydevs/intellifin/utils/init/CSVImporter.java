package ro.happydevs.intellifin.utils.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.business.City;
import ro.happydevs.intellifin.repositories.CityRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class CSVImporter {
    @Autowired
    private CityRepository cityRepository;

    public void importCities() {
        String csvFile = "S:\\ro_cities.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        ArrayList<City> cities = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] cityLine = line.split(cvsSplitBy);
                if (cityLine[0].equals("X")) {
                    continue;
                }
                City city = new City();

                city.setLat(Double.parseDouble(cityLine[0]));
                city.setLng(Double.parseDouble(cityLine[1]));
                city.setName(cityLine[2]);
                city.setCounty(cityLine[3]);
                cities.add(city);

            }

            cityRepository.saveAll(cities);

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
