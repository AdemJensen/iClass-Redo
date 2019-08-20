package top.chorg.window.miniDrawPad;

import top.chorg.window.foundation.IImageIcon;
import top.chorg.window.foundation.notice.IConfirmNoticeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static top.chorg.kernel.Variable.resource;

// 主界面类
public class MiniDrawPad extends JFrame implements ActionListener {

    private static final long serialVersionUID = -2551980583852173918L;
    private JToolBar buttonPanel;//定义按钮面板
    private JMenuBar bar;//定义菜单条
    private JMenu file, color, stroke, help;//定义菜单
    private JMenuItem insertItem, newFile, openFile, saveFile, exportFile, exit; //file 菜单中的菜单项
    private JMenuItem helpSubItem, helpItem, colorChoiceItem, strokeItem;//help 菜单中的菜单项
    private JLabel startBar;//状态栏
    private JButton insertToolButton; // 插入编辑器按钮

    private DrawArea drawarea;//画布类的定义
    private Help helpObject; //定义一个帮助类对象
    private FileClass fileclass;//文件对象
    String[] fontName;
    //定义工具栏图标的名称
    private String[] names = {"newFile", "openFile", "saveFile", "export", "pen", "line"
            , "rect", "fRect", "oval", "fOval", "circle", "fCircle"
            , "roundRect", "froundRect", "rubber", "color"
            , "stroke", "word"};//定义工具栏图标的名称
    private IImageIcon[] icons;//定义图象数组

    private String[] tipText = {//这里是鼠标移到相应的按钮上给出相应的提示
            "新建画布", "打开画布", "保存画布", "导出画布为图像", "铅笔", "直线"
            , "空心矩形", "填充矩形", "空心椭圆", "填充椭圆"
            , "空心圆", "填充圆", "圆角矩形", "填充圆角矩形"
            , "橡皮擦", "颜色", "笔触大小", "插入文字"};
    JButton[] button;//定义工具条中的按钮组
    private JCheckBox bold, italic;//工具条字体的风格（复选框）
    private JComboBox styles;//工具条中的字体的样式（下拉列表）

    public void addInsertActionListener(ActionListener listener) {
        ActionListener action = e -> new IConfirmNoticeFrame(
                drawarea.index > 0 ? "结束绘图并将图片插入到编辑器中" : "将空画布插入编辑器中",
                f -> {
                    listener.actionPerformed(e);
                    this.dispose();
                }
        ).showWindow();
        insertItem.addActionListener(action);
        insertToolButton.addActionListener(action);
    }

    public IImageIcon generateImageIcon() {
        return fileclass.generateImageIcon();
    }

    public MiniDrawPad(String title) {
        this(title, false);
    }

    public MiniDrawPad(String title, boolean insertAction) {
        // 主界面的构造方法
        super(title);
        // 菜单的初始化
        file = new JMenu("文件");
        color = new JMenu("颜色");
        stroke = new JMenu("画笔");
        help = new JMenu("帮助");
        bar = new JMenuBar();   //菜单条的初始化

        // 菜单条添加菜单
        bar.add(file);
        bar.add(color);
        bar.add(stroke);
        bar.add(help);

        //界面中添加菜单条
        setJMenuBar(bar);

        //菜单中添加快捷键
        file.setMnemonic('F');  //既是ALT+“F”
        color.setMnemonic('C'); //既是ALT+“C”
        stroke.setMnemonic('S');//既是ALT+“S”
        help.setMnemonic('H');  //既是ALT+“H”

        //File 菜单项的初始化
        if (insertAction) {
            insertItem = new JMenuItem("将内容插入编辑器");
            insertItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK
            ));
            file.add(insertItem);
        }
        newFile = new JMenuItem("新建画板");
        openFile = new JMenuItem("打开图片");
        saveFile = new JMenuItem("保存画板");
        exportFile = new JMenuItem("导出画布为图像");

        exit = new JMenuItem("退出");

        //File 菜单中添加菜单项
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(exportFile);
        file.add(exit);

        //File 菜单项添加快捷键
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        exportFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.CTRL_DOWN_MASK));

        //File 菜单项的注册监听
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);

        //Color 菜单项的初始化
        colorChoiceItem = new JMenuItem("调色板");
        colorChoiceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        colorChoiceItem.addActionListener(this);
        color.add(colorChoiceItem);
        //Help 菜单项的初始化
        helpItem = new JMenuItem("帮助主题");
        helpSubItem = new JMenuItem("关于MiniDrawPad");

        //Help 菜单中添加菜单项
        help.add(helpItem);
        help.addSeparator();//添加分割线
        help.add(helpSubItem);

        //Help 菜单项的注册监听
        helpSubItem.addActionListener(this);
        helpItem.addActionListener(this);

        //Stroke 菜单项的初始化
        strokeItem = new JMenuItem("设置画笔");
        strokeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        stroke.add(strokeItem);
        strokeItem.addActionListener(this);

        //工具栏的初始化
        buttonPanel = new JToolBar(JToolBar.HORIZONTAL);
        icons = new IImageIcon[names.length];
        button = new JButton[names.length];
        if (insertAction) {     // 与编辑器联动
            IImageIcon insertToolIcon = new IImageIcon(resource("miniDrawPad", "insert.png"));
            insertToolIcon.setSize(35, 35);
            insertToolButton = new JButton(insertToolIcon);
            insertToolButton.setPreferredSize(new Dimension(35, 35));
            insertToolButton.setToolTipText("将画板内容插入编辑器");
            buttonPanel.add(insertToolButton);
        }
        for (int i = 0; i < names.length; i++) {
            icons[i] = new IImageIcon(resource("miniDrawPad", names[i] + ".png"));
            icons[i].setSize(35, 35);
            // 获得图片（以类路径为基准）
            button[i] = new JButton("", icons[i]);//创建工具条中的按钮
            button[i].setPreferredSize(new Dimension(35, 35));
            button[i].setToolTipText(tipText[i]);//这里是鼠标移到相应的按钮上给出相应的提示
            buttonPanel.add(button[i]);
            button[i].setBackground(Color.red);
            if (i < 3) button[i].addActionListener(this);
            else if (i <= 17) button[i].addActionListener(this);
        }
        CheckBoxHandler CHandler = new CheckBoxHandler();    // 字体样式处理类
        bold = new JCheckBox("粗体");
        bold.setFont(new Font(Font.DIALOG, Font.BOLD, 18));    // 设置字体
        bold.addItemListener(CHandler);        // bold注册监听
        italic = new JCheckBox("斜体");
        italic.addItemListener(CHandler);    // italic注册监听
        italic.setFont(new Font(Font.DIALOG, Font.ITALIC, 18));    // 设置字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();    // 计算机上字体可用的名称
        fontName = ge.getAvailableFontFamilyNames();
        styles = new JComboBox<>(fontName);    // 下拉列表的初始化
        styles.addItemListener(CHandler);    // styles注册监听
        styles.setMaximumSize(new Dimension(200, 50));//设置下拉列表的最大尺寸
        styles.setMinimumSize(new Dimension(150, 40));
        styles.setFont(new Font(Font.DIALOG, Font.BOLD, 18));//设置字体

        //工具栏中添加字体式样
        buttonPanel.add(bold);
        buttonPanel.add(italic);
        buttonPanel.add(styles);

        //状态栏的初始化
        startBar = new JLabel("我的MiniDrawPad");


        //绘画区的初始化
        drawarea = new DrawArea(this);
        helpObject = new Help(this);
        fileclass = new FileClass(this, drawarea);


        Container con = getContentPane();//得到内容面板
        con.add(buttonPanel, BorderLayout.NORTH);
        con.add(drawarea, BorderLayout.CENTER);
        con.add(startBar, BorderLayout.SOUTH);
        Toolkit tool = getToolkit();//得到一个Toolkit类的对象（主要用于得到屏幕的大小）
        Dimension dim = tool.getScreenSize();//得到屏幕的大小 （返回Dimension对象）
        setBounds(40, 40, dim.width - 70, dim.height - 100);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //设置状态栏显示的字符
    public void setStartBar(String s) {
        startBar.setText(s);
    }

    public void actionPerformed(ActionEvent e) {
        // 事件的处理
        for (int i = 4; i <= 14; i++) {
            if (e.getSource() == button[i]) {
                drawarea.setCurrentChoice(i - 1);   // 因为加入了新的按钮，所以设置-1
                drawarea.createNewItem();
                drawarea.repaint();
            }
        }
        if (e.getSource() == newFile || e.getSource() == button[0]) { //新建
            if (drawarea.index > 0) {
                new IConfirmNoticeFrame(
                        "放弃现有更改",
                        f -> {
                            fileclass.newFile();
                        }
                ).showWindow();
            } else {
                fileclass.newFile();
            }
        } else if (e.getSource() == openFile || e.getSource() == button[1]) { //打开
            fileclass.openFile();
        } else if (e.getSource() == saveFile || e.getSource() == button[2]) { //保存
            fileclass.saveFile();
        } else if (e.getSource() == exportFile || e.getSource() == button[3]) {
            fileclass.exportFile();
        } else if (e.getSource() == exit) { //退出程序
            this.dispose();
        } else if (e.getSource() == colorChoiceItem || e.getSource() == button[15]) { //弹出颜色对话框
            drawarea.chooseColor();//颜色的选择
        } else if (e.getSource() == button[16] || e.getSource() == strokeItem) { //画笔粗细
            drawarea.setStroke();//画笔粗细的调整
        } else if (e.getSource() == button[17]) { //添加文字
            drawarea.setCurrentChoice(14);
            drawarea.createNewItem();
            drawarea.repaint();
        } else if (e.getSource() == helpSubItem) { //帮助信息
            helpObject.AboutBook();
        } else if (e.getSource() == helpItem) { //帮助主题
            helpObject.MainHelp();
        }


    }

    //字体样式处理类（粗体、斜体、字体名称）
    class CheckBoxHandler implements ItemListener {


        public void itemStateChanged(ItemEvent ie) {
            // 字体样式处理类（粗体、斜体、字体名称）
            if (ie.getSource() == bold)//字体粗体
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                    drawarea.setFont(1, Font.BOLD);
                else
                    drawarea.setFont(1, Font.PLAIN);
            } else if (ie.getSource() == italic)//字体斜体
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                    drawarea.setFont(2, Font.ITALIC);
                else drawarea.setFont(2, Font.PLAIN);

            } else if (ie.getSource() == styles)//字体的名称
            {
                drawarea.style = fontName[styles.getSelectedIndex()];
            }
        }

    }
}


