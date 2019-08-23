package top.chorg.window.foundation;

import top.chorg.window.foundation.button.ILinkedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class IPagedListFrame extends IFrame {

    private int page = -1, total;

    private IPanel pagePanel, masterPanel;

    private INumberArea pageNum;
    private JLabel pageInfoPre, pageInfoAft;
    String pageInfoAftFormat = " 页，共 %d 页  ";

    private ILinkedButton prevBtn, nextBtn;
    private ILinkedButton testBtn;

    public IPagedListFrame(int width, int height, String title) {
        super(width, height, title, null, JFrame.DISPOSE_ON_CLOSE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.setLocationCenter(width, height);

        masterPanel = new IPanel(width, height - 60, null, new FlowLayout(FlowLayout.CENTER));

        pagePanel = new IPanel(width, 30, null, new FlowLayout(FlowLayout.CENTER));
        prevBtn = new ILinkedButton("上一页");
        prevBtn.addActionListener(e -> setPageNum(page - 1));

        nextBtn = new ILinkedButton("下一页");
        nextBtn.addActionListener(e -> setPageNum(page + 1));

        pageInfoPre = new JLabel("  第 ");
        pageNum = new INumberArea();
        pageNum.setPreferredSize(new Dimension(30, 15));
        pageNum.setNumber(1);
        pageNum.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                setPageNum(pageNum.getNumber());
            }
        });
        pageInfoAft = new JLabel(pageInfoAftFormat);

        pagePanel.addComp(prevBtn, pageInfoPre, pageNum, pageInfoAft, nextBtn);

        this.addComp(masterPanel, pagePanel);

    }

    public void setTotalPage(int total) {
        this.total = total;
        pageNum.setLimitation(1, total);
        this.pageInfoAft.setText(String.format(pageInfoAftFormat, total));
        if (page > total) this.setPageNum(total);
    }

    public void clearList() {
        this.masterPanel.removeAll();
        this.revalidate();
        this.repaint();
    }

    public void addItem(Component...comp) {
        masterPanel.addComp(comp);
        this.revalidate();
        this.repaint();
    }

    public void setPageNum(int page) {
        this.page = page;
        this.pageNum.setNumber(page);
        nextBtn.setEnabled(page != total);
        prevBtn.setEnabled(page != 1);
    }

    public int getPageNum() {
        return this.page;
    }
    public IPanel getMasterPanel() {
        return this.masterPanel;
    }

}
