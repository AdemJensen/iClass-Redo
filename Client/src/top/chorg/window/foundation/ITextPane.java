package top.chorg.window.foundation;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

public class ITextPane extends JTextPane {

    private ArrayList<ImageIcon> images = new ArrayList<>();
    public ITextPane() {
        super();
        init(-1, -1, null);
    }

    public ITextPane(int width, int height) {
        super();
        init(width, height, null);
    }

    public ITextPane(StyledDocument doc) {
        super(doc);
        init(-1, -1, null);
    }

    public void init(int width, int height, StyledDocument doc) {
        if (width > 0 && height > 0) this.setPreferredSize(new Dimension(width, height));
        if (doc != null) {
            this.setStyledDocument(doc);

        }

    }

    public String getCompiledText() {
        for (int i = 0; i < this.getStyledDocument().getLength(); i++) {

        }
        return "TEST_DEBUG";
    }

}
