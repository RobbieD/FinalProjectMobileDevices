package com.example.rob.finalprojectmobile;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;

//import java.nio.file.*;
//import java.nio.charset.*;
public class SavingSystem<T>
{
	String dir;
	T local;
	boolean appendTo;
	Context context;
	//final static Charset ENCODING = StandardCharsets.UTF_8;
	SavingSystem(){
	}
	SavingSystem(Context c){
		this.context = c;
	}
	SavingSystem(String path)
	{
		dir = path;
		appendTo = false;
	}
	SavingSystem(String path, T obj)
	{
		dir = path;
		local = obj;
		appendTo = false;
	}
	SavingSystem(String path, T obj, boolean app)
	{
		dir = path;
		local = obj;
		appendTo = app;
	}
	
	//1.0
	public void save()throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(dir, appendTo));
		out.write(local.toString());
		out.close();
	}
	public void save(String[] obj)throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(dir, appendTo));
		for (int i = 0; i < obj.length; i++)
			out.write(obj[i] + "\n");
		out.close();
	}

	public String[] load(String target)throws IOException
	{
		//Path path = Paths.get(dir);
		String[] loader;
		String[] f = openFile();
		//try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
		//String line = null;
		int numOfElements = Integer.parseInt(f[0]);
		int i = 0;
		int index = 1;
		loader = new String[numOfElements];
		loader[0] = "???????????????????????????";
		while (index<f.length)
		{
			//process each line in some way
			if (i < numOfElements)
			{
				loader[i] = f[index];
				//System.out.println(i+"l: "+line);
			}
			else if (i > (numOfElements) && !loader[0].equalsIgnoreCase(target))
			{
				i = -1;
			}
			else if (i > (numOfElements) && loader[0].equalsIgnoreCase(target))
				break;
			i++;
			index++;
		}      
		//}
		return loader;
	}

	public String[] extractRaw()throws IOException
	{
		
		//Set file path
		//path = Paths.get(dir);
		String[] loader = openFile();
		/*//Set Reader
		try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
			String line = null;
			//Get first line of file indicating length of array
			int numOfElements = Integer.parseInt(reader.readLine());
			int i = 1;
			loader = new String[numOfElements];
			loader[0] = numOfElements + "";
			while ((line = reader.readLine()) != null)
			{
				//process each line in some way
				if (i < numOfElements)
				{
					loader[i] = line;
					//System.out.println(i+"eR: "+line);
				}
				i++;		
			}
		}*/
		return loader;
	}
	//v1.5
	public String[] openFile() throws IOException
	{
		FileReader fr = new FileReader(dir);
		BufferedReader br = new BufferedReader(fr);

		int numOfLines = readLines();
		String[] textData = new String[numOfLines];
		for (int i = 0; i < numOfLines; i++)
		{
			textData[i]=br.readLine();		
		}
		br.close();
		return textData;
	}
	
	int readLines() throws IOException
	{
		FileReader fr = new FileReader(dir);
		BufferedReader br = new BufferedReader(fr);

		String line;
		int numOfLine = 0;

		while ((line = br.readLine()) != null)
		{
			numOfLine++;
		}

		br.close();
		return numOfLine;
	}
	
	int numberOfElements()throws IOException{
		FileReader fr = new FileReader(dir);
		BufferedReader br = new BufferedReader(fr);
		//int num = Integer.parseInt(br.readLine());
		return Integer.parseInt(br.readLine());
	}
	
	//v2.0
	
	public void serial(T c, String n, String path){
		try{
			//File file = new File(context.getFilesDir(), filename);
			FileOutputStream fOut = new FileOutputStream(context.getFilesDir()+n+".ser");
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(c);
			out.close();
			fOut.close();
			System.out.println("Serialized data is saved to "+context.getFilesDir()+"!");
		}catch(IOException e){
			System.out.println("IOException: I dont even");
			e.printStackTrace();
		}
		waitTime(1000);
	}

	public void mobileSerial(T c, String n, String path){
		try{
			//File file = new File(context.getFilesDir(), filename);
			FileOutputStream fos = context.openFileOutput(n+".ser"+path, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(c);
			os.close();
			fos.close();
			System.out.println("Serialized data is saved to "+context.getFilesDir()+"!");
		}catch(IOException e){
			System.out.println("IOException: I dont even");
			e.printStackTrace();
		}
		waitTime(1000);
	}

	public T deserial(String n, String path){
		T c = null;
		try{
			FileInputStream fIn = new FileInputStream(context.getFilesDir()+n+".ser");
			ObjectInputStream in = new ObjectInputStream(fIn);
			c =(T)in.readObject();
			in.close();
			fIn.close();
			System.out.println("Deserialized data...");
		}catch(IOException e){
			System.out.println("IOException: Character not found!");
			System.out.println(n + " has not been loaded into the game!");
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			System.out.println("ClassNotFoundException: Character not found!");
			System.out.println(n + " has not been loaded into the game!");
			//e.printStackTrace();
		}
		waitTime(1000);
		System.out.println(n + " has been loaded into the game!");
		return c;
	}

	public T mobileDeserial(String n, String path){
		T c = null;
		try{
			FileInputStream fIn =  context.openFileInput(n+".ser"+path);
			ObjectInputStream in = new ObjectInputStream(fIn);
			c =(T)in.readObject();
			in.close();
			fIn.close();
			System.out.println("Deserialized data from " + context.getFilesDir() + "!");
		}catch(IOException e){
			System.out.println("IOException: Character not found!");
			System.out.println(n + " has not been loaded into the game!");
			//e.printStackTrace();
		}catch(ClassNotFoundException e){
			System.out.println("ClassNotFoundException: Character not found!");
			System.out.println(n + " has not been loaded into the game!");
			//e.printStackTrace();
		}
		waitTime(1000);
		if(c!=null)
			System.out.println(n + " has been loaded into the game!");
		return c;
	}
	
	//v2.5
	public void deleteSave(String fname){
		System.out.println("Deleting "+fname+"...");
		try{
			File file = new File(fname+".ser");
			if(file.delete()){
				System.out.println(fname+" is deleted!");
			}else{
				System.out.println("Delete operation failed.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void mobileDeleteSave(String fname){
		System.out.println("Deleting "+fname+"...");
		try{
			//FileInputStream fIn = new FileInputStream(context.getFilesDir()+fname	+".ser");
			//ObjectInputStream in = new ObjectInputStream(fIn);

			File file = new File(context.getFilesDir()+"/"+fname+".ser");
			System.out.println(file);
			if(file.delete()){
				System.out.println(fname+" is deleted!");
			}else{
				System.out.println("Delete operation failed.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean printDirectory(final File folder){
		int index = 0;
		String fname;
		File newFolder = context.getFilesDir();
		for(final File fileEntry : newFolder.listFiles()){
			if(fileEntry.isDirectory()){
				//cascade effect
				//recall this loop with fileEntry appended to current folder path
			}else{
				index++;
				fname = fileEntry.getName();
				System.out.println(index+". "+fname.substring(0,fname.indexOf('.')));
				//String name = (fname.substring(0,fname.indexOf('.')));

			}
		}
		if(index == 0){
			return false;
		}
		return true;
	}


	public ArrayList<String> printDirectoryString(final File folder){
		int index = 0;
		String fname;
		String nameChar;
		ArrayList<String> retList = new ArrayList<String>();
		File newFolder = context.getFilesDir();
		for(final File fileEntry : newFolder.listFiles()){
			if(fileEntry.isDirectory()){
				//cascade effect
				//recall this loop with fileEntry appended to current folder path
			}else if(fileEntry.getName().substring(fileEntry.getName().length()-4,fileEntry.getName().length()).equalsIgnoreCase(".ser")){
				index++;
				fname = fileEntry.getName();
				//System.out.println(index+". "+fname.substring(0,fname.indexOf('.')));
				nameChar = (fname.substring(0,fname.indexOf('.')));
				retList.add(nameChar);
			}
		}
		if(index == 0){
			return retList;
		}
		return retList;
	}
	
	
	public T loadObj(String name, String objDir, T loadObj)
	{
		T c = deserial(name, objDir+"/");
		if(c == null){
			System.out.println(name + " has not been loaded to the current Player Slot!");
			return (loadObj);
		}
		System.out.println(name + " has been loaded to the current Player Slot!");
		return c;
	}
	public void waitTime(int time){
		try {
		    Thread.sleep(time);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
}	 
