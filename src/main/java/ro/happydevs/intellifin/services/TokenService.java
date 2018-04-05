package ro.happydevs.intellifin.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.happydevs.intellifin.repositories.TokenRepository;

public class TokenService {

    private static Logger logger = LoggerFactory.getLogger(TokenService.class);

    private TokenRepository tokenRepository = new TokenRepository();

    public boolean verifyToken(String token) {
        logger.info("[Verify Token] - Verifying token");
        return tokenRepository.getUserByToken(token) != null;

    }

    public String createToken(int userId) {
        return tokenRepository.createTokenForUser(userId);


    }

}
