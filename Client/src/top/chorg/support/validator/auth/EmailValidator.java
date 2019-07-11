package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class EmailValidator extends Validator {
    public EmailValidator(String str) {
        super(str);
        requireCustomValidation();
    }

    @Override
    protected String[] validateCustomRequirement(String displayName) {
        int stage = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '@' && stage == 0) {
                stage = 1;
            } else if (str.charAt(i) == '@' && stage != 0) {
                return new String[]{displayName + "必须为有效的电子邮箱"};
            } else if (str.charAt(i) == '.' && stage == 1) {
                return new String[0];
            }
        }
        return new String[]{displayName + "必须为有效的电子邮箱"};
    }

    @Override
    public String[] echoRequirements(String displayName) {
        return new String[]{displayName + "必须为有效的电子邮箱"};
    }

}
