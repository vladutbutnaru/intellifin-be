package ro.happydevs.intellifin.intellisight.compute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.business.ProductPrice;
import ro.happydevs.intellifin.services.ProductPriceService;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 1
 * @Title: IntelliSight technology worker
 * @Description: A class that provides multiple statistics related functions to produce KPIs
 * @Links: none
 */

@Component
public class IntelliSightWorker {

    @Autowired
    ProductPriceService productPriceService;

    /**
     * Calculates and returns the total amount spent by
     * all IntelliFin users on a specific product
     *
     * @param productId
     * @return amount
     */

    public double computeTransactionsForSingleProduct(Long productId) {
        double total = 0;
        for (ProductPrice productPrice : productPriceService.findProductPricesForProductId(productId)) {
            total += productPrice.getPrice();
        }
        return total;
    }

    /**
     * Calculates and returns the total amount spent by
     * all IntelliFin users on a specific product in a specific shop
     *
     * @param productId
     * @param shopId
     *
     * @return amount
     */

//    public double computeTransactionsForSingleProductInsideShop(Long productId, Long shopId){
//        //TODO: IMPLEMENTATION
//
//    }

    /**
     * Calculates and returns the total amount spent by
     * all IntelliFin users on a specific shop
     *
     * @param shopId
     * @return amount
     */

//    public double computeTransactionsForSingleShop(Long shopId){
//        //TODO: IMPLEMENTATION
//
//    }

    /**
     * Calculates and returns the total amount spent by
     * all users from a city
     *
     * @param cityId
     * @return amount
     */

//    public double computeTransactionsForCity(Long cityId){
//        //TODO: IMPLEMENTATION
//
//    }

    /**
     * Calculates and returns the total amount spent by
     * all users from a city on a specific product
     *
     * @param cityId
     * @param productId
     *
     * @return amount
     */

//    public double computeTransactionsForCityOnProduct(Long cityId, Long productId){
//        //TODO: IMPLEMENTATION
//
//    }

    /**
     * Calculates and returns the city with the most money
     * spent by adding up all transactions made in shops from that
     * city
     *
     *
     * @return City
     */

//    public City computeTheCityWithMostMoneySpent(){
//        //TODO: IMPLEMENTATION
//
//    }


    /**
     * Calculates and returns the city with the most money
     * spent by adding up all transactions made in shops from that
     * city
     *
     *
     * @return City
     */

//    public City computeTheCityWithMostMoneySpent(){
//        //TODO: IMPLEMENTATION
//
//    }


}
