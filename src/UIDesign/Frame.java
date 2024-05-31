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

    public void launchFrame(){
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
        reciteword.launchPanel();
        reciteword.setVisible(false);
        this.add(reciteword);


        this.setTitle("单词记忆软件");
        this.setVisible(true);
    }
}
class HomePage extends JPanel {
    JButton learn, setting, graph;
    JPanel pagePanel, bookPanel;
    int bookMarginWidth = 300, bookMarginHeight = 100;
    int bookSpaceHorizontal = 450, bookSpaceVertical = 400;
    int bookButtonWidth = 200, bookButtonHeight = 300;
    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        pagePanel = new JPanel();
        pagePanel.setBounds(0, 0, 170, FRAME_HEIGHT);
        pagePanel.setBackground(Color.PINK);
        pagePanel.setLayout(null);
        learn = new JButton("背单词");
        learn.setBounds(35, 40, 100, 100);
        learn.addActionListener(e -> {
            Frame.homePage.setVisible(false);
            Frame.reciteword.setVisible(true);
        });
        setting = new JButton("我的");
        setting.setBounds(35, 220, 100, 100);
        setting.addActionListener(e -> {
            Frame.homePage.setVisible(false);
            Frame.settingPage.setVisible(true);
        });
        graph = new JButton("记忆曲线");
        graph.setBounds(35, 400, 100, 100);
        graph.addActionListener(e -> {
            Frame.homePage.setVisible(false);
            Frame.chartPage.setVisible(true);
        });
        pagePanel.add(learn);
        pagePanel.add(setting);
        pagePanel.add(graph);
        this.add(pagePanel);


        bookPanel = new JPanel();
        bookPanel.setBounds(190, 0, 610, FRAME_HEIGHT);
//        bookPanel.setBackground(Color.CYAN);
        //bookPanel.setLayout(new FlowLayout()); // 使用FlowLayout作为示例，可以根据需要更改布局管理器
        bookPanel.setLayout(null);
        JButton book1 = new JButton("词书1");
        book1.setBounds(30, 50, 200, 150);
        JButton book2 = new JButton("词书2");
        book2.setBounds(340, 50, 200, 150);
        JButton book3 = new JButton("词书3");
        book3.setBounds(30, 320, 200, 150);
        JButton setBook = new JButton("修改词书");
        setBook.setBounds(340, 320, 200, 150);
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
    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);
        pagePanel = new JPanel();
        pagePanel.setBounds(0, 0, 170, FRAME_HEIGHT);
        pagePanel.setBackground(Color.PINK);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setBounds(35, 40, 100, 100);
        back.addActionListener(e -> {
            Frame.settingPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        information = new JButton("资料");
        information.setBounds(35, 220, 100, 100);
        star = new JButton("收藏单词");
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

        //头像
        head = new JLabel();
        head.setBounds(30, 50, 130, 160);
        head.setText("头像");
        head.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        informationPanel.add(head);
        //用户名
        username = new JLabel();
        username.setBounds(60, 200, 100, 100);
        username.setText(user.getNickname());
        informationPanel.add(username);
        //信息
        userInfo = new JLabel();
        userInfo.setBounds(15, 300, 160, 200);

        userInfo.setText("<html>   手机   "+user.getPhone_number()+"<br/><br/><br/>"+
                        "   邮箱   "+user.getEmail()+"<br/><br/><br/>"+
                        "   学习目标 "+user.getGoal()+"</html>");
        userInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
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
        pagePanel = new JPanel();
        pagePanel.setBounds(0, 0, 170, FRAME_HEIGHT);
        pagePanel.setBackground(Color.PINK);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setBounds(35, 40, 100, 100);
        back.addActionListener(e -> {
            Frame.starPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        information = new JButton("资料");
        information.setBounds(35, 220, 100, 100);
        information.addActionListener(e -> {
            Frame.starPage.setVisible(false);
            Frame.settingPage.setVisible(true);
        });
        star = new JButton("收藏单词");
        star.setBounds(35, 400, 100, 100);

        pagePanel.add(back);
        pagePanel.add(information);
        pagePanel.add(star);
        this.add(pagePanel);


        wordPanel = new JPanel();
        wordPanel.setBounds(195, 20, 570, FRAME_HEIGHT-80);
        wordPanel.setBackground(Color.PINK);
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

    public WordManagerPanel() {
        this.setBackground(Color.pink);
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
        table.setBackground(Color.pink);

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
            setBackground(isSelected ? Color.white: Color.pink); // 根据是否选中设置不同的背景颜色
            return this;
        }
    }

    // 自定义复选框编辑器
    static class CheckBoxEditor extends DefaultCellEditor {
        public CheckBoxEditor() {
            super(new JCheckBox());
            ((JCheckBox) editorComponent).setBackground(Color.pink);  // 设置编辑器的背景颜色
        }

        @Override
        public Object getCellEditorValue() {
            return ((JCheckBox)editorComponent).isSelected();
        }
    }
}
