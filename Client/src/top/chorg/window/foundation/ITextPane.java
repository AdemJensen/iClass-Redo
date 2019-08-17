package top.chorg.window.foundation;

import top.chorg.support.FileUtils;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static top.chorg.kernel.Variable.temp;

public class ITextPane extends JTextPane {

    private ArrayList<IImageIcon> images = new ArrayList<>();

    public int getPreferredWidth() {
        return width;
    }

    public int getPreferredHeight() {
        return height;
    }

    private int width, height;

    public ITextPane(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));
        this.width = width;
        this.height = height;
        this.setEditorKit(new WarpEditorKit());
    }

    public void refreshImageListCache() {
        images.clear();
        var root = getStyledDocument().getRootElements()[0];
        var n = root.getElementCount();
        for (var i = 0; i < n; i++) {
            var e = root.getElement(i);
            for (var j = 0; j < e.getElementCount(); j++) {
                var icon = (IImageIcon) StyleConstants.getIcon(e.getElement(j).getAttributes());
                if (icon != null) {
                    images.add(icon);
                }
            }
        }
    }

    public String[] getImageHashList() {
        refreshImageListCache();
        String[] result = new String[images.size()];
        for (int i = 0; i < result.length; i++) {
            String tempFileName = "temp@" + i;
            images.get(i).saveImage(temp(tempFileName));
            File temp = new File(temp(tempFileName));
            String hash;
            try {
                hash = FileUtils.md5HashCode32(temp.getPath());
            } catch (FileNotFoundException e) {
                hash = "0";
            }
            String destination = temp("img@" + hash + ".png");
            if (!temp.renameTo(new File(destination))) hash = "0";
            result[i] = hash;
        }
        return result;
    }

    public String getCompiledText() {
        String[] fileHashList = getImageHashList();
        var msg = new StringBuilder();
        var k = 0;
        for (int i = 0; i < getStyledDocument().getLength(); i++) {
            Element ele = getStyledDocument().getCharacterElement(i);
            if (ele.getName().equals("icon")) {
                msg.append("<[img:").append(fileHashList[i]).append("]>");
            } else {

                try {
                    var ch = getStyledDocument().getText(i, 1);
                    // 转义
                    if (ch.equals("<") || ch.equals(">") || ch.equals(File.separator)) {
                        msg.append(File.separator);
                    }
                    msg.append(ch);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return msg.toString();
    }

    public void insertIcon(int position, IImageIcon icon) {
        icon.setMaximumSize(width - 20, 1980);
        int caretPos = this.getCaretPosition();
        this.setCaretPosition(position);
        super.insertIcon(icon);
        if (caretPos > position) caretPos++;
        this.setCaretPosition(caretPos);
    }

    private class WarpEditorKit extends StyledEditorKit {

        private ViewFactory defaultFactory = new WarpColumnFactory();

        @Override
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
    }

    private class WarpColumnFactory implements ViewFactory {

        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                switch (kind) {
                    case AbstractDocument.ContentElementName:
                        return new WarpLabelView(elem);
                    case AbstractDocument.ParagraphElementName:
                        return new ParagraphView(elem);
                    case AbstractDocument.SectionElementName:
                        return new BoxView(elem, View.Y_AXIS);
                    case StyleConstants.ComponentElementName:
                        return new ComponentView(elem);
                    case StyleConstants.IconElementName:
                        return new WarpIconView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }
    }

    private class WarpIconView extends IconView {

        public WarpIconView(Element elem) {
            super(elem);
        }

        @Override
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return getPreferredSpan(View.Y_AXIS);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
    }

    private class WarpLabelView extends LabelView {

        public WarpLabelView(Element elem) {
            super(elem);
        }

        @Override
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
    }

}
