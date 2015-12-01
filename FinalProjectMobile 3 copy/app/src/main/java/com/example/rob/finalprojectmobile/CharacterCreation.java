package com.example.rob.finalprojectmobile;

import java.lang.*;
import java.util.regex.*;//Name prevention
import java.util.*;
import java.io.*;//Scanner, I/O


public class CharacterCreation{
	//Character Info
	errorLog bugLog = new errorLog();
	Character player = new Character();
	SavingSystem<Character> savSys = new SavingSystem<Character>();
	ArrayList<String> charList = new ArrayList<String>();
	CharacterCreation(String dir){
		getCharacterRoster(new File(dir));
	}
	public boolean printRoster()
	{
		System.out.println("Roster:");
		if(charList.size() < 1){
			System.out.println("~ No Characters Available ~");
			return false;
		}
		for (int i = 1; i < charList.size()+1; i++)
			System.out.println((i) + ". " + charList.get(i-1));
		return true;
	}
	public boolean getCharacterRoster(final File folder)
	{
		String fname;
		for(final File fileEntry : folder.listFiles()){
			if(fileEntry.isDirectory()){
				//cascade effect
				//recall this loop with fileEntry appended to current folder path
			}else if(charList.contains(fileEntry.getName().substring(0,fileEntry.getName().indexOf('.')))){
				//no dupes
			}else{
				fname = fileEntry.getName();
				charList.add(fname.substring(0,fname.indexOf('.')));
			}
		}
		if(charList.size() > 0){
			return true;
		}
		return false;
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
		}
		return input;
	}
	public boolean usernameCheck(String name){
		boolean b = false;
		//Name Prevention Essentials
		Pattern pat = Pattern.compile("[\\s\\W]+", Pattern.CASE_INSENSITIVE);
		Matcher m;
		//Keeps looping until proper name
		m = pat.matcher(name);
		b = m.find();
		if (name.equalsIgnoreCase("exit"))
		{
			return false;
		}
		if (b)
			bugLog.errorCode(-3, 110, "");
		if (name.length() > 18)
		{
			bugLog.errorCode(-4, 112, name.length() + "");
			b = true;
		}
		if (name.length() < 5)
		{
			bugLog.errorCode(-7, 116, name.length() + "");
			b = true;
		}
		if (!b)
			for (int i = 1; i < charList.size(); i++)
				if (charList.get(i).equalsIgnoreCase(name)){
					//Check if character already exists
					bugLog.errorCode(-5, 122, name);
					b = true;
				}
		return b;
	}
	public Character nameCreation()
	{
		if (getCharacterRoster(new File("Character/")))
			printRoster();
		else
			System.out.println("There are no characters currently saved.");
		boolean check = false;
		String name = "";
//---------------------------------------------------Name---------------------------------------------------
		while(check || !name.equalsIgnoreCase("exit")){
			System.out.println("Character Name: ");
			name = userInput(0, -1);
			check = usernameCheck(name);
			if(name.equalsIgnoreCase("exit")){
				return(new Character());
			}else if(!check){
				System.out.println("Is this name OK?[Y/N]\n(" + name + ")");
				if (userInput(0, -1).equalsIgnoreCase("y"))
					break;
			}
		}
		charList.add(name);
		return racialCreation(name);
	}
	public Character racialCreation(String name){
		int classID,raceID;
		while (player.getRace() == "UNKNOWN" || player.getClassID() == "UNKNOWN")
		{
			System.out.println("\nPick a Class:\n1.Warrior\n2.Mage\n3.Trickster\n4.Knave");
			classID = Integer.parseInt(userInput(2, 4));
//---------------------------------------------------Race---------------------------------------------------
			System.out.println("\nPick a Race:\n1.HUMAN\n2.ELF\n3.BEAST\n4.HUMANOID\n5.MACHINE");
			raceID = Integer.parseInt(userInput(2, 5));

			//Creation of Character Obj
			player = new Character(name, classID, raceID);
		}
		player.stats();//Print new character
		savSys.serial(player, player.getName(), "Character/");
		return player;
	}
}