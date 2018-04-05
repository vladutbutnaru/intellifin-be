package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Account;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountRepository implements IRepository {


    private static Logger logger = LoggerFactory.getLogger(AccountRepository.class);


    @Override
    public ArrayList<?> getAll() {
        return getAllByNumericColumn("deleted", 0);


    }

    @Override
    public ArrayList<?> getAllByStringColumn(String column, String value) {

        Connection con = DBConnection.getConnection();
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.ACCOUNT_TABLE + " WHERE " + column + "=? AND deleted=0");
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setUserId(rs.getInt(2));
                account.setName(rs.getString(3));
                account.setCurrency(rs.getInt(4));
                account.setSold(rs.getDouble(5));
                account.setType(rs.getInt(6));
                account.setDescription(rs.getString(7));
                account.setDeleted(false);
                account.setCreatedAt(rs.getDate(9));
                accounts.add(account);


            }
            logger.info("[Account Repository] - Success getByStringColumn");
        } catch (Exception e) {

            e.printStackTrace();
            logger.error("[Account Repository] - Failed getByStringColumn");
        }


        return accounts;


    }

    @Override
    public ArrayList<?> getAllByNumericColumn(String column, int value) {

        Connection con = DBConnection.getConnection();
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.ACCOUNT_TABLE + " WHERE " + column + "=? AND deleted=0");
            ps.setInt(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setUserId(rs.getInt(2));
                account.setName(rs.getString(3));
                account.setCurrency(rs.getInt(4));
                account.setSold(rs.getDouble(5));
                account.setType(rs.getInt(6));
                account.setDescription(rs.getString(7));
                account.setDeleted(false);
                account.setCreatedAt(rs.getDate(9));
                accounts.add(account);


            }
            logger.info("[Account Repository] - Success getByNumericColumn");
        } catch (Exception e) {

            e.printStackTrace();
            logger.error("[Account Repository] - Failed getByNumericColumn");
        }


        return accounts;
    }

    public ArrayList<Account> getAccountsForUserId(int userId) {
        return (ArrayList<Account>) getAllByNumericColumn("user_id", userId);

    }


    @Override
    public Object getById(int id) {
        return getAllByNumericColumn("id", id).get(0);
    }

    @Override
    public boolean delete(int id) {
        Connection con = DBConnection.getConnection();
        try {

            PreparedStatement stmt = con.prepareStatement("DELETE FROM " + CONSTANTS.ACCOUNT_TABLE + " WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            logger.info("[Account Repository] - deleteAccount success!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Account Repository] - deleteAccount failed!");
            return false;

        }


    }

    @Override
    public boolean update(int id, Object newObject) {
        return false;
    }

    @Override
    public boolean create(Object object) {
        Connection con = DBConnection.getConnection();
        Account account = (Account) object;
        try {
            java.util.Date date = new java.util.Date();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.ACCOUNT_TABLE + "(user_id,name,currency,sold,type,description,deleted,created_at) VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1, account.getUserId());
            ps.setString(2, account.getName());
            ps.setInt(3, account.getCurrency());
            ps.setDouble(4, account.getSold());
            ps.setInt(5, account.getType());
            ps.setString(6, account.getDescription());
            ps.setInt(7, account.isDeleted() ? 1 : 0);
            ps.setDate(8, new Date(date.getTime()));

            ps.executeUpdate();
            logger.error("[Account Repository Create] - Success");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Account Repository Create] - Error");

        }
        return false;

    }
}
