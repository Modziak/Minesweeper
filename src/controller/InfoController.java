package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Flags;
import model.SavedVariables;
import model.Timer;
import view.InfoPanel;

public class InfoController implements InfoListener{

	private static InfoController controller;
	private StartListener startListener;
	private InfoPanel panel;
	private Thread timer;
	private int minesCount;
	private int tilesCount;
	
	private InfoController(InfoPanel panel, StartListener l){
		this.panel = panel;
		this.startListener = l;
		addListeners();
	}
	
	public static InfoController init(InfoPanel panel, StartListener l){
		return controller != null ? controller 
				: (controller = new InfoController(panel, l));
	}
	
	private void addListeners(){
		panel.addRestartListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Flags.isStarted()){
					Flags.setStarted(false);
					stopTimer();
					startListener.restart();
				}
				
			}
		});
	}

	@Override
	public void startTimer() {
		panel.updateMinesCount(minesCount = SavedVariables.getMines());
		tilesCount = SavedVariables.getX()*SavedVariables.getY() - SavedVariables.getMines();
		Timer t = new Timer();
		t.addInfoListener(this);
		timer = new Thread(t);
		timer.start();
		
	}

	@Override
	public void updateTimer(int time) {
		panel.updateTimer(time);
		
	}

	@Override
	public void updateMinesCount(int minesChange) {
		minesCount += minesChange;
		panel.updateMinesCount(minesCount);
		
	}

	@Override
	public void stopTimer() {
		timer.interrupt();
	}

	@Override
	public void updateTilesCount(int tilesRevealed) {
		tilesCount -= tilesRevealed;
		if(tilesCount == 0) startListener.playerWon();
	}
	
	
}
