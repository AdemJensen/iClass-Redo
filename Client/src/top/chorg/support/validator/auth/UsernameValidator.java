package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class UsernameValidator extends Validator {
    public UsernameValidator(String str) {
        super(str);
        requireMinLen(1);
        requireMaxLen(16);
        requireAscii();
    }
}
