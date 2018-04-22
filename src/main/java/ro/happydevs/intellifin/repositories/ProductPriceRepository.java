package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.ProductPrice;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ProductPriceRepository implements IRepository {

    private static Logger logger = LoggerFactory.getLogger(ProductPriceRepository.class);

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
    public boolean update(Object newObject, String token) {
        return false;
    }

    @Override
    public boolean create(Object object) {
        Connection con = DBConnection.getConnection();
        ProductPrice price = (ProductPrice) object;
        try {
            java.util.Date date = new java.util.Date();
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.PRODUCT_PRICE_TABLE + "(product_id,shop_id,price,created_at,deleted) VALUES(?,?,?,?,?)");
            ps.setInt(1, price.getProductId());
            ps.setInt(2, price.getShopId());
            ps.setDouble(3, price.getPrice());
            ps.setDate(4, new Date(date.getTime()));
            ps.setInt(5, price.isDeleted() ? 1 : 0);


            ps.executeUpdate();
            logger.info("[Product Price Repository] - Create success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Product Price Repository] - Create error");
            return false;

        }
    }
}
