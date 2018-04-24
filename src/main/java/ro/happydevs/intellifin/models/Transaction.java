package ro.happydevs.intellifin.models;


public class Transaction extends GenericModel {

    private double amount;
    private int currency;
    private int userId;
    private int accountId;
    private int recurring;
    private int recurringDays;
    private String tag;
    // type = expense (0)/ earning (1)
    private int type;
    private int sourceShopId;


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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public int getSourceShopId() {
        return sourceShopId;
    }

    public void setSourceShopId(int sourceShopId) {
        this.sourceShopId = sourceShopId;
    }
}
