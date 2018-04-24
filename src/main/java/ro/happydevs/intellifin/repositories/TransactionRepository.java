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
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransactionRepository implements IRepository {

    private static Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    @Override
    public ArrayList<?> getAll() {

        return getAllByNumericColumn("deleted", 0);
    }

    @Override
    public ArrayList<Transaction> getAllByStringColumn(String column, String value) {
        Connection con = DBConnection.getConnection();
        ArrayList<Transaction> tr = new ArrayList<Transaction>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.TRANSACTION_TABLE + " WHERE " + column + "=? AND deleted=0");
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt(1));
                t.setAmount(rs.getDouble(2));
                t.setCurrency(rs.getInt(3));
                t.setUserId(rs.getInt(4));
                t.setAccountId(rs.getInt(5));
                t.setRecurring(rs.getInt(6));
                t.setRecurringDays(rs.getInt(7));
                t.setTag(rs.getString(8));
                t.setType(rs.getInt(9));
                t.setSourceShopId(rs.getInt(10));
                t.setCreatedAt(rs.getDate(11));
                t.setDeleted(false);

                tr.add(t);


            }
            logger.info("[Account Repository] - Success getByStringColumn");
        } catch (Exception e) {

            e.printStackTrace();
            logger.error("[Account Repository] - Failed getByStringColumn");
        }


        return tr;


    }

    @Override
    public ArrayList<Transaction> getAllByNumericColumn(String column, int value) {

        Connection con = DBConnection.getConnection();
        ArrayList<Transaction> tr = new ArrayList<Transaction>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.TRANSACTION_TABLE + " WHERE " + column + "=? AND deleted=0");
            ps.setInt(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt(1));
                t.setAmount(rs.getDouble(2));
                t.setCurrency(rs.getInt(3));
                t.setUserId(rs.getInt(4));
                t.setAccountId(rs.getInt(5));
                t.setRecurring(rs.getInt(6));
                t.setRecurringDays(rs.getInt(7));
                t.setTag(rs.getString(8));
                t.setType(rs.getInt(9));
                t.setSourceShopId(rs.getInt(10));
                t.setCreatedAt(rs.getDate(11));
                t.setDeleted(false);

                tr.add(t);


            }
            logger.info("[Account Repository] - Success getByNumericColumn");
        } catch (Exception e) {

            e.printStackTrace();
            logger.error("[Account Repository] - Failed getByNumericColumn");
        }


        return tr;





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
    public boolean update(Object newObject, String token) {
        return false;
    }

    @Override
    public boolean create(Object object) {
       Connection con = DBConnection.getConnection();
       Transaction t = (Transaction) object;
       try{
           java.util.Date date = new java.util.Date();
           PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.TRANSACTION_TABLE + "(amount,currency,user_id,account_id,recurring,recurring_days,tag,type,source_shop_id,created_at,deleted) " +
                   "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
           ps.setDouble(1, t.getAmount());
           ps.setInt(2,t.getCurrency());
           ps.setInt(3,t.getUserId());
           ps.setInt(4,t.getAccountId());
           ps.setInt(5,t.getRecurring());
           ps.setInt(6,t.getRecurringDays());
           ps.setString(7,t.getTag());
           ps.setInt(8,t.getType());
           ps.setInt(9, t.getSourceShopId());
           ps.setDate(10, new Date((date.getTime())));
           ps.setInt(11,0);

           if (t.getType() == 0)
               ps.setDouble(1, (-t.getAmount()));

           ps.executeUpdate();
            logger.info("[Transaction Repository Create] - Success");
           return true;

       }
       catch(Exception e){
           e.printStackTrace();
           logger.error("[Transaction Repository Create] - Error");
       }

       return false;
    }
}
