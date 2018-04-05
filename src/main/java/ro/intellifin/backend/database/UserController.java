package ro.intellifin.backend.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.intellifin.backend.models.User;
import ro.intellifin.backend.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    public static boolean userExists(User user){
        Connection c = DBConnection.getConnection();
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT ID FROM users WHERE email = ?");
            stmt.setString(1, user.getEmail());
            if(stmt.executeQuery().next())
                return true;
            return false;
        }
        catch(SQLException e){
            logger.error(e.getMessage());
            return true;

        }


    }

    public static boolean saveUser(User user){
        if(!userExists(user)){
            Connection c = DBConnection.getConnection();

            try {
                PreparedStatement stmt = c.prepareStatement("INSERT INTO users VALUES (NULL, ?,?,?,?,?,?,?,?,?,?,?,?,NULL,?,?,?)");
                stmt.setString(1, user.getEmail());
                stmt.setString(2,user.getPassword());
                stmt.setInt(3,user.isSmoker()?1:0);
                stmt.setInt(4,user.isDriverLicense()?1:0);
                stmt.setInt(5,user.isOwnsCar()?1:0);
                stmt.setInt(6,user.isMarried()?1:0);
                stmt.setInt(7,user.getNumberOfKids());
                stmt.setInt(8,user.isOwnsApartment()?1:0);
                stmt.setInt(9,user.isRentApartment()?1:0);
                stmt.setInt(10,user.getCity());
                stmt.setString(11,user.getAddress());
                stmt.setInt(12,user.getSubscriptionType());
                stmt.setInt(13,user.getAge());
                stmt.setInt(14,user.getGender());
                stmt.setString(15,user.getPhoneNumber());
                stmt.executeUpdate();


               stmt.executeUpdate();
                    return true;

            }
            catch(SQLException e){
                logger.error(e.getMessage());
                return true;

            }



        }

        return false;

    }

    public static int login(String email, String password){
        Connection c = DBConnection.getConnection();
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT ID FROM users WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                PreparedStatement stmtUpdate = c.prepareStatement("UPDATE users SET last_login =CURRENT_TIMESTAMP WHERE id = ?");
                stmtUpdate.setInt(1,rs.getInt(1));
                stmtUpdate.executeUpdate();

                return rs.getInt(1);

            }
            return 0;
        }
        catch(SQLException e) {
            logger.error(e.getMessage());
            return 0;

        }
    }

    public static User getUserEmailById(int userId){
        Connection c = DBConnection.getConnection();
        try {
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1,userId);
          ResultSet rs= stmt.executeQuery();
              rs.next();
              return rs.getString(1);
        }
        catch(SQLException e){
            logger.error(e.getMessage());
            return " ";

        }


    }
}
