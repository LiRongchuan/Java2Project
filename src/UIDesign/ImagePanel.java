package UIDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicButtonUI;

import static java.awt.Color.pink;

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

class RoundImagePanel extends JPanel {
    private Image backgroundImage;

    public RoundImagePanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setOpaque(false); // 设置面板不透明
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 设置抗锯齿，使绘制更平滑
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 创建圆角矩形路径
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50);
        g2d.setClip(roundRect); // 设置绘制区域为圆角矩形

        // 绘制背景图片，将根据设置的clip区域显示圆角效果
        g2d.drawImage(backgroundImage, 0, 0, this);

        // 如果需要，绘制圆角边框
        g2d.setColor(pink);
        g2d.draw(roundRect);
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


class RoundedButton extends JButton {
    private Color borderColor = Color.PINK; // 定义边框颜色

    public RoundedButton() {
        setContentAreaFilled(false);
        setBorder(BorderFactory.createLineBorder(borderColor, 1));
        setBackground(Color.WHITE);
//        setOpaque(true); // 设置按钮不透明，以便能看到背景

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arcDiameter = 50; // 圆角的直径
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);

        // 绘制文本
        if (getText() != null && !getText().isEmpty()) {
            g2d.setColor(getForeground());
            g2d.setFont(getFont());
            FontMetrics metrics = g2d.getFontMetrics();
            String text = getText();
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();
            int x = (getWidth() - textWidth) / 2;
            int y = ((getHeight() - textHeight) / 2) + metrics.getAscent();
            g2d.drawString(text, x, y);
        }

        g2d.dispose();
    }
}


