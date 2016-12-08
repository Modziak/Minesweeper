package model;

public class LeaderboardPosition {

	private String name;
	private int time;
	private String date;
	
	public LeaderboardPosition(String name, int time, String date){
		this.name = name;
		this.time = time;
		this.date = date;
	}
	
	public String getName(){
		return name;
	}
	
	public int getTime(){
		return time;
	}
	
	public String getDate(){
		return date;
	}
	
	@Override
	public String toString(){
		return name + " " + time + " " + date;
	}
}
