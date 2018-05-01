package ro.happydevs.intellifin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Car;
import ro.happydevs.intellifin.repositories.CarRepository;
import ro.happydevs.intellifin.repositories.TokenRepository;

import java.util.ArrayList;

public class CarService {

    private static Logger logger = LoggerFactory.getLogger(CarService.class);
    private CarRepository carRepository = new CarRepository();
    private TokenRepository tokenRepository = new TokenRepository();

    //creates car
    public boolean createCar(Car car){

        logger.info("[CarService createCar] - Called");
        return carRepository.create(car);

    }

    //"deletes" car
    public boolean deleteCar(int car){

        logger.info("[CarService - deleteCar] - Called");
        return carRepository.delete(car);
    }

    public ArrayList<Car> getAll(){
        logger.info("[CarService getAll] - Called");
        return (ArrayList<Car>) carRepository.getAll();
    }

}
