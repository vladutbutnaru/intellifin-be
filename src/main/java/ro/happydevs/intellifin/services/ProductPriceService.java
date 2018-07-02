package ro.happydevs.intellifin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.ProductPrice;
import ro.happydevs.intellifin.repositories.ProductPriceRepository;

import java.util.List;

@Service
public class ProductPriceService {
    @Autowired
    ProductPriceRepository productPriceRepository;

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
