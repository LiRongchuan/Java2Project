package UIDesign;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//import static Back_end.WordBook;
import static Back_end.WordBook.*;
import static UIDesign.Util.*;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Back_end.WordBook;
public class Reciteword extends JPanel {
    public static JButton back, a, b, c, d, collect, hint, next,learnagain;
    public static JPanel headingPanel, mainPanel, recordPanel,selectBookPanel;
    public static JLabel heading, word, wordSound, meaning, success, select;
    public static String ans;
    public static int pointer;
    public static boolean correct;
    public static int startid;
    public static String wrongFile = "wrong.txt";
    public static String selectedbook;
    public static String path;
    public static List<String[]> today;
    public static String eng, sound, mean, optionA, optionB, optionC, optionD;
    JCalendar calendar;

    public void launchPanel() throws IOException {
        this.setLayout(null);
        this.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        this.setBackground(Color.white);
        this.setVisible(false);

//        headingPanel = new JPanel();
        headingPanel = new ImagePanel(new ImageIcon("panel1.jpg").getImage());
        headingPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT* 1/10);
//        headingPanel.setBackground(Color.PINK);
//        JPanel panel = new ImagePanel(new ImageIcon("path/to/your/image.jpg").getImage());
        headingPanel.setLayout(null);


        // 创建按钮
//        back = new JButton("返回");
        back = new IconButton(new ImageIcon("backbutton.png"));
//        back.setBounds(10,5, 80, 50);
        back.setSize(75, 30);
        back.setLocation(25,14);
        back.addActionListener(e -> {
            Frame.reciteword.setVisible(false);
            Frame.homePage.setVisible(true);
        });
        headingPanel.add(back);

        heading = new JLabel("今日单词");
        heading.setLocation(FRAME_WIDTH/2-60, 7);
        heading.setSize(150,50);
        heading.setForeground(Color.BLACK);
//        heading.setBackground(Color.RED);
//        heading.setOpaque(true);
        heading.setFont(new Font("幼圆", Font.BOLD, 30));
        headingPanel.add(heading);
        this.add(headingPanel);

        int main_x = FRAME_WIDTH*1/8;
        int main_y = FRAME_HEIGHT/6;
        int main_width = FRAME_WIDTH*3/4;
        int main_height = FRAME_HEIGHT*2/3;

        //选择词书 panel
//        selectBookPanel = new JPanel();
        selectBookPanel = new RoundImagePanel(new ImageIcon("panel1.jpg").getImage());
        selectBookPanel.setBounds(main_x, main_y, main_width, main_height);
//        selectBookPanel.setBackground(Color.PINK);
        selectBookPanel.setLayout(null);
        selectBookPanel.setVisible(true);

        //词书列表
        select = new JLabel("选择词书");
        select.setBounds(main_width/2-50, main_y/2-10, 250, 100);
        select.setFont(new Font("幼圆", Font.BOLD, 30));
        selectBookPanel.add(select);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(main_x+80, main_y+40, 250, 50);
        comboBox.setBackground(pink1);


        //所有文件名+当前想要的文件名的路径
        List<String> files = filePath("BookList.txt");

        for (int i = 0; i < files.size(); i++) {
            comboBox.addItem(files.get(i));
        }
//        comboBox.addItem("四级词汇");
//        comboBox.addItem("六级词汇");

//        comboBox.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    // 获取选中项的文本
//                    selectedbook = (String) e.getItem();
//                    System.out.println("选中的文本是: " + selectedbook);
//                }
//            }
//        });

        //当前选择的词书的名字
//        selectedbook = (String) comboBox.getSelectedItem();
//        System.out.println(selectedbook);
//        //path是当前选择的词书的路径
//        String path = findPath("BookList.txt" ,selectedbook);
//        selectBookPanel.add(comboBox);


        //开始背词 button
        JButton start = new RoundedButton();
        start.setText("START");
        start.setFont(new Font("Monospaced", Font.BOLD, 20));
        start.setBounds(main_width/2-40, main_y+150, 130, 50);
        start.setBackground(Color.white);
        selectBookPanel.add(start);
        start.addActionListener(e -> {
            selectBookPanel.setVisible(false);
            mainPanel.setVisible(true);
            next.setVisible(true);
            selectedbook = (String) comboBox.getSelectedItem();
            try {
                path = findPath("BookList.txt" ,selectedbook);
                startid = 0;
                startid = pre_record(selectedbook);
//                List<String[]> today;
                today = todayRecite(startid, path, wrongFile);
                word.setText(today.get(0)[0]);
                wordSound.setText(today.get(0)[1]);
                meaning.setText(today.get(0)[2]);
                meaning.setFont(new Font("Monospaced", Font.BOLD, 15));
                a.setText(today.get(0)[3]);
                a.setFont(new Font("幼圆", Font.BOLD, 15));
                b.setText(today.get(0)[4]);
                b.setFont(new Font("幼圆", Font.BOLD, 15));
                c.setText(today.get(0)[5]);
                c.setFont(new Font("幼圆", Font.BOLD, 15));
                d.setText(today.get(0)[6]);
                d.setFont(new Font("幼圆", Font.BOLD, 15));
                ans = today.get(0)[7];
                if(checkstar(word.getText())){ //在收藏中
                    collect.setIcon(new ImageIcon("star_light.png"));
                    System.out.println("y");
                }else{//不在收藏中
                    collect.setIcon(new ImageIcon("star.png"));
                    System.out.println("n");
                }
                System.out.println(today.size());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        selectBookPanel.add(comboBox);
        this.add(selectBookPanel);


        //主panel
//        mainPanel = new JPanel();
        mainPanel = new RoundImagePanel(new ImageIcon("panel1.jpg").getImage());
//        mainPanel.setBackground(Color.PINK);
        mainPanel.setBounds(main_x, main_y, main_width, main_height);
        mainPanel.setLayout(null);
        mainPanel.setVisible(false);


        //拿到要用的今日背单词的数组 String[30][8]
//        startid = 0;
//        startid = pre_record(selectedbook);
//        List<String[]> today;
//        today = todayRecite(startid, path, wrongFile);


        //单词 label
//        String eng = today.get(0)[0];
        word = new JLabel();
//        word.setText(eng);
        word.setBounds(main_width/2-65, main_y/2-50, 300, 100);
        word.setFont(new Font("Monospaced", Font.BOLD, 30));
        mainPanel.add(word);

        //音标 label
//        String sound = today.get(0)[1];
        wordSound = new JLabel();
//        wordSound.setText(sound);
        wordSound.setBounds(main_width/2-57, main_y/2-10, 180, 80);
        wordSound.setFont(new Font("Monospaced", Font.BOLD, 20));
        mainPanel.add(wordSound);

        //收藏 button
        collect = new IconButton(new ImageIcon("star.png"));
        collect.setBounds(main_width/2+180, main_height/7-40, 30, 30);
//        if(checkstar(word.getText())){ //在收藏中
//            collect.setIcon(new ImageIcon("star_light.png"));
//            System.out.println("y");
//        }else{//不在收藏中
//            collect.setIcon(new ImageIcon("star.png"));
//            System.out.println("n");
//        }
        collect.addActionListener(e -> {
            //TODO：怎么加入收藏列表里
            //收藏
            //加入star.txt中
            try {
                if(!checkstar(word.getText())){//不在收藏中
                    collect.setIcon(new ImageIcon("star_light.png"));
                    star(word.getText(), wordSound.getText(), meaning.getText(), a.getText(), b.getText(), c.getText(), d.getText(), ans);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
//            collect.setBackground(Color.YELLOW);
        });
        mainPanel.add(collect);

        //提示 button call 直接加label
//        hint = new JButton("hint");
        hint = new IconButton(new ImageIcon("hint.png"));
        hint.setBounds(main_width/2+240, main_height/7-40, 30, 30);
        hint.setBackground(Color.white);
        hint.addActionListener(e -> {
            //提示
//            hint.setBackground(Color.CYAN);
            hint.setIcon(new ImageIcon("hint_light.png"));
            meaning.setVisible(true);
        });
        mainPanel.add(hint);

        //释义详情 label
        meaning = new JLabel();
//        String mean = today.get(0)[2];
//        meaning.setText(mean);
        meaning.setFont(new Font("Monospaced", Font.BOLD, 15));
        meaning.setBounds(main_width/2-40, main_y/2+10, 250, 100);
        meaning.setVisible(false);
        mainPanel.add(meaning);


        //4个选项 button
//        AtomicReference<String> ans = new AtomicReference<>("苹果");
//        ans = today.get(0)[7];

//        a = new JButton();
        a = new RoundedButton();
//        String optionA = today.get(0)[3];
//        a.setText(optionA);
        a.setFont(new Font("Monospaced", Font.BOLD, 15));
        a.setBounds(main_width/2-100, main_y/2+80+10, 200, 40);
//        b = new JButton();
        b = new RoundedButton();
//        String optionB = today.get(0)[4];
//        b.setText(optionB);
        b.setFont(new Font("Monospaced", Font.BOLD, 15));
        b.setBounds(main_width/2-100, main_y/2+145+10, 200, 40);
//        c = new JButton();
        c = new RoundedButton();
//        String optionC = today.get(0)[5];
//        c.setText(optionC);
        c.setFont(new Font("Monospaced", Font.BOLD, 15));
        c.setBounds(main_width/2-100, main_y/2+145+65+10, 200, 40);
//        d = new JButton();
        d = new RoundedButton();
//        String optionD = today.get(0)[6];
//        d.setText(optionD);
        d.setFont(new Font("Monospaced", Font.BOLD, 15));
        d.setBounds(main_width/2-100, main_y/2+145+65+65+10, 200, 40);

//        correct = false;
        a.addActionListener(e -> {
            if(a.getText().equals(ans)){
//                correct = true;
                //正确
                a.setBackground(c_green);
            }else{
                //错误
                a.setBackground(w_red);
                if(b.getText().equals(ans)) {
                    b.setBackground(c_green);
                }else if(c.getText().equals(ans)) {
                    c.setBackground(c_green);
                }else{
                    d.setBackground(c_green);
                }
                try {
                    wrong(word.getText(), wordSound.getText(), meaning.getText(), a.getText(), b.getText(), c.getText(), d.getText(), ans);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
            meaning.setVisible(true);
        });
        b.addActionListener(e -> {
            if(b.getText().equals(ans)){
                //正确
                correct = true;
                b.setBackground(c_green);
            }else{
                //错误
                b.setBackground(w_red);
                if(a.getText().equals(ans)) {
                    a.setBackground(c_green);
                }else if(c.getText().equals(ans)) {
                    c.setBackground(c_green);
                }else{
                    d.setBackground(c_green);
                }
                try {
                    wrong(word.getText(), wordSound.getText(), meaning.getText(), a.getText(), b.getText(), c.getText(), d.getText(), ans);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            meaning.setVisible(true);
        });
        c.addActionListener(e -> {
            if(c.getText().equals(ans)){
                //正确
                correct = true;
                c.setBackground(c_green);
            }else{
                //错误
                c.setBackground(w_red);
                if(b.getText().equals(ans)) {
                    b.setBackground(c_green);
                }else if(a.getText().equals(ans)) {
                    a.setBackground(c_green);
                }else{
                    d.setBackground(c_green);
                }
                try {
                    wrong(word.getText(), wordSound.getText(), meaning.getText(), a.getText(), b.getText(), c.getText(), d.getText(), ans);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            meaning.setVisible(true);
        });
        d.addActionListener(e -> {
            if(d.getText().equals(ans)){
                //正确
                correct = true;
                d.setBackground(c_green);
            }else{
                //错误
                d.setBackground(w_red);
                if(b.getText().equals(ans)) {
                    b.setBackground(c_green);
                }else if(c.getText().equals(ans)) {
                    c.setBackground(c_green);
                }else{
                    a.setBackground(c_green);
                }
                try {
                    wrong(word.getText(), wordSound.getText(), meaning.getText(), a.getText(), b.getText(), c.getText(), d.getText(), ans);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            meaning.setVisible(true);
        });

        //wrong的话就加入wrong.txt
        //banana,[bə'nɑːnə],n.香蕉,香蕉,橘子,西瓜,苹果,香蕉
//        if(!correct){
//            wrong(word.getText(), wordSound.getText(), meaning.getText(), a.getText(), b.getText(), c.getText(), d.getText(), ans);
//        }
        a.setBackground(Color.white);
        b.setBackground(Color.white);
        c.setBackground(Color.white);
        d.setBackground(Color.white);
        mainPanel.add(a);
        mainPanel.add(b);
        mainPanel.add(c);
        mainPanel.add(d);

        //打卡panel 用于记录打卡情况
//        recordPanel = new JPanel();
        recordPanel = new RoundImagePanel(new ImageIcon("pinkback.jpg").getImage());
        recordPanel.setBounds(main_x, main_y, main_width, main_height);
        recordPanel.setBackground(pinkbackground);
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
        calendarPanel.setBounds(main_x-35, main_y-35, main_width*7/8, main_height/2+50);
        calendarPanel.setBackground(pinkbackground);
        recordPanel.add(calendarPanel);


        //打卡成功 label
        success = new JLabel("打卡成功");
        success.setBounds(main_x+250, main_y+300, 150, 100);
        success.setFont(new Font("幼圆", Font.BOLD, 30));
        success.setForeground(wine);
        success.setVisible(false);
//        recordPanel.add(success);

        //再学一次 button
        learnagain = new IconButton(new ImageIcon("again.png"));
        learnagain.setBounds(main_width-30, main_height+20, 50, 50);
        learnagain.setVisible(false);
        learnagain.addActionListener(e -> {
            //跳到新重新开始
            recordPanel.setVisible(false);
            success.setVisible(false);
            learnagain.setVisible(false);
//            selectBookPanel.setVisible(true);
            // 重新启动界面
//            Reciteword reciteword0 = new Reciteword();
//            try {
//                reciteword0.launchPanel();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
            // 将当前窗口不可见，并使新创建的reciteword可见
            try {
                Frame.reciteword.launchPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Frame.reciteword.setVisible(true);
//            reciteword0.setVisible(true);
        });
        this.add(learnagain);
        this.add(success);
        this.add(recordPanel);

        //下一个  button 不在mainPanel中
        next = new IconButton(new ImageIcon("next.png"));
        next.setBounds(main_width, main_height+20, 50, 50);
        next.setVisible(false);

        //arr中存储今天要学的单词

        //TODO: 从词书txt中获取今天要学的单词
        pointer = 1;
        next.addActionListener(e -> {
            if(pointer <= today.size()-1){
                //下一个单词
                a.setBackground(Color.white);
                b.setBackground(Color.white);
                c.setBackground(Color.white);
                d.setBackground(Color.white);
                //下一个单词的信息
                word.setText(today.get(pointer)[0]);
                wordSound.setText(today.get(pointer)[1]);
                meaning.setText(today.get(pointer)[2]);
                a.setText(today.get(pointer)[3]);
                b.setText(today.get(pointer)[4]);
                c.setText(today.get(pointer)[5]);
                d.setText(today.get(pointer)[6]);
                ans = today.get(pointer)[7];
                meaning.setVisible(false);
//                hint.setBackground(Color.white);
//                collect.setBackground(Color.white);
                hint.setIcon(new ImageIcon("hint.png"));
//                collect.setIcon(new ImageIcon("star.png"));
                try {
                    if(checkstar(word.getText())){ //在收藏中
                        collect.setIcon(new ImageIcon("star_light.png"));
//                        collect.setBackground(Color.yellow);
                        System.out.println("y");
                    }else{//不在收藏中
                        collect.setIcon(new ImageIcon("star.png"));
//                        collect.setBackground(Color.white);
                        System.out.println("n");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                pointer++;
            }else{
                //结束
                //返回主页
                mainPanel.setVisible(false);
                recordPanel.setVisible(true);
                next.setVisible(false);
                success.setVisible(true);
                learnagain.setVisible(true);
                //record(int id, int newid, int wrongid)
                try {
                    //当前选择的词书的名字：selectedbook
                    record(selectedbook,startid+20);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
