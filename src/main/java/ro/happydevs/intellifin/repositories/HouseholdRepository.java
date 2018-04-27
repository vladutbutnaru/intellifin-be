package ro.happydevs.intellifin.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.models.Household;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HouseholdRepository implements IRepository{

    private static Logger logger = LoggerFactory.getLogger(HouseholdRepository.class);


    @Override //list of households
    public ArrayList<?> getAll() {return null;}
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

    @Override //delete Household
    public boolean delete(int id) {
        Connection con = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE " + CONSTANTS.HOUSEHOLDS_TABLE + " SET deleted = 1 WHERE house_id = ?");

            ps.setInt(1,id);
            ps.executeUpdate();
            logger.info("[HouseholdRepository] - delete Household success!");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            logger.error("[HouseholdRepository] - delete Household failed!");
        }

        return false;
    }

    @Override
    public boolean update(Object newObject, String token) {
        return false;
    }

    @Override //creates Household
    public boolean create(Object object) {

        Connection con = DBConnection.getConnection();
        Household h = (Household) object;

        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.HOUSEHOLDS_TABLE + "(name,address,city,deleted,is_admin,house_id,userID) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1,h.getNameHousehold());
            ps.setString(2,h.getAddressHousehold());
            ps.setInt(3,h.getCityHousehold());
            ps.setBoolean(4,false);
            ps.setInt(5,1); //daca e admin e 1
            ps.setInt(6,h.getIdHousehold());
            ps.setInt(7,h.getUserID());

            ps.executeUpdate();
            logger.info("[Household Repository Create] - Success");
            return true;

        }catch(Exception e){
            e.printStackTrace();
            logger.error("[Household Repository Create] - Error");
        }
        return false;
    }

    //List of members that belong to a household
    public ArrayList<?> getHouseholdMembers(int idHousehold){

        Connection con = DBConnection.getConnection();
        ArrayList<Household> householdMembers = new ArrayList<Household>();//ArrayList<User> members
        try{
            PreparedStatement ps = con.prepareStatement("SELECT userID FROM " + CONSTANTS.HOUSEHOLDS_TABLE + " WHERE house_id =?" );
            ps.setInt(1,idHousehold);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                Household h = new Household();
                h.setUserID(rs.getInt(1));
                householdMembers.add(h);


            }

            logger.info("[Household Repository] - Success getHouseholdMembers");

        }catch(Exception e){
            e.printStackTrace();
            logger.error("[Household Repository] - Failed getHouseholdMembers");
        }


        return householdMembers;
    }

    //adds individual member to household (aka is_admin = 0 )
    public boolean addMemberToHousehold(Object object){

        Connection con = DBConnection.getConnection();
        Household h = (Household) object;

        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO " + CONSTANTS.HOUSEHOLDS_TABLE + "(name,address,city,deleted,is_admin,house_id,userID) VALUES(?,?,?,?,?,?,?)");

            ps.setString(1,h.getNameHousehold());
            ps.setString(2,h.getAddressHousehold());
            ps.setInt(3,h.getCityHousehold());
            ps.setBoolean(4,false);
            ps.setInt(5,0);
            ps.setInt(6,h.getIdHousehold());
            ps.setInt(7,h.getUserID());

            ps.executeUpdate();
            logger.info("[Household Repository addMemberToHousehold] - Success");
            return true;

        }catch(Exception e){
            e.printStackTrace();
            logger.error("[Household Repository addMemberToHousehold] - Error");
        }
        return false;
    }

    //Remove Member from HouseHold
    public boolean deleteMemberFromHousehold(Object object){

        Connection con = DBConnection.getConnection();
        Household h = (Household) object;
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE " + CONSTANTS.HOUSEHOLDS_TABLE + " SET deleted = 1 WHERE house_id = ? AND userID = ?");
            ps.setInt(1,h.getIdHousehold());
            ps.setInt(2,h.getUserID());
            ps.executeUpdate();
            logger.info("[HouseholdRepository] - delete deleteMemberFromHousehold success!");
            return true;
            }catch(Exception e){
            e.printStackTrace();
            logger.error("[HouseholdRepository] - delete deleteMemberFromHousehold failed!");
        }

        return false;

    }
}
