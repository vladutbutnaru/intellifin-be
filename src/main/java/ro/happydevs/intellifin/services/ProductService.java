package ro.happydevs.intellifin.services;

import ro.happydevs.intellifin.models.Product;
import ro.happydevs.intellifin.repositories.ProductRepository;

import java.util.ArrayList;

public class ProductService {
    private ProductRepository productRepository = new ProductRepository();

    public boolean createProduct(Product product) {
        //TODO: Find category by name
        return productRepository.create(product);

    }

    public ArrayList<Product> getAll() {
        return (ArrayList<Product>) productRepository.getAll();


    }

    public Product getById(int id) {
        return (Product) productRepository.getById(id);

    }


}
