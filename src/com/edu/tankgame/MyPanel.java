package com.edu.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * @author 苏
 * @version 1.0
 * 坦克大战绘图区域
 */
public class MyPanel extends JPanel {

    // 定义我的坦克
    Hero hero = null;
    public MyPanel(){
        hero = new Hero(100,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);

        // 画出坦克-封装方法
        drawTank(hero.getX(), hero.getY(),g,0,0);


    }

    // 编写方法画出坦克

    /**
     * @param x         坦克左上角的x坐标
     * @param y         坦克左上角y坐标
     * @param g         画笔
     * @param direct 坦克方向
     * @param type      什么类型的坦克
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
//            根据不能类型的坦克，绘制不同颜色的坦克
            case 0: // 主角坦克
                g.setColor(Color.cyan);
            case 1: // 敌人坦克
        }

        // 绘制坦克根据方向
        switch(direct){
            case 0:
                g.fill3DRect(x,y,10,60,false); //画出坦克左边轮子
                g.fill3DRect(x+30,y,10,60,false); //画出坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false); //画出坦克的主体
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y); //画出炮筒

                break;
            default:
        }

    }


}
