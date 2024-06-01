package UIDesign;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(Image image) {
        backgroundImage = image;
        setOpaque(false); // 设置不透明，让底层的图片显示出来
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // 绘制背景图片，可以设置平铺方式等
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
class IconButton extends JButton {
    public IconButton(ImageIcon icon) {
        // 设置图标
        setIcon(icon);
        // 设置按钮不显示边框
        setBorderPainted(false);
        // 设置按钮内容区域不填充
        setContentAreaFilled(false);
        // 设置按钮背景透明
        setOpaque(false);
        // 设置按钮焦点不可见
        setFocusPainted(false);
    }
}
