package ro.happydevs.intellifin.services;

import ro.happydevs.intellifin.models.City;
import ro.happydevs.intellifin.repositories.CityRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CityService {

    private CityRepository cityRepository = new CityRepository();

    public ArrayList<City> getAll() {
        return (ArrayList<City>) cityRepository.getAll();


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
                cityRepository.create(city);


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
