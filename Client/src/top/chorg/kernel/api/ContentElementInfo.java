package top.chorg.kernel.api;

import top.chorg.support.FontUtils;

import javax.swing.text.*;
import java.awt.*;

public class ContentElementInfo {

    public Color color;
    public String family;
    public int size;
    public boolean isItalic, isBold, isUnderline;

    public int startOff, len;

    public String content;

    public ContentElementInfo(Element ele) {
        AttributeSet set = ele.getAttributes();
        color = StyleConstants.getForeground(set);
        family = StyleConstants.getFontFamily(set);
        size = StyleConstants.getFontSize(set);
        isBold = StyleConstants.isBold(set);
        isItalic = StyleConstants.isItalic(set);
        isUnderline = StyleConstants.isUnderline(set);
        this.startOff = ele.getStartOffset();
        this.len = ele.getEndOffset() - ele.getStartOffset();
        try {
            this.content = ele.getDocument().getText(0, len);
        } catch (BadLocationException e) {
            this.content = "";
        }
    }

    public Style getContentAttribute() {
        return FontUtils.transferAttr(color, family, size, isBold, isItalic, isUnderline);
    }

}
