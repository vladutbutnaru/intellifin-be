package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.happydevs.intellifin.models.Token;
import ro.happydevs.intellifin.models.User;
import ro.happydevs.intellifin.repositories.TokenRepository;
import ro.happydevs.intellifin.repositories.UserRepository;
import ro.happydevs.intellifin.utils.security.TokenGenerator;

@Service
public class TokenService {

    private static Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    public boolean verifyToken(String code) {
        logger.info("[Verify Token] - Verifying token");
        if(tokenRepository.findByCode(code)!=null)
        return true;

        return false;

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
        tokenRepository.save(token);
        return token.getCode();


    }

}
