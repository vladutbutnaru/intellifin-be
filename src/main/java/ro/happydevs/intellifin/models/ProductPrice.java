package ro.happydevs.intellifin.models;

public class ProductPrice extends GenericModel {

    private int productId;
    private double price;
    private int shopId;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "productId=" + productId +
                ", price=" + price +
                ", shopId=" + shopId +
                '}';
    }
}

