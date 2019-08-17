package top.chorg.window.foundation;

import top.chorg.kernel.api.ContentElementInfo;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static top.chorg.kernel.Variable.*;

public class ITextEditor extends IPanel {

    private int width, height;
    private IPanel toolPanel;
    private JScrollPane scrollPane;

    private JComboBox<String> fontPanel;
    private IColorChooserButton color;
    private INumberArea sizePanel;
    private JCheckBox isItalic, isBold, isUnderline;
    private ITextPane textPane;
    private boolean toolPanelVisibility = false;

    private Style lastStyle;

    private List<ImageIcon> images = new ArrayList<>();

    public ITextEditor(int width, int height, Border border) {
        super(width, height, border);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);

        this.height = height;
        this.width = width;

        prepareTextPane();
        prepareToolPanel();

        try {
            textPane.getStyledDocument().insertString(0, "Yaqwertyuioplkjhgfdsa", defaultStyle);
            textPane.insertIcon(5, new IImageIcon(resource("loadFailed.png")));
            textPane.insertIcon(7, new IImageIcon(resource("loadFailed.png")));
            textPane.insertIcon(7, new IImageIcon(resource("loadFailed.png")));
            textPane.insertIcon(7, new IImageIcon(resource("loadFailed.png")));
            textPane.insertIcon(7, new IImageIcon(resource("loadFailed.png")));
            textPane.insertIcon(7, new IImageIcon(resource("loadFailed.png")));
            textPane.getStyledDocument().insertString(15, "apkalyse", defaultStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        System.out.println(gson.toJson(new ContentElementInfo(textPane.getStyledDocument().getCharacterElement(0))));
        System.out.println(textPane.getStyledDocument().getCharacterElement(4).getName());
        System.out.println(textPane.getStyledDocument().getCharacterElement(5).getName());

        this.add(scrollPane);
    }

    private void prepareTextPane() {
        textPane = new ITextPane(width - 20, height);
        textPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //textPane.set
        scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        textPane.getStyledDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (lastStyle != null && e.getOffset() + e.getLength() == textPane.getStyledDocument().getLength()) {
                    EventQueue.invokeLater(() -> {
                                if (lastStyle != null) textPane.getStyledDocument().setCharacterAttributes(
                                        e.getOffset(), e.getLength(), lastStyle, false
                                );
                            }
                    );
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (e.getOffset() == textPane.getStyledDocument().getLength()) {
                    lastStyle = null;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) { }
        });

        textPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                setToolPanelValue(getSelectionAttribute());
            }
        });
    }

    private void prepareToolPanel() {
        toolPanel = new IPanel(width, 30, null, new FlowLayout(FlowLayout.LEFT));

        color = new IColorChooserButton(20, 20, Color.BLACK, e -> reassignFontInfo());

        JLabel fontLabel = new JLabel("字体", JLabel.RIGHT);
        fontLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontPanel = new JComboBox<>(env.getAvailableFontFamilyNames());
        fontPanel.setPreferredSize(new Dimension(180, 20));
        fontPanel.addItemListener(e -> reassignFontInfo());

        JLabel sizeLabel = new JLabel("字号", JLabel.RIGHT);
        sizeLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
        sizePanel = new INumberArea(1, 3);
        sizePanel.setPreferredSize(new Dimension(40, (int) sizePanel.getPreferredSize().getHeight()));
        sizePanel.setLimitation(6, 256);
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
        isItalic.addActionListener(e -> reassignFontInfo());
        isBold = new JCheckBox("粗体");
        isBold.addActionListener(e -> reassignFontInfo());
        isUnderline = new JCheckBox("下划线");
        isUnderline.addActionListener(e -> reassignFontInfo());

        toolPanel.addComp(color, fontLabel, fontPanel, sizeLabel, sizePanel, isItalic, isBold, isUnderline);
        setToolPanelValue(defaultStyle);
    }

    private void reassignFontInfo() {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        textPane.getStyledDocument().setCharacterAttributes(
                start, end - start, readFontInfo(), false
        );
    }

    public void setToolPanelVisibility(boolean visibility) {
        if (visibility) {
            this.removeAll();
            scrollPane.setPreferredSize(new Dimension(width, height - 30));
            AttributeSet selectionAttribute = getSelectionAttribute();
            setToolPanelValue(selectionAttribute == null ? defaultStyle : selectionAttribute);
            this.addComp(toolPanel, scrollPane);
        } else {
            this.removeAll();
            scrollPane.setPreferredSize(new Dimension(width, height));
            this.addComp(scrollPane);
        }
        toolPanelVisibility = visibility;
    }

    public boolean getToolPanelVisibility() {
        return toolPanelVisibility;
    }

    private AttributeSet getSelectionAttribute() {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        Element element = textPane.getStyledDocument().getCharacterElement(start);
        return element.getAttributes();
    }

    private Style readFontInfo() {
        return transferAttr(
                color.getSelectedColor(),
                (String) fontPanel.getSelectedItem(), Integer.parseInt(sizePanel.getText()),
                isBold.isSelected(), isItalic.isSelected(), isUnderline.isSelected()
        );
    }

    private Style transferAttr(AttributeSet set) {
        return transferAttr(
                StyleConstants.getForeground(set),
                StyleConstants.getFontFamily(set),
                StyleConstants.getFontSize(set),
                StyleConstants.isBold(set),
                StyleConstants.isItalic(set),
                StyleConstants.isUnderline(set)
        );
    }

    private Style transferAttr(
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

    private void setToolPanelValue(AttributeSet set) {
        color.setSelectedColor(StyleConstants.getForeground(set));

        String family = StyleConstants.getFontFamily(set);
        if (family.equals("...")) fontPanel.addItem("...");
        fontPanel.setSelectedItem(family);

        int size = StyleConstants.getFontSize(set);
        if (size < 0) sizePanel.setText("");
        else sizePanel.setText(String.valueOf(size));

        isBold.setSelected(StyleConstants.isBold(set));
        isItalic.setSelected(StyleConstants.isItalic(set));
        isUnderline.setSelected(StyleConstants.isUnderline(set));
    }

}
