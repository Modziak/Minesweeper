package view;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	private CustomButton[][] buttons;
	private int x = 16;
	private int y = 30;
	private JPanel panel;
	
	public MainFrame(){
		super("Minesweeper");
		
		panel = new JPanel();
		GridLayout layout = new GridLayout(x, y, 0, 0);
		panel.setLayout(layout);
		populate();
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		
	}
	
	/*
	 * Zmieniæ sposób podawania wartoœci range dla CustomButton
	 * Umo¿liæ zmianê liczby kafelków
	 * Wyeliminowaæ koniecznoœæ dzielenia za ka¿dym razem : DONE
	 */ 
	public void populate(){
		
		buttons = new CustomButton[this.x][this.y];
		double tempValue = (255/46)/2;
		
		panel.add(buttons[0][0] = new CustomButton(0,0,tempValue));
		
		for(int y=1; y<this.y; y++){
			panel.add(buttons[0][y] = new CustomButton(0,y,tempValue));
			buttons[0][y].addNeighbour(buttons[0][y-1].addNeighbour(buttons[0][y]));
		}
		
		CustomButton button;
		
		for(int x=1; x<this.x; x++){
			for(int y=0; y<this.y; y++){
				button = new CustomButton(x,y,tempValue);
				panel.add(buttons[x][y] = button);
				
				button.addNeighbour(buttons[x-1][y].addNeighbour(button));
				if(y!=0){
					button.addNeighbour(buttons[x-1][y-1].addNeighbour(button));
					button.addNeighbour(buttons[x][y-1].addNeighbour(button));
				}
				if(y < this.y-1){
					button.addNeighbour(buttons[x-1][y+1].addNeighbour(button));
				}
				
			}
		}
	}
	
	public void addListeners(MouseAdapter adapter){
		for(int x=0; x<this.x; x++){
			for(int y=0; y<this.y; y++){
				buttons[x][y].addMouseListener(adapter);
			}
		}
	}

}
