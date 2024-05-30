package UIDesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImportFileGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Import File Example");
        JButton importButton = new JButton("Import File");

        // 创建文件选择器
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Import File");

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // 这里可以添加代码处理文件，例如读取文件内容等
                    System.out.println("File to import: " + file.getAbsolutePath());
                }
            }
        });

        frame.getContentPane().add(importButton);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}