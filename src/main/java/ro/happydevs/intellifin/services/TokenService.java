package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.business.User;
import ro.happydevs.intellifin.models.security.Token;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.repositories.UserRepository;
import ro.happydevs.intellifin.utils.reporting.IntelliLogger;
import ro.happydevs.intellifin.utils.security.TokenGenerator;

import java.util.List;

@Service
public class TokenService {

    private static Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    IntelliLogger intelliLogger;

    public boolean verifyToken(String code) {
        logger.info("[Verify Token] - Verifying token");

        if(tokenRepository.findByCode(code)!=null){

        }
        return tokenRepository.findByCode(code) != null;

    }

    public User getUserByToken(String code) {


        return userRepository.findById(tokenRepository.findByCode(code).getUserId()).get();
    }

    public String createToken(Long userId) {
        Token token = new Token();
        token.setCode(TokenGenerator.generateToken());
        token.setValid(true);
        token.setUserId(userId);
        token.setDeleted(false);
        //24 hours lifespan
        token.setLifeSpan(60*60*24);

        tokenRepository.save(token);
        return token.getCode();


    }

    public List<Token> getValidTokens(){
        return tokenRepository.findValidTokens();
    }
}
