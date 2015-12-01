package com.example.rob.finalprojectmobile;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by 100356161 on 11/2/2015.
 */
public class MainThread extends Thread {
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder holder, GamePanel gp){
        super();
        this.surfaceHolder = holder;
        this.gamePanel = gp;
    }

    @Override
    public void run(){
        //Time in ms
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS; //time per frame
        while(running){
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){
                //Log.d("Exception: ", e.toString());
            }finally {
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime()-startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try{
                this.sleep(waitTime);
            }catch (Exception e){
                //Log.d("Exception: ", e.toString());
            }
            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                totalTime = 0;
                frameCount = 0;
                //System.out.println(averageFPS);
            }
        }
    }

    public void setRunning(boolean b){running = b;}
}
