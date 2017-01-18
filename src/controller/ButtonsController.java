package controller;

import javax.swing.JOptionPane;

import model.Flags;
import model.Logic;
import view.ButtonPanel;
import view.CustomButton;
import view.MainFrame;

public class ButtonsController implements StartListener{
	
	private static ButtonsController controller;
	
	private Logic logic;
	private ButtonPanel panel;
	private InfoListener info;
	private CustomMouseAdapter adapter;

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
//		logic.addChangeButtonListener(this);
		
		adapter = new CustomMouseAdapter();
		adapter.addStartListener(this);
		panel.addListeners(adapter);
	}
	
	public void addInfoListener(InfoListener listener){
		info = listener;
		adapter.addInfoListener(listener);
	}
	
	@Override
	public void generateBoard(CustomButton button) {
		Flags.setStarted(true);
		logic.setButtons(panel.getButtons());
		logic.generateMines(button);
		info.startTimer();
	}

	@Override
	public void playerLost() {
		info.stopTimer();
		panel.disableAll();
		JOptionPane.showMessageDialog(MainFrame.getFrame(), 
				"Przegra³eœ. Spróbuj jeszcze raz.", 
				"", 
				JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void playerWon() {
		info.stopTimer();
		panel.disableAll();
		JOptionPane.showMessageDialog(MainFrame.getFrame(), 
				"Wygra³eœ!", 
				"", 
				JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void restart() {
		
		if(Flags.isStarted()) info.stopTimer();
		Flags.resetAll();
		MainFrame.getFrame().remove(panel);
		panel = ButtonPanel.createNewPanel();
		panel.addListeners(adapter);
		MainFrame.getFrame().add(panel, "cell 0 0");
		panel.revalidate();
		panel.repaint();
		MainFrame.getFrame().pack();
		
	}
}
