package top.chorg.window.index.chatPanel;

import top.chorg.window.foundation.IPanel;
import top.chorg.window.foundation.ITextPane;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class ChatBubble extends IPanel {

    final static int BUBBLE_LEFT = 0;
    final static int BUBBLE_RIGHT = 1;

    ITextPane textPane;
    boolean isLeft;

    public ChatBubble(int width, int bubbleDirection, Color background, String compiledDoc) {
        super(1, 1, null, new FlowLayout(FlowLayout.CENTER));
        isLeft = bubbleDirection == 0;
        if (isLeft) {
            this.setBorder(new EmptyBorder(5, 18, 5, 5));
        } else {
            this.setBorder(new EmptyBorder(5, 5, 5, 18));
        }

        textPane = new ITextPane(width - 33, 1);
        textPane.setBackground(background);
        textPane.setCompiledText(compiledDoc);

        this.setBackground(background);

        resetWidth(width);

        this.add(textPane);
    }

    public int getTargetHeight() {
        return textPane.getTargetHeight() + 20;
    }

    public int getTargetWidth() {
        return textPane.getTargetWidth() + 33;
    }

    public void resetWidth(int width) {
        textPane.trimWidth(width - 33, true);
        this.setPreferredSize(new Dimension(getTargetWidth(), getTargetHeight()));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //RendererUtil.applyQualityRenderingHints(g2d);
        g2d.setPaint(getBackground());
        if (isLeft) {
            paintLeftBubble(g2d);
        } else {
            paintRightBubble(g2d);
        }
    }

    private void paintRightBubble(Graphics2D g2d) {
        var width = getWidth();
        var height = getHeight();
        var path = new GeneralPath();
        path.moveTo(width - 10, 18);
        path.lineTo(width, 10);
        path.lineTo(width - 10, 8);
        path.curveTo(width - 10, 8, width - 10, 0, width - 15, 0);
        path.lineTo(5, 0);
        path.curveTo(5, 0, 0, 0, 0, 5);
        path.lineTo(0, height - 5);
        path.curveTo(0, height - 5, 0, height, 5, height);
        path.lineTo(width - 15, height);
        path.curveTo(width - 15, height, width - 10, height, width - 10, height - 5);
        path.lineTo(width - 10, 18);
        path.closePath();
        g2d.fill(path);
    }

    private void paintLeftBubble(Graphics2D g2d) {
        var width = getWidth();
        var height = getHeight();
        var path = new GeneralPath();
        path.moveTo(10, 18);
        path.lineTo(0, 10);
        path.lineTo(10, 8);
        path.curveTo(10, 8, 10, 0, 15, 0);
        path.lineTo(width - 5, 0);
        path.curveTo(width - 5, 0, width, 0, width, 5);
        path.lineTo(width, height - 5);
        path.curveTo(width, height - 5, width, height, width - 5, height);
        path.lineTo(15, height);
        path.curveTo(15, height, 10, height, 10, height - 5);
        path.lineTo(10, 18);
        path.closePath();
        g2d.fill(path);
    }

}
