package view;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1585407456367611962L;
	private static MainFrame frame;
	
	private MainFrame(){
		super("Minesweeper");
		
		setLayout(new MigLayout("",
								"[fill]",
								"[fill][fill]"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public static MainFrame getFrame(){
		return frame != null ? frame : (frame = new MainFrame());
	}
	
	public void showFrame(){
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	

}
