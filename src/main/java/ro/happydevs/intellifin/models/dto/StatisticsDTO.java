package ro.happydevs.intellifin.models.dto;

import ro.happydevs.intellifin.models.business.Activity;
import ro.happydevs.intellifin.models.business.Transaction;

import java.util.List;

public class StatisticsDTO {

    private double todaySpendings;
    private double todayEarnings;
    private double monthSpendings;
    private double monthEarnings;
    private List<Activity> latestActivity;
    private List<Transaction> latestTransactions;

    public double getTodaySpendings() {
        return todaySpendings;
    }

    public void setTodaySpendings(double todaySpendings) {
        this.todaySpendings = todaySpendings;
    }

    public double getTodayEarnings() {
        return todayEarnings;
    }

    public void setTodayEarnings(double todayEarnings) {
        this.todayEarnings = todayEarnings;
    }

    public double getMonthSpendings() {
        return monthSpendings;
    }

    public void setMonthSpendings(double monthSpendings) {
        this.monthSpendings = monthSpendings;
    }

    public double getMonthEarnings() {
        return monthEarnings;
    }

    public void setMonthEarnings(double monthEarnings) {
        this.monthEarnings = monthEarnings;
    }

    public List<Activity> getLatestActivity() {
        return latestActivity;
    }

    public void setLatestActivity(List<Activity> latestActivity) {
        this.latestActivity = latestActivity;
    }

    public List<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setLatestTransactions(List<Transaction> latestTransactions) {
        this.latestTransactions = latestTransactions;
    }
}
