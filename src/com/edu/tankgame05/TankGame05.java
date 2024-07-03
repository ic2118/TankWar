package com.edu.tankgame05;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 苏
 * @version 1.0
 */
public class TankGame05 extends JFrame {
    private MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        // 查看启动了多少线程
//        int activeThreadCount = Thread.activeCount();
//        System.out.println("当前活动线程数: " + activeThreadCount);



        TankGame05 tankGame02 = new TankGame05();


    }

    public TankGame05(){

        // 选择新游戏还是继续上局
        System.out.println("请输入选择 1：新游戏 2：继续上局游戏");
        String key = scanner.next();

        mp = new MyPanel(key);
        new Thread(mp).start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1300,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // 在JFrame中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                System.out.println("监听到关闭窗口");
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
}
