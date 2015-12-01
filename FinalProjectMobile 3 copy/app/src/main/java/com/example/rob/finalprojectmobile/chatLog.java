package com.example.rob.finalprojectmobile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class chatLog implements java.io.Serializable{

	String[] chat = new String[20];
	int currentLine = 0;
	int x;
	int y;
	int screenWidth;
	int screenHeight;
	transient Rect r;
	transient Paint p1,p2;

	public chatLog(int sw, int sh){
		screenWidth = sw;
		screenHeight = sh;
		x = 0;
		y = (int)(screenHeight);
		r = new Rect(x,y,screenWidth,(int)(screenHeight*(1.0/2.0)));
		p1 = new Paint();
		p1.setColor(Color.DKGRAY);
		p2 = new Paint();
		p2.setColor(Color.GREEN);
		p2.setTextSize(42f);
		chat[0] = "Welcome to the Demo";
	}

	public void resetRect(){
		r = new Rect(x,y,screenWidth,(int)(screenHeight*(1.0/2.0)));
	}

	public void resetPaint(){
		p1 = new Paint();
		p1.setColor(Color.DKGRAY);
		p2 = new Paint();
		p2.setColor(Color.GREEN);
		p2.setTextSize(42f);
	}

	//adds a new entry into the chat, once the last possible entry is reached
	//it places the new line into the first index and goes on from there
	public void newLine(String line, boolean byLine){
		if(currentLine > 19){
			currentLine = 0;
		}
		for(int i = chat.length-1; i > 0;i--) {
			chat[i] = chat[i-1];
		}
		chat[0] = line;
		currentLine++;
		/*if(byLine)
			displayByLine();
		else
			displayByChar();*/
	}
	
	//clears chat
	public void clear(){
		chat = new String[20];
	}
	
	//returns the current chat array
	public String[] getChat(){
		return chat;
	}
	
	//Displays the current line of text in the chat one character at a time
	public void displayByChar(){
		for(int i = 1; i < chat[currentLine].length(); i++)
		{
			System.out.print(chat[currentLine].substring(i-1, i));
			waitTime(100);
		}
		waitTime(500);
		System.out.println("");
	}
	
	//displays the current line of text in the chat
	public void displayByLine(){
		System.out.print(chat[currentLine]);
		waitTime(500);
		System.out.println("");
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
		canvas.drawRect(r, p1);
		for(int i = 0; i < chat.length; i++) {
			if(chat[i] != null) {
				canvas.drawText(chat[i], x + 15, (y - 25) - 46 * i, p2);
			}
		}
		/*canvas.drawBitmap(image,x,y,null);
		if(x<0){
			canvas.drawBitmap(image,x+ GamePanel.WIDTH, y, null);
		}*/
	}
}
