package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Expense;
import ro.happydevs.intellifin.models.Transaction;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class TransactionRepository implements IRepository {

    private static Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    @Override
    public ArrayList<?> getAll() {
        return null;
    }

    @Override
    public ArrayList<?> getAllByStringColumn(String column, String value) {
        return null;
    }

    @Override
    public ArrayList<?> getAllByNumericColumn(String column, int value) {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(int id, Object newObject) {
        return false;
    }

    @Override
    public boolean create(Object object) {
       Connection con = DBConnection.getConnection();
       Transaction expense = (Transaction) object;
       try{
           java.util.Date date = new java.util.Date();
           PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.EXPENSE_TABLE + "(amount,currency,user_id,account_id,recurring,recurring_days,tag,type,shop_id,created_at,deleted) " +
                   "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
           ps.setDouble(1, expense.getAmount());
           ps.setInt(2,expense.getCurrency());
           ps.setInt(3,expense.getUserId());
           ps.setInt(4,expense.getAccountId());
           ps.setInt(5,expense.getRecurring());
           ps.setInt(6,expense.getRecurringDays());
           ps.setString(7,expense.getTag());
           ps.setInt(8,expense.getType());
           ps.setInt(9,expense.getShopId());
           ps.setDate(10, new Date((date.getTime())));
           ps.setInt(11,0);
           ps.executeUpdate();
            logger.info("[Transaction Repository] - Created expense");
           return true;

       }
       catch(Exception e){
           e.printStackTrace();
           logger.error(e.getMessage());
       }

       return false;
    }
}
