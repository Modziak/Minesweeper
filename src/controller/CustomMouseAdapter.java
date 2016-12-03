package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonModel;
import javax.swing.JToggleButton;

import model.Flags;
import view.CustomButton;

public class CustomMouseAdapter extends MouseAdapter{
	
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
			if(evt.getButton() == 3 && button.isEnabled()) button.setNextState();
		}
		else pressNeighbours(Flags.getLastEntered());
		
		super.mousePressed(evt);
	}
	
	@Override
	public void mouseReleased(MouseEvent evt){
		
		CustomButton button = Flags.getLastEntered();
		
		if(button != null){
			if(!Flags.bothPressed()){
				if(evt.getButton() == 1 && button.isEnabled() && !button.isFlagged()){
					button.doClick();
					button.setStateZero();
					button.setEnabled(false);
					Flags.setLastEntered(null);	
				}
			}
			else if(Flags.bothPressed()){
				clearNeigbours(Flags.getLastEntered());
			}
		}
		
		Flags.setButtonPressed(evt.getButton(), false);
		
	}

	
	@Override
	public void mouseEntered(MouseEvent evt){
		
		CustomButton button = (CustomButton) evt.getComponent();
		ButtonModel model = button.getModel();
		
		Flags.setLastEntered(button);
		
		if(Flags.isButton1Pressed()){
			model.setPressed(true);
			model.setArmed(true);
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
		for(CustomButton b : buttons) b.setState(true);
	}
	
	private void clearNeigbours(CustomButton button){
		ArrayList<CustomButton> buttons = button.getNeighbours();
		button.setState(false);
		for(CustomButton b : buttons) b.setState(false);
	}
}

