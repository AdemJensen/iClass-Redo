package top.chorg.window.foundation;

import top.chorg.kernel.api.ContentElementInfo;
import top.chorg.support.FileUtils;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static top.chorg.kernel.Variable.*;

public class ITextPane extends JTextPane {

    private ArrayList<IImageIcon> images = new ArrayList<>();

    public int getTargetWidth() {
        if (width == 0) return getPreferredSize().width;
        return width;
    }

    public int getTargetHeight() {
        if (height == 0) return getPreferredSize().height;
        return height;
    }

    private int width, height;

    public void setPreferredSize(Dimension size) {
        this.width = size.width;
        this.height = size.height;
        super.setPreferredSize(size);
    }

    public ITextPane() {
        super();
        this.setEditorKit(new WarpEditorKit());
    }

    public ITextPane(int width, int height) {
        this();
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));
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

    /**
     * 用于计算组件应该有的实际高度。
     * @param width 期望的宽度
     * @param document 当前文档内容
     * @return 高度
     */
    private int calcContentHeight(int width, StyledDocument document) {
        var testPane = new ITextPane();
        testPane.setSize(width, Short.MAX_VALUE);
        testPane.setStyledDocument(document);
        return testPane.getPreferredSize().height;
    }

    public void trimWidth(int maximumWidth) {
        trimWidth(maximumWidth, false);
    }

    /**
     * 根据内容设置最大宽度，需要在内容确定时才能使用
     * @param maximumWidth 需要的最大宽度
     * @param force 是否强制覆盖当前的宽度
     */
    public void trimWidth(int maximumWidth, boolean force) {
        int curWidth = this.getPreferredSize().width;
        if (!force && curWidth < maximumWidth) {
            this.setPreferredSize(new Dimension(
                    curWidth,
                    calcContentHeight(curWidth, this.getStyledDocument())
            ));
            return;
        }

        var testPane = new ITextPane();
        testPane.setStyledDocument(this.getStyledDocument());
        int pw = testPane.getPreferredSize().width;
        if (pw < maximumWidth) {
            int targetHeight = calcContentHeight(pw, this.getStyledDocument());
            this.setPreferredSize(new Dimension(pw, targetHeight));
        } else {
            int targetHeight = calcContentHeight(maximumWidth, this.getStyledDocument());
            this.setPreferredSize(new Dimension(maximumWidth, targetHeight));
        }
    }

    public void limitWidth(int minimumWidth) {
        int pw = this.getPreferredSize().width;
        if (pw < minimumWidth) {
            int targetHeight = calcContentHeight(minimumWidth, this.getStyledDocument());
            this.setPreferredSize(new Dimension(minimumWidth, targetHeight));
        }
    }

    /**
     * 准备要上传的图片，将它们复制到temp目录中，并返回对应图片的Hash值
     * @return 图片的Hash值数组
     */
    public String[] getUploadImageHash() {
        refreshImageListCache();
        String[] result = new String[images.size()];
        for (int i = 0; i < result.length; i++) {
            String tempFileName = "temp@" + i;
            String hash;
            if (!images.get(i).saveOriginalImage(temp(tempFileName))) hash = "0";
            else {
                File temp = new File(temp(tempFileName));
                try {
                    hash = FileUtils.md5HashCode32(temp.getPath());
                } catch (FileNotFoundException e) {
                    hash = "0";
                }
                String destination = temp("img@" + hash + ".png");
                if (!temp.renameTo(new File(destination))) hash = "0";
            }
            result[i] = hash;
        }
        return result;
    }

    public String getCompiledText(String[] fileHashList) {
        ArrayList<String> arr = new ArrayList<>();
        var k = 0;
        for (
                int i = 0;
                i < getStyledDocument().getLength();
                i = getStyledDocument().getCharacterElement(i).getEndOffset()
        ) {
            Element ele = getStyledDocument().getCharacterElement(i);
            switch (ele.getName()) {
                case "icon":
                    arr.add("icon");
                    arr.add(fileHashList[k++]);
                    break;
                case "content":
                    arr.add("content");
                    arr.add(gson.toJson(new ContentElementInfo(ele)));
                    break;
                default:
                    System.out.println("WARNING: Unidentified element type(getCompiledText).");
            }
        }
        int size = arr.size();
        if (size > 0 && arr.get(size - 2).equals("content")) {
            ContentElementInfo temp = gson.fromJson(arr.get(size - 1), ContentElementInfo.class);
            temp.content = temp.content.trim();
            arr.set(size - 1, gson.toJson(temp));
        }
        String[] str = new String[arr.size()];
        return gson.toJson(arr.toArray(str));
    }

    /**
     * 设置解压并显示传输格式的内容
     * 使用该方法以前需要确保该消息中的图片已存在于temp目录中
     * @param text 被压缩的消息信息
     */
    public void setCompiledText(String text) {
        String[] list = gson.fromJson(text, String[].class);
        String type = null;
        int caretPos = 0;
        for (String str : list) {
            if (type == null) {
                type = str;
            } else {
                try {
                    switch (type) {
                        case "content":
                            ContentElementInfo info = gson.fromJson(str, ContentElementInfo.class);
                            this.getStyledDocument().insertString(caretPos, info.content, info.getContentAttribute());
                            caretPos += info.len;
                            break;
                        case "icon":
                            IImageIcon icon = new IImageIcon(temp("img@" + str + ".png"));
                            if (!icon.isValid()) {
                                icon = new IImageIcon(resource("loadFailed.png"));
                                System.out.println("WARNING: Image load fail(ITextPane.setCompiledText).");
                            }
                            this.insertIcon(caretPos, icon);
                            caretPos++;
                            break;
                        default:
                            System.out.println("WARNING: Unidentified element type(ITextPane.setCompiledText).");
                    }
                } catch (BadLocationException e) {
                    System.out.println("WARNING: Wrong caret position(ITextPane.setCompiledText).");
                }
                type = null;
            }
        }
        this.setPreferredSize(new Dimension(width, calcContentHeight(width, getStyledDocument())));
        this.revalidate();
        this.repaint();
    }

    public void insertIcon(int position, IImageIcon...iconList) {
        Insets inset = this.getBorder().getBorderInsets(this);
        int caretPos = this.getCaretPosition();
        for (IImageIcon icon : iconList) {
            icon.setMaximumSize(width - inset.left - inset.right, 1980);
            this.setCaretPosition(position);
            super.insertIcon(icon);
            if (caretPos > position) caretPos++;
            position++;
        }
        this.setCaretPosition(caretPos);
        this.setPreferredSize(new Dimension(width, calcContentHeight(width, getStyledDocument())));
        this.revalidate();
        this.repaint();
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