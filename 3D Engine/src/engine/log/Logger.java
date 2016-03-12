package engine.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import engine.Time;

public class Logger {
	
	private final String logName;
	private final String filePath;
	private boolean shouldLogToFile = true;
	
	private BufferedWriter writer;
	
	public Logger(String logName, String file){
		this.logName = logName;
		this.filePath = System.getProperty("user.dir") + "/log";
		try {
			createWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void log(LogLevel level, String s, boolean writeToLog){
		String timeFormat = "[" + Time.getTimeFormat() + "]";
		String levelFormat = "[" +  level.name + "]";
		String nameFormat = "[" + logName + "]";
		write(timeFormat + levelFormat + ":" + s);
		System.out.println(timeFormat + nameFormat + levelFormat + ":" + s);
	}
	
	private void write(String s){
		try {
			writer.write(s + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createWriter() throws IOException{
		File dirfile = new File(filePath);
		if(dirfile.mkdirs()){
			
		}

		File file = new File(filePath + "/" + logName + ".log");
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file);
		writer = new BufferedWriter(fw);
	}
	
	public void inf(String s){
		log(LogLevel.INFO, s, shouldLogToFile);
	}
	
	public void war(String s){
		log(LogLevel.WARNING, s, shouldLogToFile);
	}
	
	public void err(String s){
		log(LogLevel.ERROR, s, shouldLogToFile);
	}
	
	public void closeLog(){
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
