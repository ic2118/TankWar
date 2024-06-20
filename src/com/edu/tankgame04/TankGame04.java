package com.edu.tankgame04;


import javax.swing.*;

/**
 * @author 苏
 * @version 1.0
 */
public class TankGame04 extends JFrame {
    private MyPanel mp = null;


    public static void main(String[] args) {

        TankGame04 tankGame02 = new TankGame04();
        int activeThreadCount = Thread.activeCount();
        System.out.println("当前活动线程数: " + activeThreadCount);

    }

    public TankGame04(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
