package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class StuNumberValidator extends Validator {
    public StuNumberValidator(String str) {
        super(str);
        requireMinLen(12);
        requireMaxLen(12);
        requireDigit();
    }

    @Override
    public String[] echoRequirements(String displayName) {
        return new String[]{displayName + "必须为有效的12位号码"};
    }

    @Override
    public String[] validate(String displayName) {
        String [] res = super.validate();
        if (res.length > 0) return new String[]{displayName + "必须为有效的12位号码"};
        else return new String[0];
    }
}
