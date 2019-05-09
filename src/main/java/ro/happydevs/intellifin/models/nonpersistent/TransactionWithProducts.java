package ro.happydevs.intellifin.models.nonpersistent;

import ro.happydevs.intellifin.models.business.ProductPrice;
import ro.happydevs.intellifin.models.business.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 1
 * @Title: Transaction with Products helper Bean
 * @Description: A class that links a transaction with all associated products
 * @Links: Transaction, ProductPrice
 */

public class TransactionWithProducts {


    private List<ProductPrice> productPrices = new ArrayList<>();

    private Transaction transaction;

    public List<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(List<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
