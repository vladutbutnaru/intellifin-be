package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.ProductPrice;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
    @Query("select pp from ProductPrice pp where pp.productId = :productId and pp.shopId = :shopId")
    List<ProductPrice> findPricesForProductAndShop(@Param("productId") Long productId,
                                                   @Param("shopId") Long shopId);

}
