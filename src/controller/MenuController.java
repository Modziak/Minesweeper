package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SavedVariables;
import view.DifficultyBar;

public class MenuController {

	private static MenuController controller;
	
	private DifficultyBar menuBar;
	private StartListener startListener;
	
	private MenuController(DifficultyBar menuBar){
		this.menuBar = menuBar;
		addListeners();
	}
	
	public static MenuController init(DifficultyBar menuBar){
		return controller != null ? controller : (controller = new MenuController(menuBar));
	}
	
	public void addStartListener(StartListener l){
		startListener = l;
	}
	
	private void addListeners(){
		ActionListener[] l = new ActionListener[3];
		
		l[0] = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SavedVariables.setDifficulty(SavedVariables.Difficulty.BEGINNER);
				startListener.restart();
			}
		};
		
		l[1] = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SavedVariables.setDifficulty(SavedVariables.Difficulty.INTERMEDIATE);
				startListener.restart();
			}
		};
		
		l[2] = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SavedVariables.setDifficulty(SavedVariables.Difficulty.ADVANCED);
				startListener.restart();
			}
		};
		
		menuBar.addListeners(l);
	}
	
	
}
