import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ButtonsController;
import controller.InfoController;
import controller.MenuController;
import model.Logic;
import model.SavedVariables;
import view.ButtonPanel;
import view.DifficultyBar;
import view.InfoPanel;
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
		
		ButtonsController c = ButtonsController.init(Logic.getInstance(), ButtonPanel.getPanel());
		c.addInfoListener(InfoController.init(InfoPanel.getPanel(), c));
		MenuController c2 = MenuController.init(DifficultyBar.getMenuBar());
		c2.addStartListener(c);
		frame.setJMenuBar(DifficultyBar.getMenuBar());
		frame.add(ButtonPanel.getPanel(), "cell 0 0");
		frame.add(InfoPanel.getPanel(), "cell 0 1");
		
		frame.showFrame();
	}
}
