package top.chorg.tests.moduleTests;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;

public class FontTest {

    public static void main(String[] args) {
        HashMap<TextAttribute, Object> attr = new HashMap<>();
        //attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font font = new Font(attr);
        font.getAttributes().get(TextAttribute.UNDERLINE);
        System.out.printf("Family: %s\nSize: %d\nUnderline: %s", font.getFamily(), font.getSize(), font.getAttributes().get(TextAttribute.UNDERLINE));
    }

}
