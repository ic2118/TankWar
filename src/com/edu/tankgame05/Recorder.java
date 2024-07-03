package com.edu.tankgame05;

import java.io.*;
import java.util.Vector;

/**
 * @author 苏
 * @version 1.0
 * 该类用于记录相关信息 会和文件进行交互
 */
public class Recorder {
    // 定义变量记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;
    // 定义IO对象
//    private static FileWriter fw = null;
//    private static BufferedWriter bw = null;
//    private static BufferedReader br = null;
    // 定义文件路径
    private static String recordFile = "src\\myRecord.txt";
    // 定义vector，指向 MyPanel 对象的 敌人坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;

    // 定义一个Node的Vector，用于保存敌人的信息Node
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    // 增加一个方法，用于读取recordFile，恢复相关信息
    // 在确认继续上局游戏的时候开始调用
    public static Vector<Node> getNodesAndEnemyTankRec() {

        File file = new File(recordFile);
        if(!file.exists() || file.length() == 0){
            return new Vector<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(recordFile))) {
            String firstLine = br.readLine();
            if (firstLine != null && !firstLine.isEmpty()) {
                allEnemyTankNum = Integer.parseInt(firstLine);
            }


            // 循环读取文件，生成nodes集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                // 放入nodes Vector
                nodes.add(node);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return nodes;
    }


    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    // 当我方坦克击毁敌人坦克就应当对 allEnemyTankNum 进行 +1
    public static void addallEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }

    // 增加一个方法当游戏退出时将allEnemyTank的值保存到recordFile中
    // 对方法进行升级，保存敌人坦克的坐标和方向
    public static void keepRecord() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(recordFile))) {
//            bw.write("allEnemyTankNum = "+allEnemyTankNum);
            bw.write(String.valueOf(allEnemyTankNum));
            bw.newLine();
            // 遍历敌人坦克的vector，然后根据情况保存即可。
            // OOP, 定义一个属性，然后通过setXXX得到 敌人坦克的Vector

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                // 建议判断坦克是否还存活
                if (enemyTank.isLive) {
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(record);
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ;
    }
}
