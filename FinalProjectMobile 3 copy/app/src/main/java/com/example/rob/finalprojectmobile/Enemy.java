package com.example.rob.finalprojectmobile;

import java.lang.*;
import java.util.*;
public class Enemy extends Character {
	Enemy(){}
	Enemy(String n, String r, String c, int lv, int i, int v, int w, int s, int l, int e){
		long id;
		int cN;
		Random rand = new Random();
		if(i > v && i > w && i > s && i > l && i > e)
			n = "Intelligent ";
		else if(v > i && v > w && v > s && v > l && v > e)
			n = "Defensive ";
		else if(w > v && w > i && w > s && w > l && w > e)
			n = "Mystical ";
		else if(s > v && s > w && s > i && s > l && s > e)
			n = "Brutal ";
		else if(l > v && l > w && l > s && l > i && l > e)
			n = "Lucky ";
		else if(e > v && e > w && e > s && e > i && e > l)
			n = "Quick ";
		else
			n = "Well-Rounded ";
		cN = rand.nextInt(100)+1;
		//for(int count = 0; count<loadedEnemy.length; count++)
		//	System.out.println(loadedEnemy[count]);
		NAME = n;
		RACE = r;
		CLASS = c;
		LVL = lv;
		INT = i;
		VIT = v;
		WIL = w;
		STR = s;
		LUK = l;
		END = e;
		CHARNUM = cN;
		health();
		mana();
		baseDMG();
		currHP = HP;
		currMP = MP;
	}
	Enemy(int lv, String[] enemyType){
		String n, cID;
		int i,v,w,s,l,e,cN;
		Random rand = new Random();
		i = rand.nextInt(20)+lv*5;
		v = rand.nextInt(20)+lv*5;
		w = rand.nextInt(20)+lv*5;
		s = rand.nextInt(20)+lv*5;
		l = rand.nextInt(20)+lv*5;
		e = rand.nextInt(20)+lv*5;
		if(i > v && i > w && i > s && i > l && i > e)
			n = "Intelligent ";	
		else if(v > i && v > w && v > s && v > l && v > e)
			n = "Defensive ";
		else if(w > v && w > i && w > s && w > l && w > e)
			n = "Mystical ";
		else if(s > v && s > w && s > i && s > l && s > e)
			n = "Brutal ";
		else if(l > v && l > w && l > s && l > i && l > e)
			n = "Lucky ";
		else if(e > v && e > w && e > s && e > i && e > l)
			n = "Quick ";
		else
			n = "Well-Rounded ";
		cN = rand.nextInt(100)+1;
		
		int randIndex = rand.nextInt(enemyType.length-1)+1;
		String[] loadedEnemy = enemyType[randIndex].split("\\|");
		RACE = loadedEnemy[0];
		//for(int count = 0; count<loadedEnemy.length; count++)
		//	System.out.println(loadedEnemy[count]);
		n += loadedEnemy[0];
		cID = loadedEnemy[1];
		i += Integer.parseInt(loadedEnemy[2]);
		v += Integer.parseInt(loadedEnemy[3]);
		w += Integer.parseInt(loadedEnemy[4]);
		s += Integer.parseInt(loadedEnemy[5]);
		l += Integer.parseInt(loadedEnemy[6]);
		e += Integer.parseInt(loadedEnemy[7]);
		NAME = n;
		CLASS = cID;
		LVL = lv;
		INT = i;
		VIT = v;
		WIL = w;
		STR = s;
		LUK = l;
		END = e;
		CHARNUM = cN;
		health();
		mana();
		baseDMG();
		currHP = HP;
		currMP = MP;
	}

}