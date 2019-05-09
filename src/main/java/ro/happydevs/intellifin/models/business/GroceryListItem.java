package ro.happydevs.intellifin.models.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 2
 * @Title: Grocery List Item Entity Bean
 * @Description: Representation of a single item in a collection called GroceryList
 * @Links: Product, GroceryList
 */

@Entity
@Table(name = "intelli_grocery_list_item")
public class GroceryListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private boolean deleted;

    private Long productId;
    private double quantity;
    private boolean checked;
    private Long groceryListId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getGroceryListId() {
        return groceryListId;
    }

    public void setGroceryListId(Long groceryListId) {
        this.groceryListId = groceryListId;
    }
}
