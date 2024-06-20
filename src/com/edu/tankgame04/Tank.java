package com.edu.tankgame04;

/**
 * @author 苏
 * @version 1.0
 */
public class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed = 5;
    boolean isLive = true; //坦克是否存活

    //上右下左方法
    public void moveUp() {
        if (y == 0) {

        } else {
            y -= this.speed;
        }

    }

    public void moveRight() {

        if (x == 925) {

        } else {
            x += this.speed;
        }
//        System.out.println("afsfdsa的x" + x);
    }


    public void moveDown() {

        if(y == 645){

        }else {
            y += this.speed;
        }
//        System.out.println("afsfdsa的y" + y);

    }

    public void moveLeft() {
        if (x == 0) {
        } else {
            x -= this.speed;
        }


//        System.out.println("afsfdsa的x" + x);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
