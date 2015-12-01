package com.example.rob.finalprojectmobile;

import java.lang.*;
import java.util.*;
import java.io.*;
public class DamageSystem{
	Character player;
	Enemy enemy;
	Random rand = new Random();
	String[] battlelog;
	int turn;
	//SavingSystem <Character> savSys;

	int choice = -1;
	String bLog=".";

	DamageSystem(Character p, Enemy e){

		player = p;
		p.reset();
		enemy = e;
		battlelog = new String[5];
		turn = 0;
	}
	public void setChoice(int i){choice = i;}
	public int physDamage(boolean attack){
		//String bLog;
		/*if(player.getLUK()>enemy.getWIL() && (rand.nextInt(Math.max(player.getLUK(),player.getWIL())) + Math.min(player.getLUK(),player.getWIL())) < Math.max(player.getLUK(),player.getWIL())){
			
		}*/
		if(attack == true){
			double dmg = player.getBaseDMG()*((player.getSTR()*1.0)/enemy.getVIT());
			bLog = turn+"."+player.getName()+": does "+(int)dmg+" physical damage to "+enemy.getName();
			battleDialogue(bLog);
			return((int)dmg);
		}else{
			double dmg = enemy.getBaseDMG()*((enemy.getSTR()*1.0)/player.getVIT());
			bLog = turn+"."+enemy.getName()+": does "+(int)dmg+" physical damage to "+player.getName();
			battleDialogue(bLog);
			return((int)dmg);
		}
	}

	public boolean isAlive(){
		if(player.getCurrHP()<0 || enemy.getCurrHP()<0){
			return false;
		}
		return true;
	}

	public boolean isAlive(Character player){
		if(player.getCurrHP()<0){
			return false;
		}
		return true;
	}


	public void battle(){
		Scanner userIn = new Scanner(System.in);
		while(isAlive()){
			turn++;
				/* try{
					Runtime.getRuntime().exec("cls");
				}catch(IOException e){System.out.println(e);}
				 */
			//System.out.print("\u001b[2J");
			//System.out.flush();
			player.compareStats(enemy);
			updateChat();
			System.out.println("1.Attack\n2.Spellbook\n3.Skillbook\n4.Inventory\n5.Flee");
			while (!userIn.hasNextInt()) {
				System.out.println("Integer, please!");
				userIn.nextLine();
			}
			int choice = userIn.nextInt();
			if(choice == 1){
				if(player.getEND()>enemy.getEND() && isAlive(player)){
					enemy.physDmgGained(physDamage(true));
					if(isAlive(enemy))
						player.physDmgGained(physDamage(false));
				}else{
					player.physDmgGained(physDamage(false));
					if(isAlive(player))
						enemy.physDmgGained(physDamage(true));
				}
				//System.out.println("DMG: "+DS.physDamage(true));
			}else if(choice == 5){
				break;
			}
			//fightIn = userIn.nextLine();
		}
		//System.out.print("\u001b[2J");
		System.out.flush();
		System.out.println("------------------[End Game Results]-------------------");
		player.compareStats(enemy);
		if(player.getCurrHP()<0 && turn > 1){
			System.out.println("You died!");
		}else if(enemy.getCurrHP()<0 && turn > 1){
			System.out.println(enemy.getName()+" died!");
		}else if(player.getCurrHP()<0 && turn == 1){
			System.out.println("You died in ONE HIT!");
		}else if(enemy.getCurrHP()<0 && turn == 1){
			System.out.println(enemy.getName()+" died in ONE HIT!");
		}
		player.reset();
		userIn.nextLine();
	}


	public void mobileBattle(){
		//Scanner userIn = new Scanner(System.in);
		//while(isAlive()){

		turn++;
				/* try{
					Runtime.getRuntime().exec("cls");
				}catch(IOException e){System.out.println(e);}
				 */
		//System.out.print("\u001b[2J");
		//System.out.flush();
		//player.compareStats(enemy);
		//updateChat();
		//System.out.println("1.Attack\n2.Spellbook\n3.Skillbook\n4.Inventory\n5.Flee");



		if(choice == 1){
			System.out.println("tiggernits");
			if(player.getEND()>enemy.getEND() && isAlive(player)){
				enemy.physDmgGained(physDamage(true));
				if(isAlive(enemy))
					player.physDmgGained(physDamage(false));
			}else{
				player.physDmgGained(physDamage(false));
				if(isAlive(player))
					enemy.physDmgGained(physDamage(true));
			}
			//System.out.println("DMG: "+DS.physDamage(true));
		}else if(choice == 5){
			//break;
		}
		//fightIn = userIn.nextLine();
		//}
		//System.out.print("\u001b[2J");
		if(!isAlive(enemy)||!isAlive(player)) {
			System.out.flush();
			System.out.println("------------------[End Game Results]-------------------");
			player.compareStats(enemy);
			if (player.getCurrHP() < 0 && turn > 1) {
				bLog=("You died!");
			} else if (enemy.getCurrHP() < 0 && turn > 1) {
				bLog=(enemy.getName() + " died!");
				System.out.println(enemy.getCHARNUM());
				bLog =  (player.getName() +" got "+enemy.getCHARNUM()+" Exp.");
				player.addEXP(enemy.getCHARNUM());
			} else if (player.getCurrHP() < 0 && turn == 1) {
				bLog=("You died in ONE HIT!");
			} else if (enemy.getCurrHP() < 0 && turn == 1) {
				bLog=(enemy.getName() + " died in ONE HIT!");
				System.out.println(enemy.getCHARNUM());
				bLog =  (player.getName() +" got "+enemy.getCHARNUM()+" Exp.");
				player.addEXP(enemy.getCHARNUM());
			}
			player.reset();
		}
	}


	public void battleDialogue(String bLog){
		if(battlelog[0] == null){
			battlelog[0]=bLog;
		}else{
			for(int i = battlelog.length-1; i>0; i--){
				battlelog[i] = battlelog[i-1];
			}
			battlelog[0]=bLog;
		}
	}

	public void updateChat(){
		System.out.println("[Battle Log]-------------------------------------------");
		for(int i = 0; i < battlelog.length; i++)
			if(battlelog[i]!=null)
				System.out.println(battlelog[i]);
			else
				System.out.println("");
		System.out.println("-------------------------------------------[Battle Log]");
	}

}
