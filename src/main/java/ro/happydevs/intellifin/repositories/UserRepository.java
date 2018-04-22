package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class UserRepository implements IRepository {

    private static Logger logger = LoggerFactory.getLogger(UserRepository.class);


    @Override
    public ArrayList<User> getAll() {

        return getAllByNumericColumn("deleted", 0);
    }

    @Override
    public ArrayList<User> getAllByStringColumn(String column, String value) {
        Connection con = DBConnection.getConnection();
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.USER_TABLE + " WHERE " + column + " = ? AND deleted = 0");
            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            logger.info("[User Get By Column] - Successful!");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setSmoker(rs.getInt(4) == 1);
                user.setDriverLicense(rs.getInt(5) == 1);
                user.setOwnsCar(rs.getInt(6) == 1);
                user.setMarried(rs.getInt(7) == 1);
                user.setNumberOfKids(rs.getInt(8));
                user.setOwnsApartment(rs.getInt(9) == 1);
                user.setRentApartment(rs.getInt(10) == 1);
                user.setCity(rs.getInt(11));
                user.setAddress(rs.getString(12));
                user.setSubscriptionType(rs.getInt(13));
                user.setLastLogin(rs.getTimestamp(14));
                user.setAge(rs.getInt(15));
                user.setGender(rs.getInt(16));
                user.setPhoneNumber(rs.getString(17));
                user.setBirthDate(rs.getDate(18));
                user.setCreatedAt(rs.getDate(19));
                user.setDeleted(rs.getInt(20) == 1);
                users.add(user);

            }
        } catch (Exception e) {
            logger.error("[User Get By Column] - error occurred");
            e.printStackTrace();

        }


        return users;

    }

    @Override
    public ArrayList<User> getAllByNumericColumn(String column, int value) {
        Connection con = DBConnection.getConnection();
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.USER_TABLE + " WHERE " + column + " = ? AND deleted = 0");
            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            logger.info("[User Get By Column] - Successful!");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setSmoker(rs.getInt(4) == 1);
                user.setDriverLicense(rs.getInt(5) == 1);
                user.setOwnsCar(rs.getInt(6) == 1);
                user.setMarried(rs.getInt(7) == 1);
                user.setNumberOfKids(rs.getInt(8));
                user.setOwnsApartment(rs.getInt(9) == 1);
                user.setRentApartment(rs.getInt(10) == 1);
                user.setCity(rs.getInt(11));
                user.setAddress(rs.getString(12));
                user.setSubscriptionType(rs.getInt(13));
                user.setLastLogin(rs.getTimestamp(14));
                user.setAge(rs.getInt(15));
                user.setGender(rs.getInt(16));
                user.setPhoneNumber(rs.getString(17));
                user.setBirthDate(rs.getDate(18));
                user.setCreatedAt(rs.getDate(19));
                user.setDeleted(rs.getInt(20) == 1);
                users.add(user);

            }
        } catch (Exception e) {
            logger.error("[User Get By Column] - error occurred");
            e.printStackTrace();

        }


        return users;


    }

    @Override
    public Object getById(int id) {
        Connection con = DBConnection.getConnection();
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.USER_TABLE + " WHERE id = ? AND deleted = 0");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            logger.info("[User Get By Column] - Successful!");
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setSmoker(rs.getInt(4) == 1);
                user.setDriverLicense(rs.getInt(5) == 1);
                user.setOwnsCar(rs.getInt(6) == 1);
                user.setMarried(rs.getInt(7) == 1);
                user.setNumberOfKids(rs.getInt(8));
                user.setOwnsApartment(rs.getInt(9) == 1);
                user.setRentApartment(rs.getInt(10) == 1);
                user.setCity(rs.getInt(11));
                user.setAddress(rs.getString(12));
                user.setSubscriptionType(rs.getInt(13));
                user.setLastLogin(rs.getTimestamp(14));
                user.setAge(rs.getInt(15));
                user.setGender(rs.getInt(16));
                user.setPhoneNumber(rs.getString(17));
                user.setBirthDate(rs.getDate(18));
                user.setCreatedAt(rs.getDate(19));
                user.setDeleted(rs.getInt(20) == 1);
                return user;

            }
        } catch (Exception e) {
            logger.error("[User Get By Column] - error occurred");
            e.printStackTrace();

        }


        return null;
    }

    @Override
    public boolean delete(int id) {
        Connection con = DBConnection.getConnection();
        try {

            PreparedStatement stmt = con.prepareStatement("UPDATE " + CONSTANTS.USER_TABLE + " SET deleted = 1 WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            logger.info("[User Repository] - deleteUser success!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[User Repository] - deleteUser failed!");
        }
        return false;
    }

    @Override
    public boolean update(Object object, String token) {return false;}

    public boolean update(Object newObject) {
        Connection con = DBConnection.getConnection();
        User u = ((User)newObject);

        try {
            PreparedStatement ps = con.prepareStatement("UPDATE " + CONSTANTS.USER_TABLE + " SET email = ?, password = ?, smoker = ?, driver_license = ?, " +
                    "owns_car = ?, married = ?, number_of_kids = ?, owns_apartment = ?, rent_apartment = ?, city = ?, address = ?, " +
                    "subscription_type = ?, age = ?, gender = ?, phone_number = ?, birth_date = ? WHERE id = " + u.getId());
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setInt(3,u.isSmoker()?1:0);
            ps.setInt(4,u.isDriverLicense()?1:0);
            ps.setInt(5,u.isOwnsCar()?1:0);
            ps.setInt(6,u.isMarried()?1:0);
            ps.setInt(7,u.getNumberOfKids());
            ps.setInt(8,u.isOwnsApartment()?1:0);
            ps.setInt(9,u.isRentApartment()?1:0);
            ps.setInt(10, u.getCity());
            ps.setString(11, u.getAddress());
            ps.setInt(12, u.getSubscriptionType());
            ps.setInt(13,u.getAge());
            ps.setInt(14,u.getGender());
            ps.setString(15,u.getPhoneNumber());
            ps.setDate(16,u.getBirthDate());

            ps.executeUpdate();
            logger.error("[User Repository Update] - Success");
            return true;
        }

        catch(Exception e) {
            e.printStackTrace();
            logger.error("[User Repository Update] - Error");
        }
        return false;

    }

    @Override
    public boolean create(Object object) {
        User userToCreate = (User) object;
        Connection con = DBConnection.getConnection();
        if (getAllByStringColumn("email", userToCreate.getEmail()).size() == 0) {

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.USER_TABLE + "(email,password,smoker,driver_license,owns_car,married,number_of_kids,owns_apartment,rent_apartment," +
                        "city,address,subscription_type,last_login,age,gender,phone_number,birth_date,created_at,deleted)" +
                        " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                Date date = new Date();
                ps.setString(1, userToCreate.getEmail());
                ps.setString(2, userToCreate.getPassword());
                ps.setInt(3, userToCreate.isSmoker() ? 1 : 0);
                ps.setInt(4, userToCreate.isDriverLicense() ? 1 : 0);
                ps.setInt(5, userToCreate.isOwnsCar() ? 1 : 0);
                ps.setInt(6, userToCreate.isMarried() ? 1 : 0);
                ps.setInt(7, userToCreate.getNumberOfKids());
                ps.setInt(8, userToCreate.isOwnsApartment() ? 1 : 0);
                ps.setInt(9, userToCreate.isRentApartment() ? 1 : 0);
                ps.setInt(10, userToCreate.getCity());
                ps.setString(11, userToCreate.getAddress());
                ps.setInt(12, userToCreate.getSubscriptionType());
                ps.setTimestamp(13, new Timestamp(date.getTime()));
                ps.setInt(14, userToCreate.getAge());
                ps.setInt(15, userToCreate.getGender());
                ps.setString(16, userToCreate.getPhoneNumber());
                ps.setDate(17, userToCreate.getBirthDate());
                ps.setDate(18, new java.sql.Date(date.getTime()));
                ps.setInt(19, 0);


                ps.executeUpdate();

                logger.info("[Create User] - Successful!");

            } catch (Exception e) {
                logger.error("[Create user] - error occurred");
                e.printStackTrace();

            }


            return true;
        }
        logger.info("[Create User] - Already exists!");
        return false;


    }

    public int loginUser(String email, String password) {
        Connection con = DBConnection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM " + CONSTANTS.USER_TABLE + " WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logger.info("[User Login] - Successful!");
                return rs.getInt(1);

            }
        } catch (Exception e) {
            logger.error("[User Login] - error occurred");
            e.printStackTrace();

        }

        logger.info("[User Login] - Failed!");
        return 0;


    }


}
