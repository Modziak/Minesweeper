import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ButtonsController;
import model.Logic;
import model.SavedVariables;
import view.ButtonPanel;
import view.MainFrame;


public class Main {
	
	public static void main(String[] args){
		MainFrame frame = MainFrame.getFrame();
		try {
			SavedVariables.init().loadData();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, 
					"Save file was corrupted.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, 
					"Something went wrong. Not my fault.");
		}
		
		ButtonsController.init(Logic.getInstance(), ButtonPanel.getPanel());
		frame.add(ButtonPanel.getPanel(), "cell 0 0");
		
		frame.showFrame();
	}
}
