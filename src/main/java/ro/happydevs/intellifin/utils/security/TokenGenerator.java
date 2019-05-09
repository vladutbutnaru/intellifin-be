package ro.happydevs.intellifin.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Vlad Butnaru
 * @Version: 1.0
 * @Revision: 2
 * @Title: Token generation class
 * @Description: Provides token generation actions for inside or outside IntelliFin
 * @Links: none
 */

public class TokenGenerator {

    private static RandomString session = new RandomString(50);
    private static Logger logger = LoggerFactory.getLogger(TokenGenerator.class);

    public static String generateToken() {
        logger.info("[Token Generator] - Generating authentication token... ");

        return session.nextString();

    }
}
