package top.chorg.window.foundation;

import javax.swing.border.Border;
import java.awt.*;

public class IClickablePanel extends IPanel {
    public IClickablePanel(int width, int height, Border border, LayoutManager layout) {
        super(width, height, border, layout);
        addDefaultListener();
    }

    public IClickablePanel(int width, int height, Border border) {
        super(width, height, border);
        addDefaultListener();
    }

    public IClickablePanel(int width, int height) {
        super(width, height);
        addDefaultListener();
    }

    private void addDefaultListener() {
        this.addMouseListener(new IClickableAdapter(this));
    }

}
