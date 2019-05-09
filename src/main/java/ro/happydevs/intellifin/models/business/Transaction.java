package ro.happydevs.intellifin.models.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 24
 * @Title: Transaction Entity Bean
 * @Description: Representation of a transaction created by a user
 * @Links: Account, Shop, User
 */

@Entity
@Table(name = "intelli_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private boolean deleted;

    private double amount;
    private Long currency;
    private Long userId;
    private Long accountId;
    private boolean recurring;
    private int recurringDays;
    private String tag;
    private int type;
    private Long shopId;
    private String name;
    private String description;
    private String category;
    private boolean earning;

    public boolean isEarning() {
        return earning;
    }

    public void setEarning(boolean earning) {
        this.earning = earning;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
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

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
        return "Transaction{" +
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
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", earning=" + earning +
                '}';
    }
}
