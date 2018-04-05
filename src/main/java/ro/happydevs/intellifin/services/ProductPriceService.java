package ro.happydevs.intellifin.services;

import ro.happydevs.intellifin.models.ProductPrice;
import ro.happydevs.intellifin.repositories.ProductPriceRepository;

public class ProductPriceService {

    private ProductPriceRepository productPriceRepository = new ProductPriceRepository();

    public boolean create(ProductPrice productPrice) {
        return productPriceRepository.create(productPrice);

    }
}
