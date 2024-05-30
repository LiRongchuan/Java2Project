package UIDesign;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("单词管理界面");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        // 创建WordManagerPanel实例并添加到JFrame
        WordManagerPanel wordManagerPanel = new WordManagerPanel();
        wordManagerPanel.setBounds(0, 0, 400, 400);
        add(wordManagerPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
