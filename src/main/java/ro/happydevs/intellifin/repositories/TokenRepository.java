package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;
import ro.happydevs.intellifin.utils.security.TokenGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TokenRepository {


    private static Logger logger = LoggerFactory.getLogger(TokenRepository.class);

    private UserRepository userRepository = new UserRepository();


    /**
     * @param token
     * @return user by id
     */
    public User getUserByToken(String token) {
        Connection con = DBConnection.getConnection();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT user_id FROM " + CONSTANTS.TOKEN_TABLE + " WHERE token = ? AND valid = 1");
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                logger.info("[User by Token] - success");
                return (User) userRepository.getById(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[User by Token] - exception occured");

        }

        logger.info("[User by Token] - failed");
        return null;


    }

    /**
     * @param userId
     * @return created token
     */

    public String createTokenForUser(int userId) {
        Connection con = DBConnection.getConnection();

        try {
            String token = TokenGenerator.generateToken();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO " + CONSTANTS.TOKEN_TABLE + "(user_id,token,valid) VALUES(?,?,?)");
            stmt.setInt(1, userId);
            stmt.setString(2, token);
            stmt.setInt(3, 1);
            stmt.executeUpdate();

            logger.info("[Create Token for User] - success");
            return token;


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Create Token for User] - exception occurred");

        }

        logger.info("[Create Token for User] - failed");
        return null;


    }
}
