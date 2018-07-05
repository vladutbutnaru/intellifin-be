package ro.happydevs.intellifin.utils.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.reporting.LogLine;
import ro.happydevs.intellifin.repositories.LogLineRepository;

@Component
public class IntelliLogger {

    @Autowired
    LogLineRepository logLineRepository;

    public void createLog(LogLine logLine) {
        logLineRepository.save(logLine);

    }

}
