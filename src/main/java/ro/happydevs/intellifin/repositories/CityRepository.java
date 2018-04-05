package ro.happydevs.intellifin.repositories;

import ro.happydevs.intellifin.models.City;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CityRepository implements IRepository {
    @Override
    public ArrayList<?> getAll() {
        Connection con = DBConnection.getConnection();
        ArrayList<City> cities = new ArrayList<City>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + CONSTANTS.CITY_TABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt(1));
                city.setName(rs.getString(2));
                cities.add(city);

            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return cities;
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
        City city = (City) object;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.CITY_TABLE + "(name,created_at,deleted) VALUES(?,?,?)");
            ps.setString(1, city.getName());
            ps.setDate(2, null);
            ps.setInt(3, 0);
            ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        return true;
    }
}
