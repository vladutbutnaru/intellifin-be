package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Product;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ProductRepository implements IRepository {

    private static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Override
    public ArrayList<?> getAll() {
        return getAllByNumericColumn("deleted", 0);


    }

    @Override
    public ArrayList<?> getAllByStringColumn(String column, String value) {

        Connection con = DBConnection.getConnection();
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + CONSTANTS.PRODUCT_TABLE + " WHERE " + column + "=? AND deleted=0");
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImagePath(rs.getString(3));
                product.setCategory(rs.getInt(4));
                product.setCreatedAt(rs.getDate(5));
                product.setDeleted(rs.getInt(6) > 0);
                products.add(product);


            }
            logger.info("[Product Repository] - getAllByStringColumn success!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Product Repository] - Get All By String failed");

        }


        return products;
    }

    @Override
    public ArrayList<?> getAllByNumericColumn(String column, int value) {
        Connection con = DBConnection.getConnection();
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + CONSTANTS.PRODUCT_TABLE + " WHERE " + column + "=? AND deleted=0");
            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImagePath(rs.getString(3));
                product.setCategory(rs.getInt(4));
                product.setCreatedAt(rs.getDate(5));
                product.setDeleted(rs.getInt(6) > 0);
                products.add(product);


            }
            logger.info("[Product Repository] - getAllByNumericColumn success!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Product Repository] - Get All By Numeric failed");

        }


        return products;
    }

    @Override
    public Object getById(int id) {
        return getAllByNumericColumn("id", id).get(0);
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
        Product product = (Product) object;

        try {
            Date date = new Date();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.PRODUCT_TABLE + "(name,image_path,category,created_at,deleted) VALUES(?,?,?,?,?)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getImagePath());
            ps.setInt(3, product.getCategory());
            ps.setDate(4, new java.sql.Date(date.getTime()));
            ps.setInt(5, product.isDeleted() ? 1 : 0);
            ps.executeUpdate();
            logger.info("[Product Repository] - Create product success!");
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Product Repository] - Create product failed!");
            return false;

        }
    }
}
