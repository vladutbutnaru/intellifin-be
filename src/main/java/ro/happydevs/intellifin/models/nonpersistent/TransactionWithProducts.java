package ro.happydevs.intellifin.models.nonpersistent;

import ro.happydevs.intellifin.models.business.Product;
import ro.happydevs.intellifin.models.business.ProductPrice;
import ro.happydevs.intellifin.models.business.Transaction;

import java.util.ArrayList;
import java.util.List;

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
