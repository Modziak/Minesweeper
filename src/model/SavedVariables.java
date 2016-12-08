package model;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public final class SavedVariables {
	
	public static enum Difficulty{BEGINNER, INTERMEDIATE, ADVANCED}; 
	
	private static SavedVariables instance;
	private static final String variablesPath = System.getProperty("user.dir") + "\\savedVariables.ms";
	private static List<List<LeaderboardPosition>> leaderboards;
	
	private static int width;
	private static int height;
	private static int buttonSize;
	private static Dimension dimension;
	
	private static int x = 16;
	private static int y = 30;
	private static int mines = 99;
	
	private SavedVariables(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		width = (int) gd.getDisplayMode().getWidth();
		height = (int) gd.getDisplayMode().getHeight();
		
		//Ustandaryzowana wielkosc przycisku
		buttonSize = (int)(width/3)/30;
		System.out.println(buttonSize);
		dimension = new Dimension(buttonSize, buttonSize);
	}
	
	public static SavedVariables init(){
		return instance != null ? instance : (instance = new SavedVariables());
	}
	
	public void loadData() throws NumberFormatException, IOException{
		File file = new File(variablesPath);
		BufferedReader br = null;
		leaderboards = new ArrayList<List<LeaderboardPosition>>(5);
		List<LeaderboardPosition> leaderboard;
		
		String line;
		String[] lp;
			
		if(file.exists()){
			try{
				br = new BufferedReader(new FileReader(file));
				x = Integer.parseInt(br.readLine());
				y = Integer.parseInt(br.readLine());
				mines = Integer.parseInt(br.readLine());
				
				for(int d=0; d<3; d++){
					leaderboard = new ArrayList<LeaderboardPosition>();
					for(int i=0; i<=5 && (line = br.readLine()) != null; i++){
						lp = line.split(" ");
						leaderboard.add(new LeaderboardPosition(lp[0], Integer.parseInt(lp[1]), lp[2]));
					}
					leaderboards.add(leaderboard);
				}
			}finally{
				br.close();
			}
		}
	}
	
	public void saveData() throws IOException{
		File file = new File(variablesPath);
		BufferedWriter bw = null;
		
		try{
			bw = new BufferedWriter(new FileWriter(file));
			
			bw.write(x);
			bw.newLine();
			bw.write(y);
			bw.newLine();
			bw.write(mines);
			bw.newLine();
			
			for(int i = 0; i<leaderboards.size() && i<5; i++){
				bw.write(leaderboards.get(i).toString());
				bw.newLine();
			}
		}finally{
			bw.flush();
			bw.close();
		}
		
	}
	
	public static int getX(){
		return x;
	}
	
	public static int getY(){
		return y;
	}
	
	public static int getMines(){
		return mines;
	}
	
	public static int getButtonSize(){
		return buttonSize;
	}
	
	public static Dimension getDimension(){
		return dimension;
	}
	
	public static void setDifficulty(Difficulty d){
		
		switch(d){
			case BEGINNER:
				y = x = 9;
				mines = 10;
				break;
			case INTERMEDIATE:
				y = x = 16;
				mines = 40;
				break;
			case ADVANCED:
				x = 16;
				y = 30;
				mines = 99;
				break;
		}
	}
	
	public static void setDifficulty(int x, int y, int mines){
		SavedVariables.x = x;
		SavedVariables.y = y;
		SavedVariables.mines = mines;
	}
	
	public static List<LeaderboardPosition> getLeaderboard(Difficulty d){
		
		List<LeaderboardPosition> leaderboard = null;
		
		switch(d){
			case BEGINNER:
				leaderboard = leaderboards.get(0);
				break;
			case INTERMEDIATE:
				leaderboard = leaderboards.get(1);
				break;
			case ADVANCED:
				leaderboard = leaderboards.get(2);
				break;
		}
		
		return leaderboard.size() <=6 ? leaderboard : leaderboard.subList(0, 5);
	}

}
