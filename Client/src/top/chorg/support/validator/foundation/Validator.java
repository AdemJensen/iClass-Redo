package top.chorg.support.validator.foundation;

import java.util.ArrayList;

public abstract class Validator {
    String str;
    int maxLen = 0;
    int minLen = 0;
    boolean ascii = false;

    public Validator(String str) {
        this.str = str;
    }

    protected void requireAscii() { ascii = true; }
    protected void requireMaxLen(int len) { maxLen = len; }
    protected void requireMinLen(int len) { minLen = len; }

    public String[] echoRequirements(String displayName) {
        ArrayList<String> result = new ArrayList<>();
        if (minLen > 1) result.add(displayName + "不能少于" + minLen + "字符");
        else if (minLen == 1) result.add(displayName + "不能为空");
        if (maxLen > 0) result.add(displayName + "不能超过" + maxLen + "字符");
        if (ascii) result.add(displayName + "只能包含英文字母和数字");
        return result.toArray(new String[0]);
    }

    public String[] echoRequirements() {
        return echoRequirements("该字段");
    }

    public String[] validate(String displayName) {
        ArrayList<String> result = new ArrayList<>();
        if (minLen > 1 && str.length() < minLen) result.add(displayName + "不能少于" + minLen + "字符");
        else if (minLen == 1 && str.length() == 0) result.add(displayName + "不能为空");
        if (maxLen > 1 && str.length() > maxLen) result.add(displayName + "不能超过" + maxLen + "字符");
        if (ascii && !validateAscii()) result.add(displayName + "只能包含英文字母和数字");
        return result.toArray(new String[0]);
    }

    public String[] validate() {
        return validate("该字段");
    }

    private boolean validateAscii() {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetterOrDigit(c)) return false;
        }
        return true;
    }

}
