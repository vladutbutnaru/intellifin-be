package ro.happydevs.intellifin.models.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 @Author: Vlad Butnaru
 @Version: 1.0
 @Revision: 14
 @Title: Expense Entity Bean
 @Description: Representation of an expense
 @Links: User, Account, Currency, CONSTANTS

 */

@Entity
@Table(name = "intelli_expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private boolean deleted;

    private double amount;
    private int currency;
    private Long userId;
    private Long accountId;
    private int recurring;
    private int recurringDays;
    private String tag;
    private int type;
    private Long shopId;

    @Transient
    private ArrayList<Product> products;

    private boolean toOwnAccount;

    private boolean toCredit;

    private Long toAccountId;

    private boolean toIntelliFinUser;

    private String ibanIntelliFinUser;

    public boolean isToIntelliFinUser() {
        return toIntelliFinUser;
    }

    public void setToIntelliFinUser(boolean toIntelliFinUser) {
        this.toIntelliFinUser = toIntelliFinUser;
    }

    public String getIbanIntelliFinUser() {
        return ibanIntelliFinUser;
    }

    public void setIbanIntelliFinUser(String ibanIntelliFinUser) {
        this.ibanIntelliFinUser = ibanIntelliFinUser;
    }

    public boolean isToOwnAccount() {
        return toOwnAccount;
    }

    public void setToOwnAccount(boolean toOwnAccount) {
        this.toOwnAccount = toOwnAccount;
    }

    public boolean isToCredit() {
        return toCredit;
    }

    public void setToCredit(boolean toCredit) {
        this.toCredit = toCredit;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }


    public int getRecurring() {
        return recurring;
    }

    public void setRecurring(int recurring) {
        this.recurring = recurring;
    }

    public int getRecurringDays() {
        return recurringDays;
    }

    public void setRecurringDays(int recurringDays) {
        this.recurringDays = recurringDays;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", deleted=" + deleted +
                ", amount=" + amount +
                ", currency=" + currency +
                ", userId=" + userId +
                ", accountId=" + accountId +
                ", recurring=" + recurring +
                ", recurringDays=" + recurringDays +
                ", tag='" + tag + '\'' +
                ", type=" + type +
                ", shopId=" + shopId +
                ", products=" + products +
                ", toOwnAccount=" + toOwnAccount +
                ", toCredit=" + toCredit +
                ", toAccountId=" + toAccountId +
                ", toIntelliFinUser=" + toIntelliFinUser +
                ", ibanIntelliFinUser='" + ibanIntelliFinUser + '\'' +
                '}';
    }
}
