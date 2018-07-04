package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.business.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
