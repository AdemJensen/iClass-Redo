package top.chorg.window.foundation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IFrame extends JFrame {

    public int DEBUG_X, DEBUG_Y;

    public IFrame(int width, int height, String title, LayoutManager layout, int constant) {
        this.setSize(new Dimension(width, height));
        this.setResizable(false);
        if (title == null) this.setTitle("NULL");
        else this.setTitle(title);
        this.setLayout(layout);
        this.setDefaultCloseOperation(constant);
        this.addComponents();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.printf("Mouse down: x = %d, y = %d\n", e.getX(), e.getY());
                DEBUG_X = e.getX();
                DEBUG_Y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.printf("Mouse up: x = %d, y = %d\n", e.getX(), e.getY());
                System.out.printf("Selected Area: width = %d, height = %d\n", e.getX() - DEBUG_X, e.getY() - DEBUG_Y);
            }
        });
        this.addListeners();
    }

    public IFrame(int width, int height, String title, LayoutManager layout) {
        this(width, height, title, layout, JFrame.DISPOSE_ON_CLOSE);
    }

    public IFrame(int width, int height, String title, int constant) {
        this(width, height, title, null, constant);
    }

    public IFrame(int width, int height, String title) {
        this(width, height, title, null, JFrame.DISPOSE_ON_CLOSE);
    }

    public void addComp(Component...components) {
        for (Component component : components) {
            this.add(component);
        }
    }

    public void addComponents() { }
    public void addListeners() { }

    public void showWindow() {
        //this.pack();
        this.setVisible(true);
    }

    public void hideWindow() {
        this.setVisible(false);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paint(Graphics g) {
        System.out.printf("%s: x = %d, y = %d\n", this.hashCode(), getX(), getY());
        System.out.printf("%s: width = %d, height = %d\n", this.hashCode(), getWidth(), getHeight());
        super.paint(g);
    }

    /**
     * 根据给定的窗口尺寸将窗口移动到屏幕中心位置
     * @param width 本窗口宽度
     * @param height 本窗口高度
     */
    public void setLocationCenter(int width, int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sw = (int) screenSize.getWidth();
        int sh = (int) screenSize.getHeight();
        this.setLocation(sw / 2 - width / 2, sh / 2 - height / 2);
    }
}
