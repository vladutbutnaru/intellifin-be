package ro.happydevs.intellifin.models.dto.business.accounts;

/**
 @Author: Vlad Butnaru
 @Version: 1.0
 @Revision: 1
 @Title: DTO for creating an account in IntelliFin
 @Description: none
 @Links: none

 */
public class CreateAccountDTO {

    private String accountName;
    private String accountDescription;
    private int accountCurrency;
    private double initialSold;
    private boolean shareWithHousehold;
    private int type;
    private boolean creditCard;
    private double creditCardLimit;
    private String iban;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public int getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(int accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public double getInitialSold() {
        return initialSold;
    }

    public void setInitialSold(double initialSold) {
        this.initialSold = initialSold;
    }

    public boolean isShareWithHousehold() {
        return shareWithHousehold;
    }

    public void setShareWithHousehold(boolean shareWithHousehold) {
        this.shareWithHousehold = shareWithHousehold;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }
}
