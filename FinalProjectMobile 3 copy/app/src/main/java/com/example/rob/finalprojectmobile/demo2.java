package com.example.rob.finalprojectmobile;

import java.lang.*;
import java.util.*;
import java.util.regex.*;//Name prevention
import java.util.Arrays.*;//Arraylist
import java.io.*;//Scanner, I/O
import java.lang.reflect.*;//Reflect, getting class methods by string

public class demo2{
	errorLog bugLog = new errorLog();
	SavingSystem<Character> savSys = new SavingSystem<Character>();
	ArrayList<String> charList;
	String[] command;
	Character player = new Character();
	//chatLog cLog = new chatLog();
	demo2(){}
	public static void main(String[]args)
	{
		demo2 DEMO = new demo2();//way to access same classes method from main
		//Main Menu keeps looping forever until user inputs exit command
		while (true)
		{
			for (int i = 0; i < 101; ++i) System.out.println();
			DEMO.menu();
		}
	}
	public void menu()
	{
		CharacterCreation newChar = new CharacterCreation("Character/");
		//Main menu prompt
		/*cLog.newLine("------------------------------------------------------------------------", true);
		cLog.newLine("Welcome to My Demo\nCurrent Character: " + player.getName(),false);
		cLog.newLine("Race: " + player.getRace(),false);
		cLog.newLine("N]ew",false);
		cLog.newLine("[L]oad",false);
		cLog.newLine("[H]ero",false);
		cLog.newLine("[F]ight",false);
		cLog.newLine("[C]ompare",false);
		cLog.newLine("[S]ave",false);
		cLog.newLine("[D]elete",false);
		cLog.newLine("E[X]IT",false);
		cLog.newLine("------------------------------------------------------------------------", true);
*//*
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Welcome to My Demo\nCurrent Character: " + player.getName()
							+ "\nRace: " + player.getRace()
							+ "\n[N]ew"
							+ "\n[L]oad"
							+ "\n[H]ero"
							+ "\n[F]ight"
							+ "\n[C]ompare"
							+ "\n[S]ave"
							+ "\n[D]elete"
							+ "\nE[X]IT");
		System.out.println("------------------------------------------------------------------------");
*/		command = stringSplit(userInput(0, -1));
		System.out.println("");
		//Parse through commands
//------------------------------------New Game------------------------------------
		if (command[0].equalsIgnoreCase("n"))
		{
			//Create new character, Initialize files 
			//Character Save, List of Character
			//player = Start();
			player = newChar.nameCreation();
		}
//-----------------------------------Load Game------------------------------------
		else if (command[0].equalsIgnoreCase("l"))
		{
			boolean isChar = newChar.printRoster();
			if (command.length > 1 && isChar)
				player = loadCharacter(command[1]);
			/*System.out.println("Roster:");
			boolean noCharacters = savSys.printDirectory(new File("Character/"));
			if(!noCharacters){
				System.out.println("~ No Current Characters ~");
			}
			if (command.length > 1 && !noCharacters)
				player = loadCharacter(command[1]);*/
		}
//-----------------------------------Hero Menu------------------------------------
		else if (command[0].equalsIgnoreCase("h"))
		{
			if (command.length > 1)
				heroMenu(loadCharacter(command[1]));
			else
				heroMenu(player);
		}
//----------------------------------Fight Menu-----------------------------------
		else if (command[0].equalsIgnoreCase("f"))
		{
			if (!player.getName().equals("CHARACTER NOT LOADED"))
				initBattle(player);
			else if (command.length > 1)
				bugLog.errorCode(-1, 36, "");//error -1
			else
				bugLog.errorCode(-8, 36, "");//error -1

		}
//--------------------------------Compare Menu-----------------------------------
		else if (command[0].equalsIgnoreCase("c"))
		{
			if (command.length < 3)
			{
				bugLog.errorCode(-1, 49, "");
			}
			else
			{
				//Prepare character extraction from save
				SavingSystem<Character> loadSys = new SavingSystem<Character>("save.txt");
				Character a, b;
				//Must catch since method throws IOException
				a = loadSys.deserial(command[1], "Character/");
				b = loadSys.deserial(command[2], "Character/");
				if (a != null && b != null)
				{
					//Compare the two locally loaded characters
					a.compareStats(b);
				}
				else
				{
					bugLog.errorCode(-2, 65, "");
				}       
			}
//----------------------------------Save Menu-----------------------------------
		}else if (command[0].equalsIgnoreCase("s"))
		{
			savSys.serial(player, player.getName(), "Character/");
//---------------------------------Delete Menu----------------------------------
		}else if (command[0].equalsIgnoreCase("d"))
		{
			if(!savSys.printDirectory(new File("Character/"))){
				System.out.println("No Characters currently exist.");
			}else{
				if (command.length > 1)
					savSys.deleteSave("Character/"+command[1].toUpperCase());
				else{
					System.out.println("Enter the name of the character you wish to delete: ");
					savSys.deleteSave("Character/"+userInput(0,-1).toUpperCase());
				}
			}
		}
//----------------------------------Exit Menu-----------------------------------
		else if (command[0].equalsIgnoreCase("x"))
		{
			//Shuts program down
			System.exit(0);
		}
		gamePause();
	}

	public Character loadCharacter(String charName)
	{
		//Loading character from save files
		/*v1.0
		//String[] loadedChar;
		//SavingSystem<Character> loadSys = new SavingSystem<Character>("save.txt");
		//Must catch since method throws IOException
		try
		{
			//Buffer Array for character details
			loadedChar = loadSys.load(charName);
			//If character was found and loaded create Character Obj
			if (loadedChar[0].contains("?"))
			{
				bugLog.errorCode(-6, 184, charName);
				return (new Character());
			}
			else if (!loadedChar[0].equalsIgnoreCase(charName))
			{
				bugLog.errorCode(-6, 184, charName);
				return (new Character());
			}
		}
		catch (IOException e)
		{
			bugLog.errorCode(0, 186, e.toString());//io error
			return (new Character());
		}*/
		Character c = savSys.deserial(charName, "Character/");
		if(c == null){
			return (new Character());
		}
		return c;
	}

	public void heroMenu(Character p)
	{
		bugLog.confirmCode(-1, 229, p.getName());
		System.out.println("Growth Points: "+p.getPTS());
		System.out.println("Skill Points: ");
		//print attribute and skill points
		System.out.println("[A]ttributes\n[S]kill Tree\nE[X]IT");
		command = stringSplit(userInput(0, -1));
		if (command[0].equalsIgnoreCase("a"))
		{
			player = applyAppPoints(p);
		}		
		else if (command[0].equalsIgnoreCase("x"))
		{
			
		}
		else
		{
		}
	}

	public void displayCharacter(Character p)
	{
		//Display currently loaded character
		if (p == null || p.getName().equals("CHARACTER NOT LOADED"))
		{
			bugLog.errorCode(-8, 203, "");  
		}
		else
		{
			p.stats();
		}
	}

	public void initBattle(Character p)
	{
		//Display currently loaded character & enemy
		//as well as fight menu
		//Prepare character extraction from save
		SavingSystem<Character> loadSys = new SavingSystem<Character>("save.txt");
		SavingSystem<Character> loadEnemyList = new SavingSystem<Character>("enemyDB.txt");
		Enemy enemy = new Enemy();
		String[] enemyType;
		//Must catch since method throws IOException
		try
		{
			enemyType = loadEnemyList.extractRaw();
			if (enemyType[0] != null)
			{
				enemy = new Enemy(p.getLVL(), enemyType);
				//Compare the two locally loaded characters
			}
		}
		catch (IOException e)
		{
			bugLog.errorCode(0, 248, e.toString());//io error
		}
		DamageSystem DS = new DamageSystem(p, enemy);
		DS.battle();
	}
	public void savePlayer(Character p)
	{
		//Saving Character to Text
		SavingSystem<Character> savSys = new SavingSystem<Character>("save.txt", p, true);
		//save throws IOException, must catch
		try
		{
			savSys.save();
		}
		catch (IOException e)
		{
			bugLog.errorCode(0, 156, e.toString());//io error
		}
	}
	public String userInput(int sem, int bound)
	{
		Scanner userIn = new Scanner(System.in);
		String input = "";
		if (sem == 0)
		{
			input = userIn.nextLine();      
		}
		else if (sem == 1)
		{
			userIn.nextLine();      
		}
		else if (sem == 2)
		{
			while (!userIn.hasNextInt())
			{
				System.out.println("Integer, please!");
				userIn.nextLine();
			}
			input = userIn.nextInt() + "";
			if (Integer.parseInt(input) > bound || Integer.parseInt(input) < -1)
			{
				System.out.println(input + " is not a option here.\nPick one of the corresponding choices please.");
				input = userInput(sem, bound);                          
			}
		}else if(sem == 3){
			try{
				input = ((char)userIn.next(".").charAt(0))+"";
			}catch(Exception e){
				System.out.println("Incorrect Input!");
			}
		}
		return input;
	}

	public String[] stringSplit(String str)
	{
		//Splits User Command into parts, Command/Parameter1/../ParameterN
		return str.split("\\s+");
	}

	public void gamePause()
	{
		System.out.println("Press enter to continue...");
		userInput(1, -1);
	}

	public Character applyAppPoints(Character p)
	{
		if(p.getPTS() == 0){
			System.out.println(p.getName()+" does not have points to apply.");
			return p;
		}
		int numOfpts = 0;
		int[] usedPoints = {0,0,0,0,0,0,0};
		String attribute = "";
		String err = "";
		boolean save = false;
		Method method;
		//System.out.println("\nPick an Attribute:\n1.HP\n2.MP\n3.INT\n4.VIT\n5.WIL\n6.STR\n7.LUK\n8.SPD");
		//GET CHAR OR GET KEY
		while(p.getPTS()>usedPoints[6]){
			//for (int i = 3; i < p.charForm.length; i++){
				//prompt question, how many points would you like to put here
				//do this for each attribute
				//attribute = userInput(3,p.getPTS());
				p.heroStats(usedPoints);
				System.out.println(err+"Apply your points by pressing the indicated button.\nE[X]IT to return to the main menu.");
				attribute = userInput(3,p.getPTS());
				err = "";
				if(attribute.equalsIgnoreCase("i")){
					usedPoints[0]++;
					usedPoints[6]++;
				}else if(attribute.equalsIgnoreCase("v")){
					usedPoints[1]++;
					usedPoints[6]++;
				}else if(attribute.equalsIgnoreCase("w")){
					usedPoints[2]++;
					usedPoints[6]++;
				}else if(attribute.equalsIgnoreCase("s")){
					usedPoints[3]++;
					usedPoints[6]++;
				}else if(attribute.equalsIgnoreCase("l")){
					usedPoints[4]++;
					usedPoints[6]++;
				}else if(attribute.equalsIgnoreCase("e")){
					usedPoints[5]++;
					usedPoints[6]++;
				}else {
					err = "Incorrect Input: ";
				}
				if(p.getPTS()==usedPoints[6] || attribute.equalsIgnoreCase("x")){
					p.heroStats(usedPoints);
					System.out.println("Would you like to save?[Y/N]");
					if (userInput(0, -1).equalsIgnoreCase("y"))
					{
						save = true;
						break;
					}else{
						save = false;
					}
				}
			//}
		}
		if(save){
			for (int i = 5; i < p.charForm.length; i++){
				Class[] paramInt = new Class[1];
				paramInt[0] = Integer.TYPE;
				try{
					method = p.getClass().getMethod("add"+p.charForm[i], paramInt);
					method.invoke(p,usedPoints[i-5]);
				}catch(SecurityException e){
					System.out.println(e);
					break;
				}catch(NoSuchMethodException e){
					System.out.println(e);
					break;
				}catch(IllegalArgumentException e){
					System.out.println(e);
					break;
				}catch(IllegalAccessException e){
					System.out.println(e);
					break;
				}catch(InvocationTargetException e){
					System.out.println(e);
					break;
				}
			}
			p.health();
			p.mana();
			p.baseDMG();
			savSys.serial(p, p.getName(),"Character/");
		}
		usedPoints[0] = 0;
		usedPoints[1] = 0;
		usedPoints[2] = 0;
		usedPoints[3] = 0;
		usedPoints[4] = 0;
		usedPoints[5] = 0;
		usedPoints[6] = 0;
		//p.updateStats();
		//p.stats();
		return p;
		//compare the resulting character with the character before applied points
		//may need to add more specifications to character obj
	}
	
	
}
