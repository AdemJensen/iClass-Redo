package top.chorg.window.foundation;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class INumberArea extends JTextArea {

    private int minimum = Integer.MIN_VALUE, maximum = Integer.MAX_VALUE;
    private String stashArea;
    private Component focusTransferTarget = null;

    public INumberArea() {
        addPolicy();
    }

    public INumberArea(String text) {
        super(text);
        addPolicy();
    }

    public INumberArea(int rows, int columns) {
        super(rows, columns);
        addPolicy();
    }

    public INumberArea(String text, int rows, int columns) {
        super(text, rows, columns);
        addPolicy();
    }

    public INumberArea(Document doc) {
        super(doc);
        addPolicy();
    }

    public INumberArea(Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
        addPolicy();
    }

    public void setLimitation(int minimum, int maximum) {
        this.maximum = maximum;
        this.minimum = minimum;
        if (this.getNumber() > maximum) setNumber(maximum);
        if (this.getNumber() < minimum) setNumber(minimum);
    }

    public void setFocusTransferTarget(Component comp) {
        focusTransferTarget = comp;
    }

    private void addPolicy() {
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                stashArea = getText();
                System.out.println("DONE");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String str = getText();
                if (str.charAt(str.length() - 1) == '\n') {
                    str = str.substring(0, str.length() - 1);
                }
                try {
                    int result = Integer.parseInt(str);
                    if (result > maximum) result = maximum;
                    if (result < minimum) result = minimum;
                    setNumber(result);
                } catch (NumberFormatException nfe) {
                    setText(stashArea);
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.toString());
                if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                    if (focusTransferTarget == null) transferFocus();
                    else focusTransferTarget.requestFocus();
                }
            }
        });
    }

    public int getNumber() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setNumber(int number) {
        setText(String.valueOf(number));
    }

}
