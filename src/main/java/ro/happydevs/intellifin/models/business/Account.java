package ro.happydevs.intellifin.models.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 1
 * @Title: Account Entity Bean
 * @Description: Representation of an User's financial account
 * @Links: User, Currency, Household, CONSTANTS
 */

@Entity
@Table(name = "intelli_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private boolean deleted;
    private Long userId;
    private String name;
    private int currency;
    private double sold;
    private int type;
    private String description;
    private String iban;
    private boolean isCreditCard;
    private double creditCardLimit;
    private boolean sharedWithHousehold;
    private Long sharedHouseholdId;

    public boolean isSharedWithHousehold() {
        return sharedWithHousehold;
    }

    public void setSharedWithHousehold(boolean sharedWithHousehold) {
        this.sharedWithHousehold = sharedWithHousehold;
    }

    public Long getSharedHouseholdId() {
        return sharedHouseholdId;
    }

    public void setSharedHouseholdId(Long sharedHouseholdId) {
        this.sharedHouseholdId = sharedHouseholdId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean isCreditCard() {
        return isCreditCard;
    }

    public void setCreditCard(boolean creditCard) {
        isCreditCard = creditCard;
    }

    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", deleted=" + deleted +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                ", sold=" + sold +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", iban='" + iban + '\'' +
                ", isCreditCard=" + isCreditCard +
                ", creditCardLimit=" + creditCardLimit +
                ", sharedWithHousehold=" + sharedWithHousehold +
                ", sharedHouseholdId=" + sharedHouseholdId +
                '}';
    }


}
