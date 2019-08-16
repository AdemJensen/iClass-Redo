package top.chorg.window.foundation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;

public class ITextEditor extends IPanel {

    final Font defaultFont = new Font("宋体", Font.PLAIN, 15);

    private Font font = defaultFont;

    private int width, height;
    private IPanel toolPanel;
    private JScrollPane scrollPane;

    private JComboBox<String> fontPanel;
    private IColorChooserButton color;
    private INumberArea sizePanel;
    private JCheckBox isItalic, isBold, isUnderline;
    private ITextPane textPane;
    private boolean toolPanelVisibility = false;

    public ITextEditor(int width, int height, Border border) {
        super(width, height, border);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);

        this.height = height;
        this.width = width;

        textPane = new ITextPane(width - 20, height);
        textPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        textPane.setContentType("text/html");
        textPane.setText("<b>hahaha</b>ahaha");
        System.out.println(textPane.getText());
        scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        prepareToolPanel();

        this.add(scrollPane);
    }

    private void prepareToolPanel() {
        toolPanel = new IPanel(width, 30, null, new FlowLayout(FlowLayout.LEFT));

        color = new IColorChooserButton(20, 20, Color.BLACK);

        JLabel fontLabel = new JLabel("字体", JLabel.RIGHT);
        fontLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontPanel = new JComboBox<>(env.getAvailableFontFamilyNames());
        fontPanel.setPreferredSize(new Dimension(180, 20));
        fontPanel.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                reassignFontInfo();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        JLabel sizeLabel = new JLabel("字号", JLabel.RIGHT);
        sizeLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
        sizePanel = new INumberArea(1, 3);
        sizePanel.setPreferredSize(new Dimension(40, (int) sizePanel.getPreferredSize().getHeight()));
        sizePanel.setLimitation(1, 256);
        sizePanel.setFocusTransferTarget(textPane);
        sizePanel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                reassignFontInfo();
            }
        });

        isItalic = new JCheckBox("斜体");
        isItalic.setBorder(new EmptyBorder(0, 8, 0, 0));
        //isItalic.addActionListener(e -> font = readFontInfo());
        isBold = new JCheckBox("粗体");
        //isBold.addActionListener(e -> font = readFontInfo());
        isUnderline = new JCheckBox("下划线");
        //isUnderline.addActionListener(e -> font = readFontInfo());

        toolPanel.addComp(color, fontLabel, fontPanel, sizeLabel, sizePanel, isItalic, isBold, isUnderline);
        setToolPanelValue(defaultFont);
    }

    public void reassignFontInfo() {
        font = readFontInfo();
        String str = textPane.getSelectedText();
        int offs = textPane.getSelectionStart();
        int sta = textPane.getSelectionStart(), end = textPane.getSelectionEnd();
//            textPane.getDocument().insertString(end, "</font>", null);
//            textPane.getDocument().insertString(sta, "<font size=\"50\" face=\"Verdana\">", null);
        System.out.println(textPane.getSelectedText());
        //textPane.setFont(font);
    }

    public void setToolPanelVisibility(boolean visibility) {
        if (visibility) {
            this.removeAll();
            scrollPane.setPreferredSize(new Dimension(width, height - 30));
            setToolPanelValue(font);
            this.addComp(toolPanel, scrollPane);
        } else {
            this.removeAll();
            scrollPane.setPreferredSize(new Dimension(width, height));
            setToolPanelValue(font);
            this.addComp(scrollPane);
        }
        toolPanelVisibility = visibility;
    }

    public boolean getToolPanelVisibility() {
        return toolPanelVisibility;
    }

    public Font readFontInfo() {
        return transferAttr(
                (String) fontPanel.getSelectedItem(), Integer.parseInt(sizePanel.getText()),
                isBold.isSelected(), isItalic.isSelected(), isUnderline.isSelected()
        );
    }

    public Font transferAttr(String family, int size, boolean isBold, boolean isItalic, boolean isUnderline) {
        HashMap<TextAttribute, Object> attr = new HashMap<>();
        attr.put(TextAttribute.FAMILY, family);
        attr.put(TextAttribute.SIZE, size);
        attr.put(TextAttribute.WEIGHT, isBold ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
        attr.put(TextAttribute.POSTURE, isItalic ? TextAttribute.POSTURE_OBLIQUE : TextAttribute.POSTURE_REGULAR);
        if (isUnderline) attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        return new Font(attr);
    }

    public void setToolPanelValue(Font font) {
        fontPanel.setSelectedItem(font.getFamily());
        sizePanel.setText(String.valueOf(font.getSize()));
        isItalic.setSelected(font.isItalic());
        isBold.setSelected(font.isBold());
        isUnderline.setSelected(font.getAttributes().get(TextAttribute.UNDERLINE) != null);
    }

}
