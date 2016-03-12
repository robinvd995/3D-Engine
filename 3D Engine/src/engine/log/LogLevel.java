package engine.log;

public enum LogLevel {

	INFO("INFO"),
	WARNING("WARNING"),
	ERROR("ERROR");
	
	public final String name;
	
	private LogLevel(String name){
		this.name = name;
	}
}
