package ro.happydevs.intellifin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Product;
import ro.happydevs.intellifin.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


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
