package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class PasswordValidator extends Validator {
    public PasswordValidator(String str) {
        super(str);
        requireMinLen(8);
        requireMaxLen(16);
        requireAscii();
    }
}
