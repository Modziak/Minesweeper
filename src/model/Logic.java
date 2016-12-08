package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JToggleButton;

import controller.ChangeButtonListener;
import controller.Tile;
import view.CustomButton;

public class Logic {
	
	private static Logic logic;
	private ChangeButtonListener listener;
	
	private Tile[][] tiles;
	private int[][] mines;
	private int minesCount;
	
	private Logic(){}
	
	public static Logic getInstance(){
		return logic != null ? logic : (logic = new Logic());
	}
	
	public void generateMines(){
		Random rand = new Random();
		
		int x = SavedVariables.getX();
		int y = SavedVariables.getY();
		int minesCount = SavedVariables.getMines();
		int tilesCount = x*y;
		int tileNumber, mineX, mineY;
		
		List<Integer> randList = new ArrayList<Integer>(tilesCount);
		for(int i=0; i<tilesCount; i++) randList.add(i);
		
		for(int i=0; i<minesCount; i++){
			tileNumber = randList.remove(rand.nextInt(tilesCount-i));
			mineX = tileNumber / y;
			mineY = tileNumber % y;
			tiles[mineX][mineY].setAsMine(true);
			for(Tile tile : tiles[mineX][mineY].getNeighbours()){
				tile.incrementMineCount();
			}
		}
		
	}
	
	public void checkTile(CustomButton tile){
		if(tile.isMine()){
			
		}
		else{
			tile.revealNumber();
		}
	}
	
	public void setButtons(CustomButton[][] buttons){
		this.tiles = buttons;
	}

	public void addChangeButtonListener(ChangeButtonListener listener){
		this.listener = listener;
	}
}
