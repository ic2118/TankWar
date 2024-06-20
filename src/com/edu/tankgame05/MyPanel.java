package com.edu.tankgame05;

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
// 为了让panel不停的重绘子弹，需要将 MyPanel 实现Runable，当作一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    @Override
    public void run() { // 每隔100ms，重绘区域
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 判断是否击中敌人坦克
//            if (hero.shot != null && hero.shot.isLive) {
//
//                //遍历敌人所有的坦克
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot, enemyTank);
//                }
//            }

            hitTank();
            hitHero();

            this.repaint();
        }

    }

    // 定义我的坦克
    Hero hero = null;
    // 定义敌人坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个Vector，用于存放炸弹
    //当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    // 定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    int enemyTankSize = 3;

    public MyPanel() {
        hero = new Hero(100, 100);


        // 初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
//            enemyTanks.add(new EnemyTank((100 * (i+1)),0));
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTanks.add(enemyTank);
//            System.out.println("Enemy tank " + i + " position: (" + enemyTank.getX() + ", " + enemyTank.getY() + ")");
            enemyTank.setDirect(2);
            // 启动敌人坦克自由移动
            new Thread(enemyTank).start();
            // 给敌人坦克加入子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
//            Shot shot1 = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 30, enemyTank.getDirect());
            // 启动shot对象
            new Thread(shot).start();
//            new Thread(shot1).start();
            // 加入
            enemyTank.shots.add(shot);
//            enemyTank.shots.add(shot1);
            //

        }
        // 初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);

        // 画出坦克-封装方法
        if(hero != null && hero.isLive){
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }else{
            hero = new Hero(100, 100);
            hero.isLive = false;
        }


        // 画出hero子弹
//        if (hero.shot != null && hero.shot.isLive) {
//            g.fill3DRect(hero.shot.getX(), hero.shot.getY(), 5, 5, false);
//        }
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (hero.shot != null && hero.shot.isLive) {
                g.fill3DRect(shot.getX(), shot.getY(), 5, 5, false);
            } else {
                hero.shots.remove(shot);
            }
        }
        // 使用迭代器写一个巩固知识
//        Iterator<Shot> iterator = hero.shots.iterator();
//        while (iterator.hasNext()){
//            Shot shot = iterator.next();
//            if (shot != null && shot.isLive) {
//                g.fill3DRect(shot.getX(), shot.getY(), 5, 5, false);
//            } else {
//                iterator.remove();
//            }
//        }
        // 如果bombs中有炸弹
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前对象的life值画出对象
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }

            // 让这个炸弹的生命值减少
            bomb.lifeDown();
            // 如果life为0,就从bombs集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        // 画反派坦克，遍历vector
//


        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            // 判断当前坦克是否还存在,不存在不画出  // 更改为在下面子弹集中的时候就移出
//            if (!enemyTank.isLive) {
//                enemyTanks.remove(enemyTank);
//            }
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);


//            画出所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    // 取出子弹准备绘制
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.fill3DRect(shot.getX(), shot.getY(), 5, 5, false);
                    } else {
                        // 从Vector移除子弹，
                        enemyTank.shots.remove(shot);
                    }
                }
            }


        }
//        for (EnemyTank enemyTank : enemyTanks) {
//            // 判断当前坦克是否还存在,不存在不画出
////            if(!enemyTank.isLive){
////                enemyTanks.remove(enemyTank);
////            }
//            if (enemyTank.isLive) {
//                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
//
//
////            画出所有子弹
//                for (int j = 0; j < enemyTank.shots.size(); j++) {
//                    // 取出子弹准备绘制
//                    Shot shot = enemyTank.shots.get(j);
//                    if (shot.isLive) {
//                        g.fill3DRect(shot.getX(), shot.getY(), 5, 5, false);
//                    } else {
//                        // 从Vector移除子弹，
//                        enemyTank.shots.remove(shot);
//                    }
//                }
//            }
//        }


//        for (int i = 0; i < enemyTanks.size(); i++) {
//            EnemyTank enemyTank = enemyTanks.get(i);
//            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
//        }


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
                //画出坦克左边轮子
                g.fill3DRect(x, y, 10, 60, false);
                //画出坦克右边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克的主体
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                //画出炮筒
                g.drawLine(x + 20, y + 30, x + 20, y);

                break;
            case 1:
                //画出坦克左边轮子
                g.fill3DRect(x, y, 60, 10, false);
                //画出坦克右边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);
                //画出坦克的主体
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                //画出坦克左边轮子
                g.fill3DRect(x, y, 10, 60, false);
                //画出坦克右边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克的主体
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                //画出炮筒
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;

            case 3:
                //画出坦克左边轮子
                g.fill3DRect(x, y, 60, 10, false);
                //画出坦克右边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);
                //画出坦克的主体
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时无处理");
        }

    }

    //遍历所有子弹判断是否击中了敌人坦克
    public void hitTank() {
        // 遍历所有子弹
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);

            if (shot != null && shot.isLive) {
                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }


        }
        this.repaint();
    }

    // 编写方法判断敌人坦克是否击中英雄坦克
    public void hitHero(){
        // 遍历敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历enemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断 shot 是否击中我的坦克
                if(hero.isLive && shot.isLive){
                    hitTank(shot,hero);
                }

            }
        }
    }


    //    编写方法判断我方子弹是否集中敌人坦克
    public void hitTank(Shot s, Tank enemyTank) {
        //判断s 击中坦克
        switch (enemyTank.getDirect()) {
            case 0: //坦克向上
            case 2: // 坦克向下
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 40
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                    enemyTanks.remove(enemyTank);
                    //创建bomb对象，加入bombs集合中
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);

                }
                break;
            case 1: // 坦克向左
            case 3: // 坦克向右
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 60
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //创建Bomb对象，加入到bombs集合
                    //当我的子弹击中敌人坦克后，将enemyTank 从Vector 拿掉
                    enemyTanks.remove(enemyTank);
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;

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

        // 如果按下J，发射子弹
        // 如果用户按下J键，发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
//            if(hero.shot.isLive){
//
//            }else {
//                hero.shotEnemyTank();
//            }
            // 发射一颗子弹
//            if (hero.shot == null || !hero.shot.isLive) {
//                hero.shotEnemyTank();
//            }
            // 发射多颗子弹
            if(hero != null && hero.isLive){
                hero.shotEnemyTank();
            }



        }

        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
