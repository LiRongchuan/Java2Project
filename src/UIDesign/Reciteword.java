package UIDesign;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

import static UIDesign.Util.*;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Reciteword extends JPanel {
    public static JButton back, a, b, c, d, collect, hint, next;
    public static JPanel headingPanel, mainPanel, recordPanel,selectBookPanel;
    public static JLabel heading, word, wordSound, meaning, success, select;
    public static String ans;
    public static int pointer = 1;
    JCalendar calendar;

    public void launchPanel(){
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setVisible(false);

        headingPanel = new JPanel();
        headingPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT* 1/10);
        headingPanel.setBackground(Color.PINK);
        headingPanel.setLayout(null);
        back = new JButton("返回");
        back.setBounds(10,5, 80, 50);
        back.addActionListener(e -> {
            Frame.reciteword.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        headingPanel.add(back);

        heading = new JLabel("今日单词");
        heading.setLocation(FRAME_WIDTH/2, 0);
        heading.setSize(150,50);
        heading.setForeground(Color.BLACK);
//        heading.setBackground(Color.RED);
//        heading.setOpaque(true);
        heading.setFont(new Font("TimesRoman", Font.BOLD, 15));
        headingPanel.add(heading);
        this.add(headingPanel);

        int main_x = FRAME_WIDTH*1/8;
        int main_y = FRAME_HEIGHT/6;
        int main_width = FRAME_WIDTH*3/4;
        int main_height = FRAME_HEIGHT*2/3;

        //选择词书 panel
        selectBookPanel = new JPanel();
        selectBookPanel.setBounds(main_x, main_y, main_width, main_height);
        selectBookPanel.setBackground(Color.PINK);
        selectBookPanel.setLayout(null);
        selectBookPanel.setVisible(true);

        //词书列表
        select = new JLabel("选择词书");
        select.setBounds(main_width/2-50, main_y/2-10, 250, 100);
        select.setFont(new Font("TimesRoman", Font.BOLD, 30));
        selectBookPanel.add(select);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(main_x+80, main_y+40, 250, 50);
        comboBox.setBackground(Color.white);
        comboBox.addItem("四级词汇");
        comboBox.addItem("六级词汇");
//        comboBox.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                setText(value.toString());
//                if (isSelected) {
//                    setBackground(Color.LIGHT_GRAY);
//                    setForeground(Color.BLUE);
//                } else {
//                    setBackground(Color.WHITE);
//                    setForeground(Color.BLACK);
//                }
//                setFont(new Font("Arial", Font.BOLD, 14));
//                return this;
//            }
//        });
        selectBookPanel.add(comboBox);

        //开始背词 button
        JButton start = new JButton("开始");
        start.setBounds(main_width/2-40, main_y+150, 80, 50);
        start.setBackground(Color.white);
        selectBookPanel.add(start);
        start.addActionListener(e -> {
            selectBookPanel.setVisible(false);
            mainPanel.setVisible(true);
            next.setVisible(true);
        });
        this.add(selectBookPanel);


        //主panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.PINK);
        mainPanel.setBounds(main_x, main_y, main_width, main_height);
        mainPanel.setLayout(null);
        mainPanel.setVisible(false);

        //单词 label
        String eng = "apple";
        word = new JLabel();
        word.setText(eng);
        word.setBounds(main_width/2-50, main_y/2-50, 250, 100);
        word.setFont(new Font("TimesRoman", Font.BOLD, 30));
        mainPanel.add(word);

        //音标 label
        String sound = "[æpl]";
        wordSound = new JLabel();
        wordSound.setText(sound);
        wordSound.setBounds(main_width/2-35, main_y/2-10, 180, 80);
        wordSound.setFont(new Font("TimesRoman", Font.BOLD, 20));
        mainPanel.add(wordSound);

        //收藏 button
        collect = new JButton("star");
        collect.setBounds(main_width/2+100, main_height/7, 80, 50);
        collect.setBackground(Color.white);
        collect.addActionListener(e -> {
            //TODO：怎么加入收藏列表里
            //收藏
            collect.setBackground(Color.YELLOW);
        });
        mainPanel.add(collect);

        //提示 button call 直接加label
        hint = new JButton("hint");
        hint.setBounds(main_width/2+200, main_height/7, 80, 50);
        hint.setBackground(Color.white);
        hint.addActionListener(e -> {
            //提示
            hint.setBackground(Color.CYAN);
            meaning.setVisible(true);
        });
        mainPanel.add(hint);

        //释义详情 label
        meaning = new JLabel();
        String mean = "n. 苹果";
        meaning.setText(mean);
        meaning.setBounds(main_width/2-40, main_y/2+10, 250, 100);
        meaning.setVisible(false);
        mainPanel.add(meaning);


        //4个选项 button
//        AtomicReference<String> ans = new AtomicReference<>("苹果");
        ans = "苹果";

        a = new JButton();
        String optionA = "苹果";
        a.setText(optionA);
        a.setBounds(main_width/2-100, main_y/2+80+10, 200, 40);
        b = new JButton();
        String optionB = "香蕉";
        b.setText(optionB);
        b.setBounds(main_width/2-100, main_y/2+145+10, 200, 40);
        c = new JButton();
        String optionC = "橘子";
        c.setText(optionC);
        c.setBounds(main_width/2-100, main_y/2+145+65+10, 200, 40);
        d = new JButton();
        String optionD = "西瓜";
        d.setText(optionD);
        d.setBounds(main_width/2-100, main_y/2+145+65+65+10, 200, 40);

        a.addActionListener(e -> {
            if(a.getText().equals(ans)){
                //正确
                a.setBackground(Color.GREEN);
            }else{
                //错误
                a.setBackground(Color.red);
                if(b.getText().equals(ans)) {
                    b.setBackground(Color.GREEN);
                }else if(c.getText().equals(ans)) {
                    c.setBackground(Color.GREEN);
                }else{
                    d.setBackground(Color.GREEN);
                }
            }
            meaning.setVisible(true);
        });
        b.addActionListener(e -> {
            if(b.getText().equals(ans)){
                //正确
                b.setBackground(Color.GREEN);
            }else{
                //错误
                b.setBackground(Color.red);
                if(a.getText().equals(ans)) {
                    a.setBackground(Color.GREEN);
                }else if(c.getText().equals(ans)) {
                    c.setBackground(Color.GREEN);
                }else{
                    d.setBackground(Color.GREEN);
                }
            }
            meaning.setVisible(true);
        });
        c.addActionListener(e -> {
            if(c.getText().equals(ans)){
                //正确
                c.setBackground(Color.GREEN);
            }else{
                //错误
                c.setBackground(Color.red);
                if(b.getText().equals(ans)) {
                    b.setBackground(Color.GREEN);
                }else if(a.getText().equals(ans)) {
                    a.setBackground(Color.GREEN);
                }else{
                    d.setBackground(Color.GREEN);
                }
            }
            meaning.setVisible(true);
        });
        d.addActionListener(e -> {
            if(d.getText().equals(ans)){
                //正确
                d.setBackground(Color.GREEN);
            }else{
                //错误
                d.setBackground(Color.red);
                if(b.getText().equals(ans)) {
                    b.setBackground(Color.GREEN);
                }else if(c.getText().equals(ans)) {
                    c.setBackground(Color.GREEN);
                }else{
                    a.setBackground(Color.GREEN);
                }
            }
            meaning.setVisible(true);
        });
        a.setBackground(Color.white);
        b.setBackground(Color.white);
        c.setBackground(Color.white);
        d.setBackground(Color.white);
        mainPanel.add(a);
        mainPanel.add(b);
        mainPanel.add(c);
        mainPanel.add(d);

        //打卡panel 用于记录打卡情况
        recordPanel = new JPanel();
        recordPanel.setBounds(main_x, main_y, main_width, main_height);
        recordPanel.setBackground(Color.PINK);
        recordPanel.setLayout(null);
        recordPanel.setVisible(false);

//        calendar = new JCalendar();
//        calendar.setBounds(main_x, main_y, main_width, main_height-30);
//        calendar.setWeekdayForeground(Color.cyan);
//        calendar.setSundayForeground(Color.BLUE);
//        calendar.setDecorationBackgroundColor(Color.orange);
////        calendar.setVisible(true);
//        recordPanel.add(calendar);

        Calendar calendar = Calendar.getInstance();  // 获取当前日期和时间的Calendar对象
        int day = calendar.get(Calendar.DAY_OF_MONTH); // 当前日
        CalendarPanel calendarPanel = new CalendarPanel(day);
        calendarPanel.setBounds(main_x-35, main_y-20, main_width*7/8, main_height/2);
        calendarPanel.setBackground(Color.PINK);
        recordPanel.add(calendarPanel);


        //打卡成功 label
        success = new JLabel("打卡成功");
        success.setBounds(main_x+250, main_y+300, 150, 100);
        success.setFont(new Font("Monospaced", Font.BOLD, 30));
        success.setForeground(Color.GREEN);
        success.setVisible(false);
//        recordPanel.add(success);
        this.add(success);
        this.add(recordPanel);

        //下一个  button 不在mainPanel中
        next = new JButton("next");
        next.setBounds(main_width, main_height+20, 80, 50);
        next.setVisible(false);

        //arr中存储今天要学的单词

        //TODO: 从词书txt中获取今天要学的单词
        String[][] arr = new String[2][8];
        arr[1][0] = "banana";//word
        arr[1][1] = "[bə'nɑːnə]";//音标
        arr[1][2] = "n. 香蕉";//meaning
        arr[1][3] = "香蕉";//a
        arr[1][4] = "橘子";//b
        arr[1][5] = "西瓜";//c
        arr[1][6] = "苹果";//d
        arr[1][7] = "香蕉";//ans

        next.addActionListener(e -> {
            if(pointer <= arr.length-1){
                //下一个单词
                a.setBackground(Color.white);
                b.setBackground(Color.white);
                c.setBackground(Color.white);
                d.setBackground(Color.white);
                //下一个单词的信息
                word.setText(arr[pointer][0]);
                wordSound.setText(arr[pointer][1]);
                meaning.setText(arr[pointer][2]);
                a.setText(arr[pointer][3]);
                b.setText(arr[pointer][4]);
                c.setText(arr[pointer][5]);
                d.setText(arr[pointer][6]);
                ans = arr[pointer][7];
                meaning.setVisible(false);
                hint.setBackground(Color.white);
                collect.setBackground(Color.white);
                pointer++;
            }else{
                //结束
                //返回主页
                mainPanel.setVisible(false);
                recordPanel.setVisible(true);
                next.setVisible(false);
                success.setVisible(true);
            }

        });
        this.add(next);


        this.add(mainPanel);
        this.setVisible(true);
    }



}


class CalendarPanel extends JPanel {
    private final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private int today;
    public CalendarPanel(int today) {
        this.today = today;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 设置字体和颜色
        g.setFont(new Font("Monospaced", Font.BOLD, 17));
        g.setColor(Color.BLACK);

        // 获取当月的天数和月初的星期
        Calendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, month, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 绘制星期标题
        for (int i = 0; i < 7; i++) {
            g.drawString(days[i], 50 + i * 60, 40);
        }

        // 绘制日期
        int x = (startDay - 1) * 60; // 初始x位置偏移
        int y = 60; // 初始y位置
        for (int i = 1; i <= numberOfDays; i++) {
            if (i == today) {
                // 设置特定日期的字体和颜色
                g.setFont(new Font("Monospaced", Font.BOLD, 17));
                g.setColor(Color.RED);
            } else {
                // 重置为默认字体和颜色
                g.setFont(new Font("Monospaced", Font.BOLD, 17));
                g.setColor(Color.BLACK);
            }

            g.drawString(Integer.toString(i), 60 + x, y);
            x += 60;
            if ((i + startDay - 1) % 7 == 0) { // 换行
                x = 0;
                y += 30;
            }
        }
    }
}
