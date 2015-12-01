package com.example.rob.finalprojectmobile;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class sideMenu {

    int x;
    int y;
    int sideLength;
    int screenWidth;
    int screenHeight;
    Rect rSave,rLoad,rHero,rFight,rComp;
    Paint pSave,pLoad,pHero,pFight,pComp;
    Bitmap iSave, iLoad, iHero, iFight, iComp;

    public sideMenu(int sw, int sh, Bitmap s, Bitmap l, Bitmap h, Bitmap f, Bitmap c) {
        screenWidth = sw;
        screenHeight = sh;
        x = 0;
        y = 0;
        //r = new Rect(x,y,(int)(screenWidth*(1.0/8.0)),(int)(screenHeight*(1.0/2.0)));
        rSave = new Rect(x, y, 175, 175);
        pSave = new Paint();
        pSave.setColor(Color.CYAN);
        rLoad = new Rect(x, y + 175, 175, 350);
        pLoad = new Paint();
        pLoad.setColor(Color.GREEN);
        rHero = new Rect(x, y + 350, 175, 520);
        pHero = new Paint();
        pHero.setColor(Color.BLUE);
        rFight = new Rect(x, y + 520, 175, 695);
        pFight = new Paint();
        pFight.setColor(Color.MAGENTA);
        rComp = new Rect(x, y + 695, 175, 870);
        pComp = new Paint();
        pComp.setColor(Color.RED);
        iSave = s;
        iLoad = l;
        iHero = h;
        iFight = f;
        iComp = c;
    }

    //pause
    public void waitTime(int time){
        try {
            Thread.sleep(time);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void update(){
    }

    public void draw(Canvas canvas){
        canvas.drawRect(rSave,pSave);
        canvas.drawBitmap(iSave, x + 12, y + 12, pSave);
        canvas.drawRect(rLoad, pLoad);
        canvas.drawBitmap(iLoad, x + 12, y + 187, pLoad);
        canvas.drawRect(rHero, pHero);
        canvas.drawBitmap(iHero, x + 12, y + 362, pHero);
        canvas.drawRect(rFight, pFight);
        canvas.drawBitmap(iFight, x + 12, y + 537, pFight);
        canvas.drawRect(rComp, pComp);
        canvas.drawBitmap(iComp, x + 12, y + 712, pComp);

        //canvas.drawRect(r2,p2);

        //canvas.drawRect(r2,p2);
		/*canvas.drawBitmap(image,x,y,null);
		if(x<0){
			canvas.drawBitmap(image,x+ GamePanel.WIDTH, y, null);
		}*/
    }

    public boolean isSave(int x, int y){return rSave.contains(x,y);}
    public boolean isLoad(int x, int y){return rLoad.contains(x,y);}
    public boolean isHero(int x, int y){return rHero.contains(x,y);}
    public boolean isFight(int x, int y){return rFight.contains(x,y);}
    public boolean isComp(int x, int y){return rComp.contains(x,y);}

}
