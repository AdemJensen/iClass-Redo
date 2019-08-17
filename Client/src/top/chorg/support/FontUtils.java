package top.chorg.support;

import javax.swing.text.AttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;

import static top.chorg.kernel.Variable.defaultStyle;
import static top.chorg.kernel.Variable.styleContext;

public class FontUtils {

    public static Style transferAttr(AttributeSet set) {
        return transferAttr(
                StyleConstants.getForeground(set),
                StyleConstants.getFontFamily(set),
                StyleConstants.getFontSize(set),
                StyleConstants.isBold(set),
                StyleConstants.isItalic(set),
                StyleConstants.isUnderline(set)
        );
    }

    public static Style transferAttr(
            Color color, String family, int size, boolean isBold, boolean isItalic, boolean isUnderline
    ) {
        Style temp = styleContext.addStyle(null, defaultStyle);
        StyleConstants.setForeground(temp, color);
        StyleConstants.setFontFamily(temp, family);
        StyleConstants.setFontSize(temp, size);
        StyleConstants.setBold(temp, isBold);
        StyleConstants.setItalic(temp, isItalic);
        StyleConstants.setUnderline(temp, isUnderline);
        return temp;
    }

}
