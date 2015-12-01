package com.example.rob.finalprojectmobile;//Character Version 1

import java.util.Random;

public class Character implements java.io.Serializable{
	//add speed determines whether to attack again, how attacks first
	String NAME, CLASS, RACE;
	int INT, VIT, WIL, STR, DEX, LUK, END, CHARNUM, HP, MP, LVL, EXP, PTS, DMG;
	int bonusHP, bonusMP;
	long id;
	transient int currHP, currMP;
	String[] charForm = {"NAME","CLASS","RACE","HP","MP","INT","VIT","WIL","STR","LUK","END","PTS"};
	//skill[] skillTree;
	public String compare="";
	private static final long serialVersionUID = -6064917095379233746L;

	//Weapon wep;
	Character(){
	}
	Character(String n, String c, String r){
		NAME = n;
		if (r.equalsIgnoreCase("human")){
			RACE = "HUMAN";
		}else if (r.equalsIgnoreCase("elf")){
			RACE= "ELF";
		}else if (r.equalsIgnoreCase("beast")){
			RACE= "BEAST";
		}else if (r.equalsIgnoreCase("humanoid")){
			RACE= "HUMANOID";
		}else if (r.equalsIgnoreCase("machine")){
			RACE= "MACHINE";
		}else{
			RACE= null;
			System.out.println("Invalid Race!");
		}
		Random rand = new Random();
		if (c.equalsIgnoreCase("warrior")){
			CLASS = "Warrior";
			INT = 10;
			VIT = 30;
			WIL = 10;
			STR = 25;
			LUK = 10;
			END = 5;
			PTS = 10;
		}else if (c.equalsIgnoreCase("mage")){
			CLASS = "Mage";
			INT = 30;
			VIT = 10;
			WIL = 25;
			STR = 5;
			LUK = 10;
			END = 10;
			PTS = 10;
		}else if (c.equalsIgnoreCase("trickster")){
			CLASS = "Trickster";
			INT = 10;
			VIT = 10;
			WIL = 20;
			STR = 10;
			LUK = 20;
			END = 20;
			PTS = 10;
		}else if (c.equalsIgnoreCase("knave")){
			CLASS = "Knave";
			INT = rand.nextInt(15)+1;
			VIT = rand.nextInt(15)+1;
			WIL = rand.nextInt(15)+1;
			STR = rand.nextInt(15)+1;
			LUK = rand.nextInt(15)+1;
			END = rand.nextInt(15)+1;
			PTS = 80-(INT+VIT+WIL+STR+LUK);
			if(PTS < 0)
				PTS = 0;
		}else{
			CLASS = null;
			System.out.println("Invalid Class");
		}

		health();
		mana();
		baseDMG();
		LVL = 1;
		EXP = 0;
		bonusHP = 0;
		bonusMP = 0;
		currHP = HP;
		currMP = MP;
		CHARNUM = 	rand.nextInt(100)+1;
	}
	Character(String n, int c, int r){
		NAME = n;
		if (r == 1){
			RACE = "HUMAN";
		}else if (r == 2){
			RACE= "ELF";
		}else if (r == 3){
			RACE= "BEAST";
		}else if (r == 4){
			RACE= "HUMANOID";
		}else if (r == 5){
			RACE= "MACHINE";
		}else{
			RACE= null;
			System.out.println("Invalid Race!");
		}
		Random rand = new Random();
		if (c==1){
			CLASS = "Warrior";
			INT = 10;
			VIT = 30;
			WIL = 10;
			STR = 25;
			LUK = 10;
			END = 5;
			PTS = 10;
		}else if (c==2){
			CLASS = "Mage";
			INT = 30;
			VIT = 10;
			WIL = 25;
			STR = 5;
			LUK = 10;
			END = 10;
			PTS = 10;
		}else if (c==3){
			CLASS = "Trickster";
			INT = 10;
			VIT = 10;
			WIL = 20;
			STR = 10;
			LUK = 20;
			END = 20;
			PTS = 10;
		}else if (c == 4){
			CLASS = "Knave";
			INT = rand.nextInt(15)+1;
			VIT = rand.nextInt(15)+1;
			WIL = rand.nextInt(15)+1;
			STR = rand.nextInt(15)+1;
			LUK = rand.nextInt(15)+1;
			END = rand.nextInt(15)+1;
			PTS = 80-(INT+VIT+WIL+STR+LUK);
			if(PTS < 0)
				PTS = 0;
		}else{
			CLASS = null;
			System.out.println("Invalid Class");
		}

		health();
		mana();
		baseDMG();
		LVL = 1;
		EXP = 0;
		bonusHP = 0;
		bonusMP = 0;
		currHP = HP;
		currMP = MP;
		CHARNUM = 	rand.nextInt(100)+1;
	}

	Character(String[] obj){
		NAME = obj[0];
		RACE = obj[1];
		CLASS = obj[2];
		LVL = Integer.parseInt(obj[3]);
		EXP = Integer.parseInt(obj[4]);
		INT = Integer.parseInt(obj[7]);
		VIT = Integer.parseInt(obj[8]);
		WIL = Integer.parseInt(obj[9]);
		STR = Integer.parseInt(obj[10]);
		LUK = Integer.parseInt(obj[11]);
		END = Integer.parseInt(obj[12]);
		PTS = Integer.parseInt(obj[13]);
		CHARNUM = Integer.parseInt(obj[14]);
		health();
		mana();
		baseDMG();
		currHP = HP;
		currMP = MP;
	}

	Character(String n, String r, String c, int lvl, int exp, int i, int v, int w, int s, int l, int e, int pts){
		NAME = n;
		RACE = r;
		CLASS = c;
		LVL = lvl;
		EXP = exp;
		INT = i;
		VIT = v;
		WIL = w;
		STR = s;
		LUK = l;
		END = e;
		PTS = pts;
		CHARNUM = new Random().nextInt(100)+1;
		health();
		mana();
		baseDMG();
		currHP = HP;
		currMP = MP;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void reset(){
		currHP = HP;
		currMP = MP;
	}

	public void health(){
		HP = 100+VIT*10+STR*5;
	}

	public void mana(){
		MP = 100+INT*10+WIL*5;
	}

	public void baseDMG(){
		if(CLASS.equalsIgnoreCase("warrior")){
			DMG = STR*5+VIT+10;
		}else if(CLASS.equalsIgnoreCase("mage")){
			DMG = WIL*5+INT+10;
		}else if(CLASS.equalsIgnoreCase("trickster")){
			DMG = STR*5+LUK+10;
		}else{
			DMG = STR*6+10;
		}
	}

	public int mockHealth(int v, int s){
		return 100+(VIT+v)*10+(STR+s)*5;
	}

	public int mockMana(int i, int w){
		return 100+(INT+i)*10+(WIL+w)*5;
	}

	public int mockDMG(int i, int v, int w, int s, int l){
		if(CLASS.equalsIgnoreCase("warrior")){
			return (STR+s)*5+(VIT+v)+10;
		}else if(CLASS.equalsIgnoreCase("mage")){
			return (WIL+w)*5+(INT+i)+10;
		}else if(CLASS.equalsIgnoreCase("trickster")){
			return (STR+s)*5+(LUK+l)+10;
		}
		return (STR+s)*6+10;

	}

	public void updateStats(){
		levelUp();
		baseDMG();
		health();
		mana();
		reset();
	}

	public void levelUp(){
		if(LVL*100 < EXP){
			LVL++;
			PTS += 5;
			EXP = 0;
		}else{
			//System.out.println("NOT ENOUGH EXP");
		}
	}

	public void physDmgGained(int dmg){
		this.currHP = currHP - dmg;
	}

	public void magiDmgGained(int dmg){
		this.currMP = currMP - dmg;
	}

	public void stats(){
		System.out.println(
			"Name: "+getName()+"\n"+
			"Race: "+getRace()+"\n"+
			"Class: "+getClassID()+"\n"+
			"Level: "+getLVL()+"\n"+
			"Experience: "+getEXP()+"\n"+
			"Health: "+getHP()+"\n"+
			"Magic: "+getMP()+"\n"+
			"DMG: "+getBaseDMG()+"\n"+
			"INT: "+getINT()+"\n"+
			"VIT: "+getVIT()+"\n"+
			"WIL: "+getWIL()+"\n"+
			"STR: "+getSTR()+"\n"+
			"LUK: "+getLUK()+"\n"+
			"END: "+getEND()+"\n"+
			"Growth Points: "+getPTS());
	}

	public void heroStats(int[] heroPoints){
		System.out.println(
			"Name: "+getName()+"\n"+
			"Race: "+getRace()+"\n"+
			"Class: "+getClassID()+"\n"+
			"Level: "+getLVL()+"\n"+
			"Experience: "+getEXP()+"\n"+
			"Health: "+getHP()+ " -> "+ mockHealth(heroPoints[3],heroPoints[1]) +"\n"+
			"Magic: "+getMP()+ " -> "+ mockMana(heroPoints[0],heroPoints[2]) +"\n"+
			"DMG: "+getBaseDMG()+ " -> "+ mockDMG(heroPoints[0],heroPoints[1],heroPoints[2],heroPoints[3],heroPoints[4]) +"\n"+
			"[I]NT: "+getINT()+" + "+heroPoints[0]+"\n"+
			"[V]IT: "+getVIT()+" + "+heroPoints[1]+"\n"+
			"[W]IL: "+getWIL()+" + "+heroPoints[2]+"\n"+
			"[S]TR: "+getSTR()+" + "+heroPoints[3]+"\n"+
			"[L]UK: "+getLUK()+" + "+heroPoints[4]+"\n"+
			"[E]ND: "+getEND()+" + "+heroPoints[5]+"\n"+
			"Growth Points: "+getPTS()+" -> " + (getPTS()-heroPoints[6]));
	}

	public String getName(){
		if(NAME==null)
			return "CHARACTER NOT LOADED";
		return NAME;
	}
	public String getRace(){
		if(RACE==null)
			return "UNKNOWN";
		return RACE;
	}
	public String getClassID(){
		if(CLASS==null)
			return "UNKNOWN";
		return CLASS;
	}
	public int getLVL(){return LVL;}
	public int getEXP(){return EXP;}
	public int getHP(){return HP;}
	public int getMP(){return MP;}
	public int getCurrHP(){return currHP;}
	public int getCurrMP(){return currMP;}
	public int getINT(){return INT;}
	public int getVIT(){return VIT;}
	public int getWIL(){return WIL;}
	public int getSTR(){return STR;}
	public int getLUK(){return LUK;}
	public int getEND(){return END;}
	public int getPTS(){return PTS;}
	public int getBaseDMG(){return DMG;}
	public int getCHARNUM(){return CHARNUM;}
	public String getForm(int index){return charForm[index];}
	public void setHP(int h){HP = h;}
	public void setMP(int m){MP = m;}
	public void setEXP(int e){EXP = e;}
	public void setINT(int i){INT = i;}
	public void setVIT(int v){VIT = v;}
	public void setWIL(int w){WIL = w;}
	public void setSTR(int s){STR = s;}
	public void setLUK(int l){LUK = l;}
	public void setEND(int e){END = e;}
	public void setPTS(int p){PTS = p;}
	public void addHP(int h){bonusHP += 10*h;}
	public void addMP(int m){bonusMP += 10*m;}
	public void addEXP(int e){EXP += LVL*10*e;levelUp();}
	public void addINT(int i){INT += i;}
	public void addVIT(int v){VIT += v;}
	public void addWIL(int w){WIL += w;}
	public void addSTR(int s){STR += s;}
	public void addLUK(int l){LUK += l;}
	public void addEND(int e){END += e;}
	public void addPTS(int p){PTS -= p;}


	public void compareStats(Character local){
			System.out.format(
			"Name %21s|%-15s\t Name%nRace %21s|%-15s\t Race%nClass %20s|%-15s\t Class%n",
			this.getName(),local.getName(),this.getRace(),local.getRace(),this.getClassID(),local.getClassID());
			System.out.println("-------------------------------------------------------");
			System.out.format("Level %20d|%-5d\t\t Level%n",this.getLVL(),local.getLVL());
			System.out.format("Exp %22d|%-5d\t\t Exp%n",this.getEXP(),local.getEXP());
			System.out.format("Health %15d\\%d|%d/%-12d\t Health%n",this.getHP(),this.getCurrHP(),local.getCurrHP(),local.getHP());
			System.out.format("Magic %16d\\%d|%d/%-12d\t Magic%n",this.getMP(),this.getCurrMP(),local.getCurrMP(),local.getMP());
			System.out.println("-------------------------------------------------------");
			//if(local.RACE=="HUMAN"){
				System.out.format("INT %22d|%-19d\t INT%n",this.getINT(),local.getINT());
				System.out.format("VIT %22d|%-19d\t VIT%n",this.getVIT(),local.getVIT());
				System.out.format("WIL %22d|%-19d\t WIL%n",this.getWIL(),local.getWIL());
				System.out.format("STR %22d|%-19d\t STR%n",this.getSTR(),local.getSTR());
				System.out.format("LUK %22d|%-19d\t LUK%n",this.getLUK(),local.getLUK());
				System.out.format("END %22d|%-19d\t END%n",this.getEND(),local.getEND());
				System.out.format("DMG %22d|%-19d\t DMG%n",this.getBaseDMG(),local.getBaseDMG());
			/*}else{
				System.out.format("INT %22d %-22s INT%n",this.getINT(),"??");
				System.out.format("VIT %22d %-22s VIT%n",this.getVIT(),"??");
				System.out.format("WIL %22d %-22s WIL%n",this.getWIL(),"??");
				System.out.format("STR %22d %-22s STR%n",this.getSTR(),"??");
				System.out.format("LUK %22d %-22s LUK%n",this.getLUK(),"??");
			}*/
			System.out.println("-------------------------------------------------------");
	}

	public void mobileCompareStats(){
		compare = (
				"Name: "
		);
		compare+=("" + this.getName() );
		compare+=("\nRace \t Class");
		compare+=( "\n" + this.getRace() + "\t" + this.getClassID());
		compare+=("\n------------------------");
		compare+=("\nLevel: ");
		compare+=(""+this.getLVL());
		compare+=("\nExp: ");
		compare+=(""+this.getEXP());
		compare+=("\nHealth: ");
		compare+=(""+ this.getCurrHP()+"/"+ this.getHP());

		compare+=("\n------------------------");
		//if(local.RACE=="HUMAN"){
		compare+=("\nINT: ");
		compare+=("" + this.getINT());
		compare+=("\nVIT: ");
		compare+=(""+ this.getVIT());
		compare+=("\nWIL: ");
		compare+=(""+ this.getWIL());
		compare+=("\nSTR: ");
		compare+=(""+ this.getSTR());
		compare+=("\nLUK: ");
		compare+=(""+ this.getLUK());
		compare+=("\nEND: ");
		compare+=(""+ this.getEND());
		compare+=("\nDMG: ");
		compare+=(""+this.getBaseDMG());
			/*}else{
				System.out.format("INT %22d %-22s INT%n",this.getINT(),"??");
				System.out.format("VIT %22d %-22s VIT%n",this.getVIT(),"??");
				System.out.format("WIL %22d %-22s WIL%n",this.getWIL(),"??");
				System.out.format("STR %22d %-22s STR%n",this.getSTR(),"??");
				System.out.format("LUK %22d %-22s LUK%n",this.getLUK(),"??");
			}*/
		compare+=("\n-----------------------");
		System.out.println(compare);
	}

	public String toString(){
		return(	"15\n"+
			getName()+"\n"+
			getRace()+"\n"+
			getClassID()+"\n"+
			getLVL()+"\n"+
			getEXP()+"\n"+
			getHP()+"\n"+
			getMP()+"\n"+
			getINT()+"\n"+
			getVIT()+"\n"+
			getWIL()+"\n"+
			getSTR()+"\n"+
			getLUK()+"\n"+
			getEND()+"\n"+
			getBaseDMG()+"\n"+
			getPTS()+"\n"+
			getCHARNUM()+"\n~\n");
	}
}
