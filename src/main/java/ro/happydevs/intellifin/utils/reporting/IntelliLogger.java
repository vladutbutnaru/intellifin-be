package ro.happydevs.intellifin.utils.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.reporting.LogLine;
import ro.happydevs.intellifin.repositories.LogLineRepository;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;
import ro.happydevs.intellifin.utils.factory.ActivityFactory;

@Component
public class IntelliLogger {

    @Autowired
    LogLineRepository logLineRepository;

    @Autowired
    ActivityFactory activityFactory;

    public void createLog(LogLine logLine) {

        logLineRepository.save(logLine);

    }

    public void createAccountLog(String accountName, Long userId, Long accountId){
        LogLine logLine = new LogLine(userId, CONSTANTS.ACTION_CREATE_ACCOUNT);
        createLog(logLine);

        activityFactory.addActivity("Ai creat un cont nou!","Felicitari! Ai inregistrat cu succes contul " + accountName,"view-account.html?id=" + accountId, "account-added.png",userId);

    }

    public void deleteAccountLog(String accountName, Long userId){
        LogLine logLine = new LogLine(userId, CONSTANTS.ACTION_DELETE_ACCOUNT);
        createLog(logLine);

        activityFactory.addActivity("Ai sters un cont!","Ai sters contul " + accountName,"#", "account-deleted.png", userId);

    }

    public void updateAccountLog(String accountName, Long userId, Long accountId){
        LogLine logLine = new LogLine(userId, CONSTANTS.ACTION_UPDATE_ACCOUNT);
        createLog(logLine);

        activityFactory.addActivity("Ai modificat un cont!","Ai modificat informatiile contului " + accountName,"view-account.html?id=" + accountId, "account-updated.png", userId);

    }

}
