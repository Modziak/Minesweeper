package controller;

import java.util.ArrayList;

import view.CustomButton;

public interface Tile {
	void setAsMine(boolean flag);
	void incrementMineCount();
	boolean isMine();
	ArrayList<CustomButton> getNeighbours();
}
