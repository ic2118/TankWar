package com.edu.tankgame06;

import java.util.Vector;

/**
 * @author 苏
 * @version 1.0
 * 自己的坦克
 */

public class Hero extends Tank {
    public Hero(int x, int y) {
        super(x, y);
    }

    // 定义一个shot对象

    Vector<Shot> shots = new Vector<>();
    Shot shot = null;



    public void shotEnemyTank(){
        if(shots.size() == 5){
            return;
        }
        switch (getDirect()){
            case 0:
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1:
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot = new Shot(getX(),getY()+20,3);
                break;
        }
        // 把新创建的shot放入到集合中
        shots.add(shot);
        // 启动shot线程
        Thread thread = new Thread(shot);
        thread.start();
    }
}
