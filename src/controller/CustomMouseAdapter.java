package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonModel;
import javax.swing.JToggleButton;

import exceptions.MineRevealed;
import model.Flags;
import view.CustomButton;

public class CustomMouseAdapter extends MouseAdapter{
	
	private StartListener startListener;
	private InfoListener infoListener;
	
	public void addStartListener(StartListener listener){
		startListener = listener;
	}
	
	public void addInfoListener(InfoListener listener){
		infoListener = listener;
	}
	
	/*
	@Override
	public void mouseClicked(MouseEvent arg0){
		System.out.println("I was clicked");
	}
	*/
	
	@Override
	public void mousePressed(MouseEvent evt){
		
		CustomButton button = (CustomButton)evt.getComponent();
		Flags.setButtonPressed(evt.getButton(), true);
		
		if(!Flags.bothPressed()){
			Flags.setLastEntered(button);
			if(evt.getButton() == 3 && button.isEnabled()){
				int state = button.setNextState();
				infoListener.updateMinesCount(state == 1 ? -1 : state == 2 ? 1 : 0);
			}
		}
		else pressNeighbours(Flags.getLastEntered());
		
		super.mousePressed(evt);
	}
	
	@Override
	public void mouseReleased(MouseEvent evt){
		
		CustomButton button = Flags.getLastEntered();
		try{
			if(button != null){
				if(!Flags.isStarted()) startListener.generateBoard(button);
				if(!Flags.bothPressed()){
					if(evt.getButton() == 1 && button.isEnabled() && !button.isFlagged()){
						/*
						 * Najbardziej ³opatologiczne rozwiazanie
						 */
						button.setEnabled(false);
						button.reset();
						button.revealNumber();
						infoListener.updateTilesCount(1);
						if(button.isEmpty()){
							infoListener.updateTilesCount(revealNeighbours(Flags.getLastEntered()));
						}
						Flags.setLastEntered(null);	
					}
				}
				else if(Flags.bothPressed()){
					if(button.checkFlags()){
						infoListener.updateTilesCount(revealNeighbours(Flags.getLastEntered()));
					}
					clearNeigbours(Flags.getLastEntered());
					
				}
			}
			Flags.setButtonPressed(evt.getButton(), false);
		}catch(MineRevealed mine){
			startListener.playerLost();
		}	
	}

	@Override
	public void mouseEntered(MouseEvent evt){
		
		CustomButton button = (CustomButton) evt.getComponent();
		ButtonModel model = button.getModel();
		
		Flags.setLastEntered(button);
		
		if(Flags.isButton1Pressed()){
			button.setState(true);
//			model.setPressed(true);
//			model.setArmed(true);
		}
		if(Flags.bothPressed()) pressNeighbours(button); 
		
		
	}
	
	@Override
	public void mouseExited(MouseEvent evt){
		
		if(Flags.bothPressed()) clearNeigbours(Flags.getLastEntered());
		Flags.setLastEntered(null);
	}
	
	
	/*
	 * Mo¿na zredukowaæ
	 * - Dziêki of future me: bardzo pomocny komentarz...
	 */
	private void pressNeighbours(CustomButton button){
		ArrayList<CustomButton> buttons = button.getNeighbours();
		button.setState(true);
		for(CustomButton b : buttons){
			if(!b.isFlagged()) b.setState(true);
		}
	}
	
	private void clearNeigbours(CustomButton button){
		ArrayList<CustomButton> buttons = button.getNeighbours();
		for(CustomButton b : buttons) b.reset();
	}
	
	private int revealNeighbours(CustomButton button) throws MineRevealed{
		int tilesRevealed = 0;
		for(CustomButton b : button.getNeighbours()){
			if(b.isEnabled() && !b.isFlagged()){
				b.revealNumber();
				b.setEnabled(false);
				tilesRevealed++;
				if(b.isEmpty()) tilesRevealed += revealNeighbours(b);
			}
		}
		
		return tilesRevealed;
	}

}

