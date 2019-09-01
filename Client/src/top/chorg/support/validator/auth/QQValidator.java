package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class QQValidator extends Validator {
    public QQValidator(String str) {
        super(str);
        requireDigit();
    }
}
