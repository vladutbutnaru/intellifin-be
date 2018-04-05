package ro.happydevs.intellifin.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TokenGenerator {

    private static RandomString session = new RandomString();
    private static Logger logger = LoggerFactory.getLogger(TokenGenerator.class);

    public static String generateToken() {
        logger.info("[Token Generator] - Generating authentication token... ");

        return session.nextString();

    }
}
