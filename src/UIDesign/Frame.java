package UIDesign;

import Back_end.Curve;
import Back_end.User;
import com.toedter.calendar.JCalendar;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Vector;


import static UIDesign.Util.*;

public class Frame extends JFrame {
    public static HomePage homePage;
    public static SettingPage settingPage;
    public static ChartPage chartPage;
    public static Reciteword reciteword;

    public static StarPage starPage;
    public static ArrayList<String> recordname = new ArrayList<>();
    public static ArrayList<Double> recordrate = new ArrayList<>();
    //词书名称 cnt 来控制哪些天背什么
    public static ArrayList<String[]> routine = new ArrayList<>();

//    public static ArrayList<String[]> deterFile = new ArrayList<>();

    public void launchFrame() throws IOException {
        this.setLayout(null);
        this.setBounds(X0,Y0,FRAME_WIDTH,FRAME_HEIGHT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        /**/
        homePage = new HomePage();
        homePage.launchPanel();
        this.add(homePage);

        settingPage = new SettingPage();
        settingPage.launchPanel();
        settingPage.setVisible(false);
        this.add(settingPage);
        /**/
        starPage = new StarPage();
        starPage.launchPanel();
        starPage.setVisible(false);
        this.add(starPage);
        /**/
        chartPage = new ChartPage();
        chartPage.launchPanel();
        chartPage.setVisible(false);
        this.add(chartPage);

        /**/
        reciteword = new Reciteword();
        try {
            reciteword.launchPanel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reciteword.setVisible(false);
        this.add(reciteword);
        /**/

        this.setTitle("单词记忆软件");
        this.setVisible(true);
    }
}
class HomePage extends JPanel {
    private Font youyuan = new Font("幼圆",Font.BOLD,16);
    JButton learn, setting, graph;
    JPanel pagePanel, bookPanel;
    JTextField learnText, settingText, graphText;
    int bookMarginWidth = 300, bookMarginHeight = 100;
    int bookSpaceHorizontal = 450, bookSpaceVertical = 400;
    int bookButtonWidth = 200, bookButtonHeight = 300;
    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
//        this.setBackground(Color.white);
        pagePanel = new ImagePanel(new ImageIcon("picture\\panel1.jpg").getImage());
        pagePanel.setBounds(0, 0, 170, FRAME_HEIGHT);
        pagePanel.setLayout(null);
        learn = new JButton();
        learn.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\单词本1.png").getImage(), true));
        learn.setBounds(35, 40, 100, 100);
        learn.setBorderPainted(true);
        learn.setContentAreaFilled(false);
        learn.addActionListener(e -> {
            Frame.homePage.setVisible(false);
            Frame.reciteword.setVisible(true);
        });
        learnText = new JTextField("背单词");
        learnText.setBounds(60, 140, 55, 20);
        learnText.setOpaque(false);
        learnText.setEditable(false);
        learnText.setBorder(null);
        learnText.setFont(youyuan);

//        setting = new JButton("我的");
        setting = new JButton("");
        setting.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\我的.png").getImage(), true));
        setting.setBounds(35, 220, 100, 100);
        setting.setBorderPainted(true);
        setting.setContentAreaFilled(false);
        setting.addActionListener(e -> {
            Frame.homePage.setVisible(false);
            Frame.settingPage.setVisible(true);
        });
        settingText = new JTextField("我的");
        settingText.setBounds(65, 320, 55, 20);
        settingText.setOpaque(false);
        settingText.setEditable(false);
        settingText.setBorder(null);
        settingText.setFont(youyuan);

//        graph = new JButton("记忆曲线");
        graph = new JButton();
        graph.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\动态曲线.png").getImage(), true));
        graph.setBounds(35, 400, 100, 100);
        graph.setBorderPainted(true);
        graph.setContentAreaFilled(false);
        graph.addActionListener(e -> {
            Frame.homePage.setVisible(false);
            Frame.chartPage.setVisible(true);
        });
        graphText = new JTextField("记忆曲线");
        graphText.setBounds(55, 500, 80, 20);
        graphText.setOpaque(false);
        graphText.setEditable(false);
        graphText.setBorder(null);
        graphText.setFont(youyuan);

        pagePanel.add(learn);
        pagePanel.add(setting);
        pagePanel.add(graph);
        pagePanel.add(learnText);
        pagePanel.add(settingText);
        pagePanel.add(graphText);
        this.add(pagePanel);


        bookPanel = new JPanel();
        bookPanel.setBounds(190, 0, 610, FRAME_HEIGHT);
//        bookPanel.setBackground(Color.CYAN);
        //bookPanel.setLayout(new FlowLayout()); // 使用FlowLayout作为示例，可以根据需要更改布局管理器
        bookPanel.setLayout(null);
        JButton book1 = new JButton();
        book1.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\词书图.png").getImage(), true));
        book1.setBounds(30, 50, 200, 150);
        book1.setBorderPainted(false);
        book1.setContentAreaFilled(false);
        JTextField book1Text = new JTextField("词书一");
        book1Text.setBounds(110, 170, 80, 50);
        book1Text.setOpaque(false);
        book1Text.setEditable(false);
        book1Text.setBorder(null);
        book1Text.setFont(youyuan);
        JButton book2 = new JButton();
        book2.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\词书图.png").getImage(), true));
        book2.setBounds(340, 50, 200, 150);
        book2.setBorderPainted(false);
        book2.setContentAreaFilled(false);
        JTextField book2Text = new JTextField("词书二");
        book2Text.setBounds(420, 170, 80, 50);
        book2Text.setOpaque(false);
        book2Text.setEditable(false);
        book2Text.setBorder(null);
        book2Text.setFont(youyuan);
        JButton book3 = new JButton();
        book3.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\词书图.png").getImage(), true));
        book3.setBounds(30, 320, 200, 150);
        book3.setBorderPainted(false);
        book3.setContentAreaFilled(false);
        JTextField book3Text = new JTextField("词书三");
        book3Text.setBounds(110, 440, 80, 50);
        book3Text.setOpaque(false);
        book3Text.setEditable(false);
        book3Text.setBorder(null);
        book3Text.setFont(youyuan);
        JButton setBook = new JButton();
        setBook.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\词书图.png").getImage(), true));
        setBook.setBounds(340, 320, 200, 150);
        setBook.setBorderPainted(false);
        setBook.setContentAreaFilled(false);
        JTextField setBookText = new JTextField("添加词书");
        setBookText.setBounds(420, 440, 80, 50);
        setBookText.setOpaque(false);
        setBookText.setEditable(false);
        setBookText.setBorder(null);
        setBookText.setFont(youyuan);

        setBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                fileChooser.setDialogTitle("Import File");
                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectBook = fileChooser.getSelectedFile();
                    // 这里可以添加代码处理文件，例如读取文件内容等
                    System.out.println("File to import: " + selectBook.getAbsolutePath());
                    File newBook = new File("wordbook\\" + selectBook.getName());
                    try {
                        newBook.createNewFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        bookPanel.add(book1);
        bookPanel.add(book2);
        bookPanel.add(book3);
        bookPanel.add(setBook);
        bookPanel.add(book1Text);
        bookPanel.add(book2Text);
        bookPanel.add(book3Text);
        bookPanel.add(setBookText);
        this.add(bookPanel);

        this.setVisible(true);
    }
}
class SettingPage extends JPanel {
    JButton back, information, star;
    JPanel pagePanel, informationPanel;
    JLabel head, username, userInfo;
    JCalendar calendar;
    User user;
    private Font youyuan = new Font("幼圆",Font.BOLD,16);
    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);
        pagePanel = new ImagePanel(new ImageIcon("picture\\背景图.jpg").getImage());
        pagePanel.setBounds(0, 0, 170, FRAME_HEIGHT);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\返回箭头.png").getImage(), true));
        back.setBorderPainted(true);
        back.setContentAreaFilled(false);
        back.setBounds(35, 40, 100, 100);
        back.addActionListener(e -> {
            Frame.settingPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
//        information = new JButton("资料");
        information = new JButton("");
        information.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\单词列表.png").getImage(), true));
        information.setBounds(35, 220, 100, 100);
        information.setBorderPainted(false);
        information.setContentAreaFilled(false);

        star = new JButton("收藏单词");
        star.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\单词收藏.png").getImage(), true));
        star.setBorderPainted(true);
        star.setContentAreaFilled(false);
        star.setBounds(35, 400, 100, 100);
        star.addActionListener(e -> {
            Frame.settingPage.setVisible(false);
            Frame.starPage.setVisible(true);
        });
        pagePanel.add(back);
        pagePanel.add(information);
        pagePanel.add(star);
        this.add(pagePanel);

        informationPanel = new JPanel();
        informationPanel.setBounds(190, 0, 610, FRAME_HEIGHT);
        informationPanel.setLayout(null);
        try {
            user = User.loadUserInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Color rim = new Color(229,152,148);
        Color background = new Color(252,232,230);
        //头像
        head = new JLabel();
        head.setBounds(30, 50, 130, 160);
        head.setFont(youyuan);
        head.setText("头像");
        head.setBorder(BorderFactory.createLineBorder(rim, 2));
        informationPanel.add(head);
        //用户名
        username = new JLabel();
        username.setBounds(60, 200, 100, 100);
        username.setFont(youyuan);
        username.setText(user.getNickname());
        informationPanel.add(username);
        //信息
        userInfo = new JLabel();
        userInfo.setBounds(15, 300, 160, 200);
        userInfo.setBorder(BorderFactory.createBevelBorder(1,rim, background));
        userInfo.setBackground(background);
        userInfo.setOpaque(true);
        userInfo.setFont(youyuan);
        userInfo.setText("<html>   手机   "+user.getPhone_number()+"<br/><br/><br/>"+
                "   邮箱   "+user.getEmail()+"<br/><br/><br/>"+
                "   学习目标 "+user.getGoal()+"</html>");
        informationPanel.add(userInfo);

        //日历  ///////////////////////////替换为我写的日历
        calendar = new JCalendar();
        calendar.setBounds(200, 50, 380, 500);
        informationPanel.add(calendar);

        this.add(informationPanel);
    }
}
class ChartPage extends JPanel {
    JButton back;
    JLabel heading;
    JPanel pagePanel, graphPanel;
    DefaultCategoryDataset dataset;
    JFreeChart lineChart;
    ChartPanel chartPanel;
    JCheckBox[] checkBoxes;
    String[] lines = {"book1", "book2", "book3"};
    Curve[] curves = new Curve[3];
    double[] todayValues = {100, 90, 80};
    ArrayList<Date> axisX = new ArrayList<>();
    ArrayList<Double>[] values = new ArrayList[3];
    int chartX = 100, chartY = 150;
    int chartWidth = 900, chartHeight = 650;
    int checkBoxX = 470, checkBoxY = 100;
    int checkBoxWidth = 300, checkBoxHeight = 50;
    public void launchPanel() {
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);
        pagePanel = new JPanel();
        pagePanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT*1/10);
        pagePanel.setBackground(Color.PINK);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setBounds(10, 5,80,50);
        back.addActionListener(e -> {
            Frame.chartPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        pagePanel.add(back);

        heading = new JLabel("今日单词");
        heading.setLocation(FRAME_WIDTH/2-40, 5);
        heading.setSize(150,50);
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("TimesRoman", Font.BOLD, 30));
        pagePanel.add(heading);
        this.add(pagePanel);

        graphPanel = new JPanel();
        graphPanel.setBounds(FRAME_WIDTH/8, FRAME_HEIGHT/6, FRAME_WIDTH*3/4, FRAME_HEIGHT*2/3+55);
        graphPanel.setBackground(Color.PINK);
        graphPanel.setLayout(null);

        //////////////////////////////////////////////
        // 在这里加今日正确率
        dataset = new DefaultCategoryDataset();
        for(int i=0;i<3;i++){
            curves[i] = new Curve();
            values[i] = new ArrayList<>();
            try {
                curves[i].loadCurveData(lines[i], todayValues[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(axisX.isEmpty()){
                axisX = curves[i].getDates();
            }
            values[i] = curves[i].getValues();
            for(int j=0;j<axisX.size();j++){
                dataset.addValue(values[i].get(j), lines[i], (axisX.get(j).getMonth()+1)+"."+axisX.get(j).getDate());
            }
        }

        lineChart = ChartFactory.createLineChart(
                "Memory Curve",
                "Date",
                "Memorability",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chartPanel = new ChartPanel(lineChart);
        chartPanel.setBounds(22, 13, FRAME_WIDTH*4/6+20, FRAME_HEIGHT*2/3);
        chartPanel.setBackground(Color.pink);
//        chartPanel.setVisible(false);
        lineChart.setBackgroundPaint(Color.white);
        graphPanel.add(chartPanel);

        checkBoxes = new JCheckBox[lines.length];
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setBounds(200, FRAME_WIDTH/3+146, 200, 40);
        checkBoxPanel.setBackground(Color.PINK);
        //checkBoxPanel.setBounds(chartX, chartY-checkBoxHeight, chartWidth, checkBoxHeight);
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS));
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(lines[i], true);
            checkBoxes[i].setForeground(Color.black); // 设置文字颜色为红色
            checkBoxes[i].setBackground(Color.pink); // 设置背景颜色为黄色
            checkBoxes[i].setOpaque(true); // 设置为不透明
            checkBoxes[i].addItemListener(e -> updateChart());
            checkBoxPanel.add(checkBoxes[i]);
        }
        graphPanel.add(checkBoxPanel);
        this.add(graphPanel);
    }

    private void updateChart() {
        DefaultCategoryDataset updatedDataset = new DefaultCategoryDataset();
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isSelected()) {
                for (Date datum : axisX) {
                    updatedDataset.addValue(
                            dataset.getValue(lines[i], (datum.getMonth()+1)+"."+datum.getDate()),
                            lines[i],
                            (datum.getMonth()+1)+"."+datum.getDate()
                    );
                }
            }
        }
        lineChart = ChartFactory.createLineChart(
                "Memory Curve",
                "Date",
                "Memorability",
                updatedDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chartPanel.setChart(lineChart);
        chartPanel.revalidate();
        chartPanel.repaint();
    }
}
class StarPage extends JPanel {
    JButton back, information, star;
    JPanel pagePanel, wordPanel;
    WordManagerPanel wordManagerPanel;
    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);
        pagePanel = new ImagePanel(new ImageIcon("picture\\背景图.jpg").getImage());
        pagePanel.setBounds(0, 0, 170, FRAME_HEIGHT);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\返回箭头.png").getImage(), true));
        back.setBorderPainted(true);
        back.setContentAreaFilled(false);
        back.setBounds(35, 40, 100, 100);
        back.addActionListener(e -> {
            Frame.starPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        information = new JButton("资料");
        information.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\单词列表.png").getImage(), true));
        information.setBorderPainted(true);
        information.setContentAreaFilled(false);
        information.setBounds(35, 220, 100, 100);
        information.addActionListener(e -> {
            Frame.starPage.setVisible(false);
            Frame.settingPage.setVisible(true);
        });
        star = new JButton("收藏单词");
        star.setIcon(SwingUtil.createAutoAdjustIcon(new ImageIcon("picture\\单词收藏.png").getImage(), true));
        star.setBorderPainted(false);
        star.setContentAreaFilled(false);
        star.setBounds(35, 400, 100, 100);

        pagePanel.add(back);
        pagePanel.add(information);
        pagePanel.add(star);
        this.add(pagePanel);


        wordPanel = new ImagePanel(new ImageIcon("picture\\淡粉.jpeg").getImage());
        wordPanel.setBounds(195, 20, 570, FRAME_HEIGHT-80);
        //bookPanel.setLayout(new FlowLayout()); // 使用FlowLayout作为示例，可以根据需要更改布局管理器
        wordPanel.setLayout(null);
        wordManagerPanel = new WordManagerPanel();
        wordManagerPanel.setBounds(20, 40, 520, FRAME_HEIGHT-150);
        wordPanel.add(wordManagerPanel);
        this.add(wordPanel);

    }

}
class WordManagerPanel extends JPanel {
    JTable table;
    DefaultTableModel model;
    CheckBoxRenderer checkBoxRenderer;
    CheckBoxEditor checkBoxEditor;
    static Color sPink = new Color(252,232,230);
    static Color sWhite = new Color(254,243,235);

    //添加美工
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("picture\\淡粉.jpeg");
        img.paintIcon(this, g, 0, 0);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int fieldX = 0;
        int fieldY = 0;
        int fieldWeight = getSize().width;
        int fieldHeight = getSize().height;
        Graphics2D g2d=(Graphics2D)g;
        Color bg = new Color(128, 128, 128, 50);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(bg);
        g.fillRoundRect(fieldX, fieldY, fieldWeight, fieldHeight, 20, 20);
        super.paintChildren(g);//可以正常显示该组件中添加的组件
    }

    public WordManagerPanel() {
        // 初始化表格模型
        String[] columnNames = {"选择", "单词", "意义", "词书"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : String.class;
            }
        };

        // 创建复选框渲染器和编辑器
        checkBoxRenderer = new CheckBoxRenderer();
        checkBoxEditor = new CheckBoxEditor();

        // 创建表格
        table = new JTable(model) {
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 0) {
                    return checkBoxEditor;
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 0) {
                    return checkBoxRenderer;
                }
                return super.getCellRenderer(row, column);
            }
        };
        table.setBackground(sPink);

        // 添加数据到表格
        for (int i = 0; i < 5; i++) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(false); // 多选框状态，默认未选中
            rowData.add("单词" + (i + 1));
            rowData.add("意义" + (i + 1));
            rowData.add("词书" + (i + 1)); // 添加词书数据
            model.addRow(rowData);
        }

        // 设置复选框列的宽度
        TableColumn checkBoxColumn = table.getColumnModel().getColumn(0);
        checkBoxColumn.setPreferredWidth(30);
        checkBoxColumn.setMaxWidth(30);

        // 添加表格到面板
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // 自定义复选框渲染器
    static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected((Boolean) value);
            setBackground(isSelected ? Color.white: sPink); // 根据是否选中设置不同的背景颜色
            return this;
        }
    }

    // 自定义复选框编辑器
    static class CheckBoxEditor extends DefaultCellEditor {
        public CheckBoxEditor() {
            super(new JCheckBox());
            ((JCheckBox) editorComponent).setBackground(sWhite);  // 设置编辑器的背景颜色
        }

        @Override
        public Object getCellEditorValue() {
            return ((JCheckBox)editorComponent).isSelected();
        }
    }
}
