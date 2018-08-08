package ro.happydevs.intellifin.models.dto.business.user;

import java.util.Date;

public class UserConfirmationDTO {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private boolean married;
    private boolean employed;
    private String employerName;
    private boolean hasStudies;
    private double salary;
    private double weight;
    private double height;
    private boolean smoker;
    private int numberOfCigarettesDaily;
    private int subscriptionType;
    private String facultyName;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

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

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
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

    public boolean isHasStudies() {
        return hasStudies;
    }

    public void setHasStudies(boolean hasStudies) {
        this.hasStudies = hasStudies;
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

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public int getNumberOfCigarettesDaily() {
        return numberOfCigarettesDaily;
    }

    public void setNumberOfCigarettesDaily(int numberOfCigarettesDaily) {
        this.numberOfCigarettesDaily = numberOfCigarettesDaily;
    }

    public int getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(int subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
