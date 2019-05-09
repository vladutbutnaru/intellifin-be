package ro.happydevs.intellifin.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.Transaction;
import ro.happydevs.intellifin.models.dto.dashboard.StatisticsDTO;
import ro.happydevs.intellifin.services.*;

import java.util.List;

@Service
public class StatisticsService {


    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AccountService accountService;

    @Autowired
    ActivityService activityService;

    public StatisticsDTO getUserStatisticsDashboard(String token) {

        StatisticsDTO statisticsDTO = new StatisticsDTO();


        //get today tansactions
        List<Transaction> todayTransactions = transactionService.getTodayTransactions(token);

        statisticsDTO.setTodayEarnings(0);
        statisticsDTO.setTodaySpendings(0);

        for (Transaction t : todayTransactions) {
            if (t.isEarning())
                statisticsDTO.setTodayEarnings(statisticsDTO.getTodayEarnings() + t.getAmount());
            else
                statisticsDTO.setTodaySpendings(statisticsDTO.getTodaySpendings() + t.getAmount());


        }

        //get month transactions
        todayTransactions = transactionService.getMonthlyTransactions(token);

        statisticsDTO.setMonthEarnings(0);
        statisticsDTO.setMonthSpendings(0);

        for (Transaction t : todayTransactions) {
            if (t.isEarning())
                statisticsDTO.setMonthEarnings(statisticsDTO.getMonthEarnings() + t.getAmount());
            else
                statisticsDTO.setMonthSpendings(statisticsDTO.getMonthSpendings() + t.getAmount());


        }

        //get latest transactions
        statisticsDTO.setLatestTransactions(todayTransactions);

        //get latest activity for user
        statisticsDTO.setLatestActivity(activityService.getUserActivity(token));


        //return the statistics object
        return statisticsDTO;
    }


}
