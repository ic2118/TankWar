package com.edu.tankgame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 苏
 * @version 1.0
 * 坦克大战绘图区域
 */

// 为了实现键盘监听事件，实现KeyListener
public class MyPanel extends JPanel implements KeyListener {

    // 定义我的坦克
    Hero hero = null;

    Vector<EnemyTank> enemyTanks = new Vector<>();

    int enemyTankSize = 3;

    public MyPanel() {
        hero = new Hero(100, 100);


        // 初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
//            enemyTanks.add(new EnemyTank((100 * (i+1)),0));
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTanks.add(enemyTank);
            System.out.println("Enemy tank " + i + " position: (" + enemyTank.getX() + ", " + enemyTank.getY() + ")");
            enemyTank.setDirect(2);
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);

        // 画出坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        // 画反派坦克，遍历vector
//        for (EnemyTank enemyTank : enemyTanks) {
//            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
//        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
        }


    }

    // 编写方法画出坦克

    /**
     * @param x      坦克左上角的x坐标
     * @param y      坦克左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向
     * @param type   什么类型的坦克
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
//            根据不能类型的坦克，绘制不同颜色的坦克
            case 0: // 敌人坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 玩家坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克方向来绘制对应形状坦克


        // 绘制坦克根据方向 direct 表示方向（0，1，2，3 == 上右下左）
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false); //画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false); //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //画出坦克的主体
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y); //画出炮筒

                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false); //画出坦克左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //画出坦克的主体
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false); //画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false); //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //画出坦克的主体
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;

            case 3:
                g.fill3DRect(x, y, 60, 10, false); //画出坦克左边轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //画出坦克的主体
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            // 改变坦克方向
            hero.setDirect(0);
            hero.moveUp();


        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();

        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }

        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
