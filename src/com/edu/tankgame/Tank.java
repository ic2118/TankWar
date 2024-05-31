package com.edu.tankgame;

/**
 * @author 苏
 * @version 1.0
 */
public class Tank {
    private int x;
    private int y;

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
