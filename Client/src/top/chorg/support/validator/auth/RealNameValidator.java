package top.chorg.support.validator.auth;

import top.chorg.support.validator.foundation.Validator;

public class RealNameValidator extends Validator {
    /**
     * 初始化校验器
     *
     * @param str 用户输入内容
     */
    public RealNameValidator(String str) {
        super(str);
        requireMinLen(1);
    }
}
