package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class PhoneValidator extends Validator {
    public PhoneValidator(String str) {
        super(str);
        requireDigit();
        requireCustomValidation();
    }

    @Override
    protected String[] validateCustomRequirement(String displayName) {
        if (str.length() != 11) return new String[] {displayName + "必须为有效的11位号码"};
        return new String[0];
    }

    @Override
    public String[] echoRequirements(String displayName) {
        return new String[]{displayName + "必须为有效的11位号码"};
    }
}
