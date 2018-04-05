package ro.happydevs.intellifin.models;

import java.util.ArrayList;

public class Expense extends Transaction {

    private Shop shop;
    private ArrayList<Product> products;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
