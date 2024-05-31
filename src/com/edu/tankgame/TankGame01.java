package com.edu.tankgame;

import javax.swing.*;

/**
 * @author 苏
 * @version 1.0
 */
public class TankGame01 extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {

        TankGame01 tankGame01 = new TankGame01();

    }

    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
