package ro.happydevs.intellifin.utils.validators;

import ro.happydevs.intellifin.models.dto.business.user.UserRegisterDTO;
import ro.happydevs.intellifin.utils.constants.CONSTANTS;

public class PasswordValidator {

    public static String validateRegistrationPassword(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getPasswordConfirm())) {
            return CONSTANTS.REGISTRATION_PASSWORDS_MUST_MATCH;
        }
        if (userRegisterDTO.getPassword().length() < 8) {
            return CONSTANTS.REGISTRATION_PASSWORD_TOO_SHORT;
        }

        return "OK";
    }
}
