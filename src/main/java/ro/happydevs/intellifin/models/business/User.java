package ro.happydevs.intellifin.models.business;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 9
 * @Title: User Entity Bean
 * @Description: Representation of an User inside IntelliFin Platform
 * @Links: City
 */

@Entity
@Table(name = "intelli_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date createdAt;

    private boolean deleted;

    private String email;
    private String password;
    private boolean smoker;
    private boolean driverLicense;
    private boolean ownsCar;
    private boolean married;
    private int numberOfKids;
    private boolean ownsApartment;
    private boolean rentApartment;
    private Long city;
    private String address;
    //see CONSTANTS for type descriptions
    private int subscriptionType;
    private Timestamp lastLogin;
    private Date subscriptionExpiry;
    private int age;
    //see CONSTANTS for gender descriptions
    private int gender;
    private String phoneNumber;
    private Date birthDate;
    private boolean paidForSubscription = false;
    private String firstName;
    private String lastName;
    private int numberOfCigarettesDaily;
    private double salary;
    private double weight;
    private double height;
    private boolean employed;
    private String employerName;
    private boolean studies;
    private String facultyName;
    private boolean accountConfigured = false;
    //transient
    @Transient
    private ArrayList<Account> accounts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberOfCigarettesDaily() {
        return numberOfCigarettesDaily;
    }

    public void setNumberOfCigarettesDaily(int numberOfCigarettesDaily) {
        this.numberOfCigarettesDaily = numberOfCigarettesDaily;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public boolean isStudies() {
        return studies;
    }

    public void setStudies(boolean studies) {
        this.studies = studies;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public boolean isPaidForSubscription() {
        return paidForSubscription;
    }

    public void setPaidForSubscription(boolean paidForSubscription) {
        this.paidForSubscription = paidForSubscription;
    }

    public boolean isAccountConfigured() {
        return accountConfigured;
    }

    public void setAccountConfigured(boolean accountConfigured) {
        this.accountConfigured = accountConfigured;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(boolean driverLicense) {
        this.driverLicense = driverLicense;
    }

    public boolean isOwnsCar() {
        return ownsCar;
    }

    public void setOwnsCar(boolean ownsCar) {
        this.ownsCar = ownsCar;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getNumberOfKids() {
        return numberOfKids;
    }

    public void setNumberOfKids(int numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public boolean isOwnsApartment() {
        return ownsApartment;
    }

    public void setOwnsApartment(boolean ownsApartment) {
        this.ownsApartment = ownsApartment;
    }

    public boolean isRentApartment() {
        return rentApartment;
    }

    public void setRentApartment(boolean rentApartment) {
        this.rentApartment = rentApartment;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(int subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getSubscriptionExpiry() {
        return subscriptionExpiry;
    }

    public void setSubscriptionExpiry(Date subscriptionExpiry) {
        this.subscriptionExpiry = subscriptionExpiry;
    }
}
