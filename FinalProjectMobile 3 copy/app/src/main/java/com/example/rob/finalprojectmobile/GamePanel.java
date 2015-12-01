package com.example.rob.finalprojectmobile;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * Created by 100356161 on 11/2/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Character player;
    public chatLog cLog;
    public sideMenu sMenu;
    SavingSystem<chatLog> savChat;

    public static final int MOVESPEED = -5;
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;

    final int rq =1101500;
    Context c;

    public GamePanel(Context context){
        super(context);
        c = context;
        //gCore = new demo2();
        //gCore.menu();
        //cLog.setChat(gCore.getchatLog());
        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        savChat = new SavingSystem<chatLog>(c.getApplicationContext());

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
        thread.setRunning(false);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        //bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        //player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter),65,25,3);
        cLog = savChat.mobileDeserial("CHAT", ".chat");
        if(cLog == null){
            cLog = new chatLog(getWidth(),getHeight());
        }else{
            cLog.resetRect();
            cLog.resetPaint();
        }
        sMenu = new sideMenu(getWidth(),getHeight(), BitmapFactory.decodeResource(getResources(),R.drawable.save),
                BitmapFactory.decodeResource(getResources(),R.drawable.load),
                BitmapFactory.decodeResource(getResources(),R.drawable.hero),
                BitmapFactory.decodeResource(getResources(),R.drawable.fight),
                BitmapFactory.decodeResource(getResources(),R.drawable.compare));
        //start game loop
        if (thread.getState()==Thread.State.TERMINATED) {
            thread = new MainThread(getHolder(),this);
        }
        thread.setRunning(true);
        thread.start();
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(sMenu.isSave((int)event.getRawX(),(int)event.getRawY())){
                System.out.println("Save");
            }else if(sMenu.isLoad((int) event.getRawX(), (int) event.getRawY())){
                System.out.println("Load");
            }else if(sMenu.isHero((int) event.getRawX(), (int) event.getRawY())){
                System.out.println("Hero");
            }else if(sMenu.isFight((int) event.getRawX(), (int) event.getRawY())){
                System.out.println("Fight");
            }else if(sMenu.isComp((int) event.getRawX(), (int) event.getRawY())){
                System.out.println("Comp");
            }
        }
        return super.onTouchEvent(event);
    }*/

    public void update(){
        //if(player.getPlaying()){
        //bg.update();
        //player.update();
        //}
    }

    @Override
    public void draw(Canvas canvas){
        //final float scaleFactorX = getWidth() / (WIDTH*1.f);
        //final float scaleFactorY = getHeight() / (HEIGHT*1.f);

        if(canvas!=null) {
            final int savedState = canvas.save();
            //canvas.scale(scaleFactorX, scaleFactorY);
            //bg.draw(canvas);
            //player.draw(canvas);
            sMenu.draw(canvas);
            cLog.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public void printToScreen(String line){
        cLog.newLine(line,true);
    }
    public void setCharacter (Character c){
        player = c;
    }
    public void setChat(chatLog c){cLog = c;}
    public void saveChat(){savChat.mobileSerial(cLog,"CHAT",".chat");}
    public void loadChat(){cLog = savChat.mobileDeserial("CHAT",".chat");}
}
