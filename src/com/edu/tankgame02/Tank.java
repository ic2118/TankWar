package com.edu.tankgame02;

/**
 * @author 苏
 * @version 1.0
 */
public class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed = 1;

    //上右下左方法
    public void moveUp(){
        y -= speed;
    }
    public void moveRight(){
        x += speed;
    }

    public void moveLift(){
        x -= speed;
    }
    public void moveDown(){
        y += speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveLeft(){
        x -= speed;
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
