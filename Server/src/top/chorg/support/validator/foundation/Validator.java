package top.chorg.support.validator.foundation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 本类是数据检查器的基类。
 * 数据检查器可以用于检查用户的输入是否符合要求，快速生成字段所需的要求说明，并可以在用户输入无效时详细指出问题所在。
 * 继承该类并将要求写在构造方法中，即require...方法。
 *
 * 另外，本类可以编写自定义校验器来丰富校验方法。
 * 要启用自定义校验器，请在子类构造方法中插入代码"requireCustomValidation()"，并至少覆盖validateCustomRequirement方法。
 *
 * 要了解输出说明和输出校验结果的原理，请详细查看下方的JavaDoc。
 */
public abstract class Validator {
    protected String str;       // 要校验的字符串，可在子类中直接访问
    private int maxLen = 0;     // 是否开启最大字符限制，小于1即为不开启
    private int minLen = -1;    // 是否开启最小字符限制，小于1即为不开启
    private boolean ascii = false;    // 是否开启仅允许字母+数字限制
    private boolean digit = false;    // 是否开启仅允许数字限制
    private boolean custom = false;   // 是否开启自定义校验器

    /**
     * 初始化校验器
     *
     * @param str 用户输入内容
     */
    public Validator(String str) {
        this.str = str;
    }

    /**
     *  启用仅允许字母+数字限制
     */
    protected final void requireAscii() { ascii = true; }

    /**
     *  启用仅允许数字限制
     */
    protected final void requireDigit() { digit = true; }

    /**
     * 启用最大字符限制，小于1即为不开启
     *
     * @param len 限制字符长度
     */
    protected final void requireMaxLen(int len) { maxLen = len; }

    /**
     * 启用最小字符限制，小于1即为不开启
     *
     * @param len 限制字符长度
     */
    protected final void requireMinLen(int len) { minLen = len; }

    /**
     *  开启自定义校验器
     */
    protected final void requireCustomValidation() { custom = true; }

    /**
     * 为自定义校验器添加字段要求说明。如果您不需要添加说明，可以不覆盖此方法。
     *
     * @param displayName 显示自定义校验时的字段名称
     * @return 全部字段要求
     */
    protected String[] echoCustomRequirement(String displayName) { return new String[0]; }

    /**
     * 自定义校验器方法。如果您在子类的构造方法中插入代码"requireCustomValidation()"，那么您必须覆盖此方法。
     *
     * @param displayName 显示用户输入错误原因时的字段名称
     * @return 用户输入错误的原因
     */
    protected String[] validateCustomRequirement(String displayName) { return new String[0]; }

    /**
     * 输出该字段的全部要求。
     * 在子类构造方法中使用require...方法时，系统将会自动记录并生成对应的要求字符串。
     * 如果您启用了自定义校验器，那么系统会通过echoCustomRequirement方法来获取到您的自定义要求。
     * 自定义校验器产生的要求条目会随着普通校验结果一同返回。
     * 当然，您也可以重写本方法，来隐藏部分要求（可以参考本工程中的PhoneValidator类）
     *
     * @param displayName 显示校验规则时的字段名称
     * @return 全部字段要求，数组中的每个元素都是一条要求。
     */
    public String[] echoRequirements(String displayName) {
        ArrayList<String> result = new ArrayList<>();
        if (minLen == maxLen && minLen > 0) {
            result.add(displayName + "必须为" + minLen + "字符");
        } else {
            if (minLen > 1) result.add(displayName + "不能少于" + minLen + "字符");
            else if (minLen == 1) result.add(displayName + "不能为空");
            if (maxLen > 0) result.add(displayName + "不能超过" + maxLen + "字符");
        }
        if (ascii) result.add(displayName + "只能包含英文字母和数字");
        if (digit) result.add(displayName + "只能包含数字");
        if (custom && this.echoCustomRequirement(displayName).length > 0) {
            result.addAll(Arrays.asList(this.echoCustomRequirement(displayName)));
        }
        return result.toArray(new String[0]);
    }

    /**
     * 与echoRequirements(String displayName)作用相同，区别是添加了默认字段提示。
     *
     * @return 全部字段要求，数组中的每个元素都是一条要求。
     */
    public String[] echoRequirements() {
        return echoRequirements("该字段");
    }

    /**
     * 检查用户输入并给出用户输入无效的全部原因。
     * 如果您启用了自定义校验器，那么系统会通过validateCustomRequirement方法来检查str字符串。
     * 自定义校验器产生的结果会随着普通校验结果一同返回。
     * 当然，您也可以重写本方法，来隐藏部分原因（可以参考本工程中的PhoneValidator类）
     *
     * @param displayName 显示校验规则时的字段名称
     * @return 用户输入错误的原因，数组中的每个元素都是一条错误原因。
     */
    public String[] validate(String displayName) {
        ArrayList<String> result = new ArrayList<>();
        if (minLen == maxLen && minLen > 0 && str.length() != minLen) {
            result.add(displayName + "必须为" + minLen + "字符");
        } else {
            if (minLen > 1 && str.length() < minLen) result.add(displayName + "不能少于" + minLen + "字符");
            else if (minLen == 1 && str.length() == 0) result.add(displayName + "不能为空");
            if (maxLen > 1 && str.length() > maxLen) result.add(displayName + "不能超过" + maxLen + "字符");
        }
        if (ascii && !validateAscii()) result.add(displayName + "只能包含英文字母和数字");
        if (digit && !validateDigit()) result.add(displayName + "只能包含数字");
        if (custom && this.validateCustomRequirement(displayName).length > 0) {
            result.addAll(Arrays.asList(this.echoCustomRequirement(displayName)));
        }
        return result.toArray(new String[0]);
    }

    /**
     * 与validate(String displayName)作用相同，区别是添加了默认字段提示。
     *
     * @return 代表全部字段要求，数组中的每个元素都是一条要求。
     */
    public String[] validate() {
        return validate("该字段");
    }

    /**
     * 检查字符串是否全部由字母和数字组成。
     *
     * @return 检查结果
     */
    private boolean validateAscii() {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetterOrDigit(c)) return false;
        }
        return true;
    }

    /**
     * 检查字符串是否全部由数字组成。
     *
     * @return 检查结果
     */
    private boolean validateDigit() {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
