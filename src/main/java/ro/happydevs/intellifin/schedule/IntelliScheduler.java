package ro.happydevs.intellifin.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.happydevs.intellifin.models.security.Token;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.services.TokenService;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 3
 * @Title: Spring Component Scheduler
 * @Description: The generic Scheduler class that provides recurrent actions inside IntelliFin platform
 * @Links: none
 */


@Component
public class IntelliScheduler {

    @Autowired
    TokenService tokenService;


    @Autowired
    TokenRepository tokenRepository;

    //scheduler that expires tokens after the lifespan is over and decreases the lifespan
    @Scheduled(fixedRate = 1000)
    public void expireTokens() {
        for (Token t : tokenService.getValidTokens()) {

            if (t.getLifeSpan() == 1) {
                t.setLifeSpan(0);
                t.setValid(false);
                tokenRepository.save(t);

            } else {
                t.setLifeSpan(t.getLifeSpan() - 1);
                tokenRepository.save(t);

            }

        }

    }


}
