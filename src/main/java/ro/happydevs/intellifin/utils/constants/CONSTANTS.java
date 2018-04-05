package ro.happydevs.intellifin.utils.constants;

public class CONSTANTS {

    //server info
    public static final String IP_ADDRESS = "167.99.248.187";
    public static final String REMOTE_MYSQL_USERNAME = "remote";
    public static final String REMOTE_MYSQL_PASSWORD = "mysqlpassword";

    //table names
    public static final String USER_TABLE = "Users";
    public static final String TOKEN_TABLE = "Tokens";
    public static final String ACCOUNT_TABLE = "Accounts";
    public static final String CITY_TABLE = "Cities";
    public static final String SHOP_TABLE = "Shops";
    public static final String PRODUCT_TABLE = "Products";
    public static final String EXPENSE_TABLE = "Expenses";
    public static final String EXPENSE_PRODUCTS_TABLE = "Expense_Products";
    public static final String PRODUCT_PRICE_TABLE = "Product_Prices";
    public static final String NOTIFICATION_TABLE = "Notifications";

    //User type constants

    public static final int USER_TYPE_REGULAR = 1;
    public static final int USER_TYPE_DEVELOPER = 2;
    public static final int USER_TYPE_SHOP = 3;
    public static final int USER_TYPE_ANALYTICS = 4;
    public static final int USER_TYPE_ADVERTISER = 5;

    //User gender constants
    public static final int USER_GENDER_MALE = 1;
    public static final int USER_GENDER_FEMALE = 2;

    //Account type constants
    public static final int ACCOUNT_TYPE_CASH = 1;
    public static final int ACCOUNT_TYPE_BANK_CURRENT = 2;
    public static final int ACCOUNT_TYPE_BANK_CREDIT = 3;
    public static final int ACCOUNT_TYPE_BANK_SAVINGS = 4;
    public static final int ACCOUNT_TYPE_BANK_DEPOSIT = 5;
    public static final int ACCOUNT_TYPE_VIRTUAL = 6;


}
