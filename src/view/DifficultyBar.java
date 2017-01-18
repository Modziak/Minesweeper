package view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DifficultyBar extends JMenuBar{
	
	private static DifficultyBar menuBar;
	
	private JMenu difficultyMenu;
	private JMenuItem begginer;
	private JMenuItem intermediate;
	private JMenuItem advanced;
	
	private DifficultyBar(){
		difficultyMenu = new JMenu("Poziom trudnoœci");
		
		difficultyMenu.add(begginer = new JMenuItem("Pocz¹tkuj¹cy"));
		difficultyMenu.add(intermediate = new JMenuItem("Œredni"));
		difficultyMenu.add(advanced = new JMenuItem("Zaawansowany"));
		
		add(difficultyMenu);
		
	}
	
	public static DifficultyBar getMenuBar(){
		return menuBar != null ? menuBar : (menuBar = new DifficultyBar());
	}
	
	public void addListeners(ActionListener[] l){
		begginer.addActionListener(l[0]);
		intermediate.addActionListener(l[1]);
		advanced.addActionListener(l[2]);
	}
	
	

}
