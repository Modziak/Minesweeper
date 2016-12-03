import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import model.Model;
import view.MainFrame;


public class Main {

	public Main(){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run(){
				
				/*
				try{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
				
				*/
				MainFrame frame = new MainFrame();
				Model model = new Model();
				
				Controller controller = new Controller(model, frame);
				
			}
		});
	}
	
	public static void main(String[] args){
		Main main = new Main();
	}
}
