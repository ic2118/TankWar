package com.edu.tankgame05;

import java.util.Random;
import java.util.Vector;

/**
 * @author 苏
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {


    boolean isLive = true;
    // 随机行动步数
    int step;

    private Random random;

    // 敌人子弹
    Vector<Shot> shots = new Vector<>();

    // 增加成员，EnemyTank可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();


    /*
    提供一个方法，将mypanel的成员Vector<EnemyTank> enemyTanks = new Vector<>();
    设置到EnemyTank的一个属性或成员
     */
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
        // 使用当前时间的纳秒值作为种子，确保每个坦克的随机数生成器不同
        this.random = new Random(System.nanoTime());
    }



    // 编写方法，判断当前的敌人坦克是否和 enemyTanks 中的其它坦克发生了重叠或者碰撞
    private boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            // 0 1 2 3 对应 上 右 下 左

            // 从Vector中取出其它坦克做对比看是否重叠
            case 0:


                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 排除自己
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //分情况 上/下
                        /*
                        敌人坦克 上/下 x的范围是 [enemyTank.getX(), enemyTank.getX() + 40]
                        y 的范围是 [enemyTank.getY(), enemyTank.getY() + 60]
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 当前坦克，左上角的坐标[this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            // 当前坦克右上角的坐标[this.getX() + 40,this.getY()]

                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }


                        }

                        // 如果敌人坦克是左/右 x[enemyTank.getX(), enemyTank.getX() + 60]
//                                            y[enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            //当前坦克 右上角的坐标 [this.getX()+60, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }


                    }

                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 排除自己
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //分情况 上/下
                        /*
                        敌人坦克 上/下 x的范围是 [enemyTank.getX(), enemyTank.getX() + 40]
                        y 的范围是 [enemyTank.getY(), enemyTank.getY() + 60]
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 当前坦克，右上角的坐标[this.getX()+60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            // 当前坦克右下角的坐标[this.getX() + 60,this.getY() + 40]

                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }


                        }

                        // 如果敌人坦克是左/右 x[enemyTank.getX(), enemyTank.getX() + 60]
//                                            y[enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 当前坦克 左下角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            //当前坦克 右下角的坐标 [this.getX()+60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }


                    }

                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 排除自己
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //分情况 上/下
                        /*
                        敌人坦克 上/下 x的范围是 [enemyTank.getX(), enemyTank.getX() + 40]
                        y 的范围是 [enemyTank.getY(), enemyTank.getY() + 60]
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 当前坦克，左下角的坐标[this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                            // 当前坦克右下角的坐标[this.getX() + 40,this.getY() + 60]

                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }


                        }

                        // 如果敌人坦克是左/右 x[enemyTank.getX(), enemyTank.getX() + 60]
//                                            y[enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }

                            //当前坦克 右下角的坐标 [this.getX()+40, this.getY()+60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }


                    }

                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 排除自己
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //分情况 上/下
                        /*
                        敌人坦克 上/下 x的范围是 [enemyTank.getX(), enemyTank.getX() + 40]
                        y 的范围是 [enemyTank.getY(), enemyTank.getY() + 60]
                         */
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 当前坦克，左上角的坐标[this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            // 当前坦克左下角的坐标[this.getX(),this.getY() + 40]

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }


                        }

                        // 如果敌人坦克是左/右 x[enemyTank.getX(), enemyTank.getX() + 60]
//                                            y[enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            //当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }


                    }

                }
                break;
        }
        return false;
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

            // 生成[30, 50]范围内的随机步数

            step = random.nextInt(21) + 30;
            switch (getDirect()) {
                // 向上
                case 0:
                    for (int i = 0; i < step; i++) {
                        if (getY() > 0 && !isTouchEnemyTank()) {
                            moveUp();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
// 右
                case 1:
                    for (int i = 0; i < step; i++) {
                        if (getX() + 60 < 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                // 下
                case 2:
                    for (int i = 0; i < step; i++) {
                        if (getY() + 60 < 750 && !isTouchEnemyTank()) {
                            moveDown();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;

                // 左
                case 3:
                    for (int i = 0; i < step; i++) {
                        if (getX() > 0 && !isTouchEnemyTank())  {
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
