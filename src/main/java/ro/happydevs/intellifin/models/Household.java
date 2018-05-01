package ro.happydevs.intellifin.models;

import java.util.ArrayList;

public class Household extends User {

    private int id;
    private int userID;
    private String nameHousehold; // household's name
    private String usernameHousehold; // User's name in the household (not the name of the Household)
    private String addressHousehold;
    private int cityHousehold;
    private ArrayList<User> members; //
    private boolean deleted;
    private int isHouseHoldAdmin;
    private int idHousehold; //id to differentiate Households



    public String getNameHousehold() {
        return nameHousehold;
    }

    public void setNameHousehold(String nameHousehold) {
        this.nameHousehold = nameHousehold;
    }

    public String getAddressHousehold() {
        return addressHousehold;
    }

    public void setAddressHousehold(String addressHousehold) {
        this.addressHousehold = addressHousehold;
    }

    public int getCityHousehold() {
        return cityHousehold;
    }

    public void setCityHousehold(int cityHousehold) {
        this.cityHousehold = cityHousehold;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getIdHousehold() {
        return idHousehold;
    }

    public void setIdHousehold(int idHousehold) {
        this.idHousehold = idHousehold;
    }

    public int getIsHouseHoldAdmin() {
        return isHouseHoldAdmin;
    }

    public void setIsHouseHoldAdmin(int isHouseHoldAdmin) {
        this.isHouseHoldAdmin = isHouseHoldAdmin;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getUsernameHousehold() {
        return usernameHousehold;
    }

    public void setUsernameHousehold(String usernameHousehold) {
        this.usernameHousehold = usernameHousehold;
    }
}