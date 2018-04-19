package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Notification;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class NotificationRepository implements IRepository {

    private static Logger logger = LoggerFactory.getLogger(NotificationRepository.class);


    @Override
    public ArrayList<?> getAll() {
        return getAllByNumericColumn("deleted", 0);
    }

    @Override
    public ArrayList<?> getAllByStringColumn(String column, String value) {
        Connection con = DBConnection.getConnection();
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.NOTIFICATION_TABLE + " WHERE " + column + " = ? AND deleted = 0 ORDER BY id DESC");
            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            logger.info("[Notification Get By Column] - Successful!");
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt(1));
                notification.setIcon(rs.getString(2));
                notification.setText(rs.getString(3));
                notification.setRead(rs.getInt(4) == 1);
                notification.setUserId(rs.getInt(5));
                notification.setLink(rs.getString(6));
                notification.setCreatedAt(rs.getDate(7));
                notification.setDeleted(rs.getInt(8) == 1);


            }
        } catch (Exception e) {
            logger.error("[Notification Get By Column] - error occurred");
            e.printStackTrace();

        }


        return notifications;
    }

    @Override
    public ArrayList<?> getAllByNumericColumn(String column, int value) {
        Connection con = DBConnection.getConnection();
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.NOTIFICATION_TABLE + " WHERE " + column + " = ? AND deleted = 0 ORDER BY id DESC");
            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            logger.info("[Notification Get By Column] - Successful!");
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt(1));
                notification.setIcon(rs.getString(2));
                notification.setText(rs.getString(3));
                notification.setRead(rs.getInt(4) == 1);
                notification.setUserId(rs.getInt(5));
                notification.setLink(rs.getString(6));
                notification.setCreatedAt(rs.getDate(7));
                notification.setDeleted(rs.getInt(8) == 1);
                notifications.add(notification);


            }
        } catch (Exception e) {
            logger.error("[Notification Get By Column] - error occurred");
            e.printStackTrace();

        }


        return notifications;
    }

    @Override
    public Object getById(int id) {
        return getAllByNumericColumn("id", id);
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
        Notification notification = (Notification) object;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.NOTIFICATION_TABLE + "(icon,notification_text,notification_read,user_id,link,created_at,deleted) VALUES(?,?,?,?,?,?,?)");
            Date date = new Date();
            ps.setString(1, notification.getIcon());
            ps.setString(2, notification.getText());
            ps.setInt(3, notification.isRead() ? 1 : 0);
            ps.setInt(4, notification.getUserId());
            ps.setString(5, notification.getLink());
            ps.setDate(6, new java.sql.Date(date.getTime()));
            ps.setInt(7, 0);


            ps.executeUpdate();

            logger.info("[Notification Create] - Successful!");
            return true;

        } catch (Exception e) {
            logger.error("[Notification Create] - error occurred");
            e.printStackTrace();

        }


        return false;
    }
}
