package UIDesign;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static UIDesign.Util.*;

public class Frame extends JFrame {
    public static HomePage homePage;
    public static SettingPage settingPage;

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
        /**/
        settingPage = new SettingPage();
        settingPage.launchPanel();
        this.add(settingPage);

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
        graph.addActionListener(e -> Frame.homePage.setVisible(false));
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
        star.addActionListener(e -> Frame.settingPage.setVisible(false));
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
        this.setVisible(true);
    }
}