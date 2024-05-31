package com.edu.draw;

import javax.swing.*;
import java.awt.*;

/**
 * @author 苏
 * &#064;verison1.0
 */
public class DrawCircle extends JFrame{
    private MyPanel mp = null;

    public static void main(String[] args) {

        new DrawCircle();

    }

    public DrawCircle(){
        mp = new MyPanel();
        this.add(mp);
        // 设置窗口大小
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);

//        g.drawOval(10,10,100,100);

//        1. 画直线 `drawLine(int x1,int y1,int x2,int y2)`

//        g.drawLine(10,10,100,100);
//        2. 画矩形边框 `drawRect(int x, int y, int width, int height)`
//        g.drawRect(10,10,50,50);
//        3. 画椭圆 `drawOval(int x, int y, int width, int height)`
//        g.drawOval(10,10,40,30);
//        4. 填充矩形 `fillRect(int x, int y, int width, int height)`
//        g.setColor(Color.red);
//        g.fillRect(10,10,100,100);
//        5. 填充椭圆 `fillOval(int x, int y, int width, int height)`
//        g.setColor(Color.green);
//        g.fillOval(10,10,100,80);
//        6. 画图片 `drawImage(Image img, int x, int y, ..)`
//            Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/云朵.jpg"));
//            g.drawImage(image,10,10,200,300,this);

//        7. 画字符串 `drawString(String str, int x, int y)`
//        g.setColor(Color.red);
//        g.setFont(new Font("隶书",Font.BOLD,50));
//        g.drawString("你好，世界！！！",300,100);
//        8. 设置画笔的字体 `setFont(Font font)`
//        9. 设置画笔的颜色 `setColor(Color c)`

        g.setColor(Color.blue);
        g.fillRect(20,20,5,30);
        g.fillRect(40,20,5,30);
        g.fillRect(25,25,20,20);
        g.setColor(Color.yellow);
        g.fillOval(27,27,10,10);
        g.setColor(Color.blue);
        g.drawLine(32,10,32,30);
    }
}