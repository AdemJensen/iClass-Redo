package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class PhoneValidator extends Validator {
    public PhoneValidator(String str) {
        super(str);
        requireMinLen(11);
        requireMaxLen(11);
        requireDigit();
    }

    @Override
    public String[] echoRequirements(String displayName) {
        return new String[]{displayName + "必须为有效的11位号码"};
    }

    @Override
    public String[] validate(String displayName) {
        String [] res = super.validate();
        if (res.length > 0) return new String[]{displayName + "必须为有效的11位号码"};
        else return new String[0];
    }
}
