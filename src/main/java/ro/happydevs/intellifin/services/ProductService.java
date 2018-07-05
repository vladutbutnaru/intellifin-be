package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Product;
import ro.happydevs.intellifin.repositories.ProductRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;

import java.util.List;

@Service
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductRepository productRepository;
    @Autowired
    IntelliLogger intelliLogger;


    public boolean createProduct(Product product) {
        //TODO: Find category by name
        productRepository.save(product);
        return true;
    }

    public List<Product> getAll() {
        return productRepository.findAll();


    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();

    }


}
