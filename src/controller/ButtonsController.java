package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Flags;
import model.Logic;
import view.ButtonPanel;
import view.CustomButton;

public class ButtonsController implements StartListener{
	
	private static ButtonsController controller;
	
	private Logic logic;
	private ButtonPanel panel;

	private ButtonsController(Logic logic, ButtonPanel panel){
		this.logic = logic;
		this.panel = panel;

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
		
		CustomMouseAdapter adapter = new CustomMouseAdapter();
		adapter.addStartListener(this);
		panel.addListeners(adapter, new ButtonChangeListener());
	}
	
	@Override
	public void generateBoard(CustomButton button) {
		Flags.setStarted(true);
		logic.setButtons(panel.getButtons());
		logic.generateMines(button);
		
	}
	
	class ButtonChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			logic.checkTile((CustomButton) e.getSource());	
		}
		
	}

}
