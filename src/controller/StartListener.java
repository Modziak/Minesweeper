package controller;

import java.util.List;

import view.CustomButton;

public interface StartListener {

	void generateBoard(CustomButton button);
	void playerLost();
	void playerWon();
	void restart();
}
