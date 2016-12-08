package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Logic;
import view.ButtonPanel;
import view.CustomButton;

public class ButtonsController implements ChangeButtonListener{
	
	private static ButtonsController controller;
	
	private Logic logic;
	private ButtonPanel panel;

	private ButtonsController(Logic logic, ButtonPanel panel){
		this.logic = logic;
		this.panel = panel;
		
		logic.setButtons(panel.getButtons());
		logic.generateMines();
		addListeners();
	}
	
	public static ButtonsController init(Logic logic, ButtonPanel panel){
		return controller != null ? controller 
				: (controller = new ButtonsController(logic, panel));
	}
	
	/*
	 * Niepotrzebny aktualnie: ChangeButtonListener i ButtonChangeListener 
	 */
	private void addListeners(){
		logic.addChangeButtonListener(this);
		panel.addListeners(new CustomMouseAdapter(), new ButtonChangeListener());
	}
	
	@Override
	public void pressButton(List<int[]> buttons) {
		// TODO Auto-generated method stub
		
	}
	
	class ButtonChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			logic.checkTile((CustomButton) e.getSource());	
		}
		
	}

}
