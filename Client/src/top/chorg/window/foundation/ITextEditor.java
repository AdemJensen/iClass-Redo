package top.chorg.window.foundation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static top.chorg.kernel.Variable.*;

public class ITextEditor extends IPanel {

    private int width, height;
    private IPanel toolPanel;
    private JScrollPane scrollPane;

    private JComboBox<String> fontPanel;
    private IColorChooserButton color;
    private INumberArea sizePanel;
    private JCheckBox isItalic, isBold, isUnderline;

    public ITextPane getTextPane() {
        return textPane;
    }

    private ITextPane textPane;
    private boolean toolPanelVisibility = false;

    private Style lastStyle;
    private int startCache, endCache;

    private List<ImageIcon> images = new ArrayList<>();

    public void setPreferredSize(Dimension size) {
        super.setPreferredSize(size);
        width = size.width;
        height = size.height;
        if (toolPanel != null) toolPanel.setPreferredSize(new Dimension(width, 30));
        if (toolPanelVisibility) {
            if (textPane != null) textPane.setPreferredSize(new Dimension(width - 20, height - 30));
            if (scrollPane != null) scrollPane.setPreferredSize(new Dimension(width, height - 30));
        } else {
            if (textPane != null) textPane.setPreferredSize(new Dimension(width - 20, height));
            if (scrollPane != null) scrollPane.setPreferredSize(new Dimension(width, height));
        }
    }

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

/*
        textPane.setCompiledText("[\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falasha\\\":0.0}," +
                "\\\"family\\\":\\\"Lucia Grade\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false," +
                "\\\"isUnderline\\\":false,\\\"startOff\\\":0,\\\"len\\\":5,\\\"content\\\":\\\"Yahweh\\\"}\"," +
                "\"icon\",\"0\",\"content\",\"{\\\"color\\\":{\\\"value\\\":-16777216,\\\"falasha\\\":0.0},\\" +
                "\"family\\\":\\\"Lucia Grade\\\",\\\"size\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":fal" +
                "se,\\\"isUnderline\\\":false,\\\"startOff\\\":6,\\\"len\\\":1,\\\"content\\\":\\\"Y\\\"}\",\"ic" +
                "on\",\"0\",\"icon\",\"0\",\"icon\",\"0\",\"icon\",\"0\",\"icon\",\"0\",\"content\",\"{\\\"colo" +
                "r\\\":{\\\"value\\\":-16777216,\\\"falasha\\\":0.0},\\\"family\\\":\\\"Lucia Grade\\\",\\\"si" +
                "ze\\\":13,\\\"isItalic\\\":false,\\\"isBold\\\":false,\\\"isUnderline\\\":false,\\\"startO" +
                "ff\\\":12,\\\"len\\\":23,\\\"content\\\":\\\"Yahweh r     tantalise\\\"}\"]\n");
*/

        this.add(scrollPane);
    }

    private void prepareTextPane() {
        textPane = new ITextPane(width - 20, height);
        textPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
                refreshToolPaneValue();
            }
        });

        textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                refreshToolPaneValue();
            }
        });
    }

    private void prepareToolPanel() {
        toolPanel = new IPanel(width, 30, null, new FlowLayout(FlowLayout.LEFT));

        color = new IColorChooserButton(20, 20, Color.BLACK,
                e -> assignFontInfo("color", color.getSelectedColor())
        );

        JLabel fontLabel = new JLabel("字体", JLabel.RIGHT);
        fontLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontPanel = new JComboBox<>(env.getAvailableFontFamilyNames());
        fontPanel.setPreferredSize(new Dimension(180, 20));
        fontPanel.addItemListener(e -> {
            if (Objects.equals(fontPanel.getSelectedItem(), "多重值")) return;
            assignFontInfo("family", fontPanel.getSelectedItem());
        });

        JLabel sizeLabel = new JLabel("字号", JLabel.RIGHT);
        sizeLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
        sizePanel = new INumberArea(1, 3);
        sizePanel.setPreferredSize(new Dimension(40, (int) sizePanel.getPreferredSize().getHeight()));
        sizePanel.setLimitation(6, 256);
        sizePanel.setFocusTransferTarget(textPane);
        sizePanel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                startCache = textPane.getSelectionStart();
                endCache = textPane.getSelectionEnd();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (sizePanel.getText().equals("多重值")) return;
                if (startCache > -1) {
                    Style temp = styleContext.addStyle(null, defaultStyle);
                    StyleConstants.setFontSize(temp, sizePanel.getNumber());
                    textPane.getStyledDocument().setCharacterAttributes(
                            startCache, endCache - startCache, temp, false
                    );
                    startCache = -1;
                    endCache = -1;
                } else {
                    assignFontInfo("size", sizePanel.getNumber());
                }
            }
        });

        isItalic = new JCheckBox("斜体");
        isItalic.setBorder(new EmptyBorder(0, 8, 0, 0));
        isItalic.addActionListener(e -> assignFontInfo("italic", isItalic.isSelected()));
        isBold = new JCheckBox("粗体");
        isBold.addActionListener(e -> assignFontInfo("bold", isBold.isSelected()));
        isUnderline = new JCheckBox("下划线");
        isUnderline.addActionListener(e -> assignFontInfo("underline", isUnderline.isSelected()));

        toolPanel.addComp(color, fontLabel, fontPanel, sizeLabel, sizePanel, isItalic, isBold, isUnderline);
        assignToolPaneValue(defaultStyle);
    }

    private void assignFontInfo(String item, Object value) {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        Style temp = styleContext.addStyle(null, defaultStyle);
        switch (item) {
            case "color":
                StyleConstants.setForeground(temp, (Color) value);
                break;
            case "family":
                StyleConstants.setFontFamily(temp, (String) value);
                break;
            case "size":
                StyleConstants.setFontSize(temp, (int) value);
                break;
            case "bold":
                StyleConstants.setBold(temp, (boolean) value);
                break;
            case "italic":
                StyleConstants.setItalic(temp, (boolean) value);
                break;
            case "underline":
                StyleConstants.setUnderline(temp, (boolean) value);
                break;
        }
        textPane.getStyledDocument().setCharacterAttributes(
                start, end - start, temp, false
        );
    }

    public void setToolPanelVisibility(boolean visibility) {
        if (visibility) {
            this.removeAll();
            scrollPane.setPreferredSize(new Dimension(width, height - 30));
            refreshToolPaneValue();
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

    private void refreshToolPaneValue() {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        Element element = textPane.getStyledDocument().getCharacterElement(start);
        if (end <= element.getEndOffset()) {
            assignToolPaneValue(element.getAttributes());
        }

        color.setSelectedColor(StyleConstants.getForeground(element.getAttributes()));
        Style result = styleContext.addStyle(null, defaultStyle);
        int currentPos = start;
        boolean isFontFamilyConfused = false;
        boolean isConfused = false;
        AttributeSet starterStyle = element.getAttributes();
        while(currentPos < end) {
            Element curEle = textPane.getStyledDocument().getCharacterElement(currentPos);
            if (!StyleConstants.getFontFamily(curEle.getAttributes()).equals(
                    StyleConstants.getFontFamily(element.getAttributes()))) {
                if (!isFontFamilyConfused) {
                    isFontFamilyConfused = true;
                    isConfused = true;
                    fontPanel.addItem("多重值");
                    fontPanel.setSelectedItem("多重值");
                    StyleConstants.setFontFamily(result, "多重值");
                }
            }
            if (StyleConstants.getFontSize(curEle.getAttributes()) !=
                    StyleConstants.getFontSize(element.getAttributes())) {
                sizePanel.setText("多重值");
                isConfused = true;
            }
            if (StyleConstants.isBold(curEle.getAttributes()) !=
                    StyleConstants.isBold(element.getAttributes())) {
                isBold.setSelected(false);
                isConfused = true;
            }
            if (StyleConstants.isItalic(curEle.getAttributes()) !=
                    StyleConstants.isItalic(element.getAttributes())) {
                isItalic.setSelected(false);
                isConfused = true;
            }
            if (StyleConstants.isUnderline(curEle.getAttributes()) !=
                    StyleConstants.isUnderline(element.getAttributes())) {
                isUnderline.setSelected(false);
                isConfused = true;
            }
            currentPos = curEle.getEndOffset();
        }
        if (!isConfused) assignToolPaneValue(starterStyle);
        if (!isFontFamilyConfused) fontPanel.removeItem("多重值");
        this.toolPanel.revalidate();
        this.toolPanel.repaint();
    }

    private void assignToolPaneValue(AttributeSet set) {
        color.setSelectedColor(StyleConstants.getForeground(set));

        String family = StyleConstants.getFontFamily(set);
        fontPanel.setSelectedItem(family);

        int size = StyleConstants.getFontSize(set);
        sizePanel.setText(String.valueOf(size));

        isBold.setSelected(StyleConstants.isBold(set));
        isItalic.setSelected(StyleConstants.isItalic(set));
        isUnderline.setSelected(StyleConstants.isUnderline(set));

    }

}
