package com.edu.tankgame03;


import javax.swing.*;

/**
 * @author 苏
 * @version 1.0
 */
public class TankGame03 extends JFrame {
    private MyPanel mp = null;


    public static void main(String[] args) {

        TankGame03 tankGame02 = new TankGame03();

    }

    public TankGame03(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
