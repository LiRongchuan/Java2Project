package UIDesign;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;


import static UIDesign.Util.*;

public class Frame extends JFrame {
    public static HomePage homePage;
    public static SettingPage settingPage;
    public static ChartPage chartPage;

    public static StarPage starPage;
    public static Reciteword reciteword;

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
        this.add(settingPage);
        /**/
        starPage = new StarPage();
        starPage.launchPanel();
        this.add(starPage);
        /**/
        chartPage = new ChartPage();
        this.add(chartPage);
        chartPage.launchPanel();



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
        pagePanel.setBounds(0, 0, FRAME_WIDTH*2/10, FRAME_HEIGHT);
        pagePanel.setLayout(null);
        learn = new JButton("背单词");
        learn.setBounds(pageMarginWidth, pageMarginHeight, pageButtonSize, pageButtonSize);
        learn.addActionListener(e -> Frame.homePage.setVisible(false));
        setting = new JButton("我的");
        setting.setBounds(pageMarginWidth, pageMarginHeight+pageSpace, pageButtonSize, pageButtonSize);
        setting.addActionListener(e -> {
            Frame.homePage.setVisible(false);

            Frame.settingPage.setVisible(true);
        });
        graph = new JButton("记忆曲线");
        graph.setBounds(pageMarginWidth, pageMarginHeight+2*pageSpace, pageButtonSize, pageButtonSize);
        graph.addActionListener(e -> {
            Frame.homePage.setVisible(false);

            Frame.chartPage.setVisible(true);
        });
        pagePanel.add(learn);
        pagePanel.add(setting);
        pagePanel.add(graph);
        this.add(pagePanel);


        bookPanel = new JPanel();
        bookPanel.setBounds(FRAME_WIDTH*2/10, 0, FRAME_WIDTH*8/10, FRAME_HEIGHT);
        //bookPanel.setLayout(new FlowLayout()); // 使用FlowLayout作为示例，可以根据需要更改布局管理器
        bookPanel.setLayout(null);
        JButton book1 = new JButton("词书1");
        book1.setBounds(bookMarginWidth, bookMarginHeight, bookButtonWidth, bookButtonHeight);
        JButton book2 = new JButton("词书2");
        book2.setBounds(bookMarginWidth + bookSpaceHorizontal, bookMarginHeight, bookButtonWidth, bookButtonHeight);
        JButton book3 = new JButton("词书3");
        book3.setBounds(bookMarginWidth, bookMarginHeight + bookSpaceVertical, bookButtonWidth, bookButtonHeight);
        JButton setBook = new JButton("修改词书");
        setBook.setBounds(bookMarginWidth + bookSpaceHorizontal, bookMarginHeight + bookSpaceVertical, bookButtonWidth, bookButtonHeight);
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
    int headX = 200, headY = 100;
    int headWidth = 200, headHeight = 200;
    int usernameX = 200, usernameY = 320;
    int usernameWidth = 200, usernameHeight = 40;
    int userInfoX = 170, userInfoY = 400;
    int userInfoWidth = 260, userInfoHeight = 300;
    int calendarX = 450, calendarY = 150;
    int calendarWidth = 700, calendarHeight = 600;
    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);
        pagePanel = new JPanel();
        pagePanel.setBounds(0, 0, FRAME_WIDTH*2/10, FRAME_HEIGHT);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setBounds(pageMarginWidth, pageMarginHeight, pageButtonSize, pageButtonSize);
        back.addActionListener(e -> {
            Frame.settingPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        information = new JButton("资料");
        information.setBounds(pageMarginWidth, pageMarginHeight+pageSpace, pageButtonSize, pageButtonSize);
        star = new JButton("收藏单词");
        star.setBounds(pageMarginWidth, pageMarginHeight+2*pageSpace, pageButtonSize, pageButtonSize);
        star.addActionListener(e -> {
            Frame.settingPage.setVisible(false);
            Frame.starPage.setVisible(true);
        });
        pagePanel.add(back);
        pagePanel.add(information);
        pagePanel.add(star);
        this.add(pagePanel);

        informationPanel = new JPanel();
        informationPanel.setBounds(FRAME_WIDTH*2/10, 0, FRAME_WIDTH*8/10, FRAME_HEIGHT);
        informationPanel.setLayout(null);
        //头像
        head = new JLabel();
        head.setBounds(headX, headY, headWidth, headHeight);
        head.setText("头像");
        head.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        informationPanel.add(head);
        //用户名
        username = new JLabel();
        username.setBounds(usernameX, usernameY, usernameWidth, usernameHeight);
        username.setText("用户名");
        informationPanel.add(username);
        //信息
        userInfo = new JLabel();
        userInfo.setBounds(userInfoX, userInfoY, userInfoWidth, userInfoHeight);
        userInfo.setText("<html>手机   "+"00011110000"+"<br/><br/><br/>"+
                        "邮箱   "+"123456@qq.com"+"<br/><br/><br/>"+
                        "学习目标 "+"托福</html>");
        userInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        informationPanel.add(userInfo);

        //日历
        calendar = new JCalendar();
        calendar.setBounds(calendarX, calendarY, calendarWidth, calendarHeight);
        informationPanel.add(calendar);

        this.add(informationPanel);
    }
}
class ChartPage extends JPanel {
    JButton back;
    JPanel pagePanel, graphPanel;
    DefaultCategoryDataset dataset;
    JFreeChart lineChart;
    ChartPanel chartPanel;
    JCheckBox[] checkBoxes;
    String[] lines = {"词书1", "词书2", "词书3"};
    String[] axisX = {"5月26日", "5月27日", "5月28日", "5月29日", "5月30日", "5月31日"};
    int chartX = 100, chartY = 150;
    int chartWidth = 900, chartHeight = 650;
    int checkBoxX = 470, checkBoxY = 100;
    int checkBoxWidth = 300, checkBoxHeight = 50;
    public void launchPanel() {
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);
        pagePanel = new JPanel();
        pagePanel.setBounds(0, 0, FRAME_WIDTH*2/10, FRAME_HEIGHT);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setBounds(pageMarginWidth, pageMarginHeight, pageButtonSize, pageButtonSize);
        back.addActionListener(e -> {
            Frame.settingPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        pagePanel.add(back);
        this.add(pagePanel);

        graphPanel = new JPanel();
        graphPanel.setBounds(FRAME_WIDTH*2/10, 0, FRAME_WIDTH*8/10, FRAME_HEIGHT);
        graphPanel.setLayout(null);
        dataset = new DefaultCategoryDataset();
        for (String lineName : lines) {
            for (String datum : axisX) {
                dataset.addValue(Math.random() * 100, lineName, datum);
            }
        }

        lineChart = ChartFactory.createLineChart(
                "记忆曲线",
                "日期",
                "记忆率",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chartPanel = new ChartPanel(lineChart);
        chartPanel.setBounds(chartX, chartY, chartWidth, chartHeight);
        graphPanel.add(chartPanel);

        checkBoxes = new JCheckBox[lines.length];
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setBounds(checkBoxX, checkBoxY, checkBoxWidth, checkBoxHeight);
        //checkBoxPanel.setBounds(chartX, chartY-checkBoxHeight, chartWidth, checkBoxHeight);
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS));
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(lines[i], true);
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
                for (String datum : axisX) {
                    updatedDataset.addValue(
                            dataset.getValue(lines[i], datum),
                            lines[i],
                            datum
                    );
                }
            }
        }
        lineChart = ChartFactory.createLineChart(
                "Sample Line Chart",
                "Month",
                "Value",
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
    int wordPanelX = 200, wordPanelY = 200;
    int wordPanelWidth = 800, wordPanelHeight = 800;
    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);
        pagePanel = new JPanel();
        pagePanel.setBounds(0, 0, FRAME_WIDTH*2/10, FRAME_HEIGHT);
        pagePanel.setLayout(null);
        back = new JButton("返回");
        back.setBounds(pageMarginWidth, pageMarginHeight, pageButtonSize, pageButtonSize);
        back.addActionListener(e -> {
            Frame.starPage.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        information = new JButton("资料");
        information.setBounds(pageMarginWidth, pageMarginHeight+pageSpace, pageButtonSize, pageButtonSize);
        information.addActionListener(e -> {
            Frame.starPage.setVisible(false);
            Frame.settingPage.setVisible(true);
        });
        star = new JButton("收藏单词");
        star.setBounds(pageMarginWidth, pageMarginHeight+2*pageSpace, pageButtonSize, pageButtonSize);

        pagePanel.add(back);
        pagePanel.add(information);
        pagePanel.add(star);
        this.add(pagePanel);


        wordPanel = new JPanel();
        wordPanel.setBounds(FRAME_WIDTH*2/10, 0, FRAME_WIDTH*8/10, FRAME_HEIGHT);
        //bookPanel.setLayout(new FlowLayout()); // 使用FlowLayout作为示例，可以根据需要更改布局管理器
        wordPanel.setLayout(null);
        wordManagerPanel = new WordManagerPanel();
        wordManagerPanel.setBounds(wordPanelX, wordPanelY, wordPanelWidth, wordPanelHeight);
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
            return this;
        }
    }

    // 自定义复选框编辑器
    static class CheckBoxEditor extends DefaultCellEditor {
        public CheckBoxEditor() {
            super(new JCheckBox());
        }

        @Override
        public Object getCellEditorValue() {
            return ((JCheckBox)editorComponent).isSelected();
        }
    }
}