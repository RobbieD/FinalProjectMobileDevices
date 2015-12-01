package com.example.rob.finalprojectmobile;

public class errorLog{
	errorLog(){}
	public void errorCode(int err, int line, String e){
		if(err == -1){
			System.out.println("ERROR -1-"+line+": Missing Parameters!");
		}else if(err == -2){
			System.out.println("ERROR -2-"+line+": A Character Does Not Exist!");
		}else if(err == -3){
			System.out.println("ERROR -3-"+line+": Invalid Name, Should not Contain Special Characters");
		}else if(err == -4){
			System.out.println("ERROR -4-"+line+": Invalid Name, Name is too Long, "+e+" > 18");
		}else if(err == -5){
			System.out.println("ERROR -5-"+line+": Invalid Name, "+e+" Character Already Exists");
		}else if(err == -6){
			System.out.println("ERROR -6-"+line+": "+e+" does not exist!");
		}else if(err == -7){
			System.out.println("ERROR -7-"+line+": Invalid Name, Name is too Short, "+e+" < 5");
		}else if(err == -8){
			System.out.println("ERROR -8-"+line+": A Character is Not Loaded");
		}else if(err == -9){
			System.out.println("ERROR -9-"+line+": Missing parameters!");	
		}else if(err == -10){
			System.out.println("ERROR -10-"+line+": Could not Load Character!");	
		}else if(err == -11){
			System.out.println("ERROR -11-"+line+": File does not contain requested data!");	
		}else{
			System.out.println("IO ERROR-"+line+": "+e);
		}
	}

	public void confirmCode(int err, int line, String e){
		if(err == -1){
			System.out.println("SYSMSG -1-"+line+": "+e+" Character Loaded!");
		}else if(err == -2){
			System.out.println("ERROR -2-"+line+": A Character Does Not Exist!");
		}else if(err == -3){
			System.out.println("ERROR -3-"+line+": Invalid Name, Should not Contain Special Characters");
		}else if(err == -4){
			System.out.println("ERROR -4-"+line+": Invalid Name, Name is too Long, "+e+" > 18");
		}else if(err == -5){
			System.out.println("ERROR -5-"+line+": Invalid Name, "+e+" Character Already Exists");
		}else if(err == -6){
			System.out.println("ERROR -6-"+line+": "+e+" does not exist!");
		}else if(err == -7){
			System.out.println("ERROR -7-"+line+": Is Shorter than 5 Characters");
		}else if(err == -8){
			System.out.println("ERROR -8-"+line+": Missing parameters!");
		}else if(err == -9){
			System.out.println("ERROR -9-"+line+": Missing parameters!");	
		}else{
			System.out.println("IO ERROR-"+line+": "+e);
		}	
	}
}