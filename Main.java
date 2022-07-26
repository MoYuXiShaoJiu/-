package com.example.aircraftwar.JavaCode.edu.hitsz.application;

import android.graphics.Bitmap;

import panel.AddRecord;
import panel.ScoreTable;
import panel.aircraftWar;



//import javax.swing.*;
//import java.awt.*;


/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static Bitmap BG = null;
    public static final Object MAIN_LOCK = new Object();
    public static boolean soundSwitch = false;
    public static boolean ifRecord = false;
    public static String Username = null;
    public static int FinalScore=0;
    public static int gameParameter = 1;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("aircraftWar");
        //    frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        aircraftWar AW = new aircraftWar();
        JPanel modeChoice = AW.getPanel();
        frame.setContentPane(modeChoice);
        frame.setVisible(true);

        synchronized (MAIN_LOCK){
            while(modeChoice.isVisible()){
                try{
                    MAIN_LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        frame.remove(modeChoice);

        System.out.println("音量是否打开："+soundSwitch);
        MusicManager.Enable(true);
        MusicManager.bgmBegin();
        Game game;
        if(gameParameter==1){
            game = new easyGame();
            System.out.println("简单模式");
        }
        else if(gameParameter==2){
            game = new normalGame();
            System.out.println("普通模式");
        }
        else {
            game = new hardGame();
            System.out.println("困难模式");
        }
        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();

        synchronized (MAIN_LOCK){
            while(!game.gameOverFlag){
                try{
                    MAIN_LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        frame.remove(game);
        MusicManager.gameOver();
        MusicManager.Enable(false);

        JPanel isRecord = new AddRecord().mainPanel;
        frame.setContentPane(isRecord);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        synchronized (MAIN_LOCK){
            while(isRecord.isVisible()){
                try{
                    MAIN_LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        frame.setContentPane(new ScoreTable().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }



}
