package com.edu.tankgame04;

import java.util.Random;
import java.util.Vector;

/**
 * @author 苏
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
//    private int x;


    //    private int y;
//    private int direct = 2;
//    int speed = 2;

    boolean isLive = true;
    int step;

    private Random random;

    // 敌人子弹
    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
        // 使用当前时间的纳秒值作为种子，确保每个坦克的随机数生成器不同
        this.random = new Random(System.nanoTime());
    }


    @Override
    public void run() {
        // 引入随机延迟，防止线程同时启动
        try {
            Thread.sleep(random.nextInt(500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            // 根据坦克方向来继续移动
//            step = (int) (Math.random() * (20 + 1)) + 30;

            // 这里我们判断如果shots size() = 0 创建一颗子弹，放入集合并启动
            if (shots.isEmpty() && isLive) {
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }

                shots.add(s);
                //启动
                new Thread(s).start();

            }

            step = random.nextInt(21) + 30; // 生成[30, 50]范围内的随机步数
            switch (getDirect()) {
                case 0: // 向上
                    for (int i = 0; i < step; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 1: // 右
                    for (int i = 0; i < step; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 2: // 下
                    for (int i = 0; i < step; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                case 3: // 左
                    for (int i = 0; i < step; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

            }

            //休眠50ms
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            // 然后随机改变坦克方向
            setDirect((int) (Math.random() * 4));
            // 被击中结束这个线程
            if (!isLive) {
                break;
            }

        }
    }


}
