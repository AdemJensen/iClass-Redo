package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class StuNumberValidator extends Validator {
    public StuNumberValidator(String str) {
        super(str);
        requireDigit();
        requireCustomValidation();
    }

    @Override
    protected String[] validateCustomRequirement(String displayName) {
        if (str.length() != 12) return new String[] {displayName + "必须为有效的12位号码"};
        return new String[0];
    }

    @Override
    public String[] echoRequirements(String displayName) {
        return new String[]{displayName + "必须为有效的12位号码"};
    }
}
