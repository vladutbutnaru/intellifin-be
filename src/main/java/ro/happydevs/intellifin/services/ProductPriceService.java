package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.ProductPrice;
import ro.happydevs.intellifin.repositories.ProductPriceRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.List;

@Service
public class ProductPriceService {
    @Autowired
    ProductPriceRepository productPriceRepository;

    private static Logger logger = LoggerFactory.getLogger(ProductPriceService.class);

    @Autowired
    IntelliLogger intelliLogger;

    public boolean create(ProductPrice productPrice) {
        productPriceRepository.save(productPrice);

        return true;

    }

    public ProductPrice findPriceForProductWithGivenShopId(Long productId, Long shopId) {
        List<ProductPrice> pricesFound = productPriceRepository.findPricesForProductAndShop(productId, shopId);
        if (pricesFound.size() > 0)
            return pricesFound.get(0);

        return new ProductPrice();


    }
}
