package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import model.SavedVariables;

public class ButtonPanel extends JPanel{
	
	private static ButtonPanel panel;
	
	private int x, y;
	private CustomButton[][] buttons;
	private Dimension dimension;
	
	private ButtonPanel(){
		this.x = SavedVariables.getX();
		this.y = SavedVariables.getY();
		
		setLayout(new GridLayout(x,y,0,0));
		dimension = new Dimension(y*SavedVariables.getButtonSize(), 
								  x*SavedVariables.getButtonSize());
		populate();
	}
	
	public static ButtonPanel getPanel(){
		return panel != null ? panel : (panel = new ButtonPanel());
	}
	
	public void destroyPanel(){
		panel = null;
	}
	
	public CustomButton[][] getButtons(){
		return buttons;
	}

	/*
	 * Zmieniæ sposób podawania wartoœci range dla CustomButton
	 * Umo¿liæ zmianê liczby kafelków
	 * Wyeliminowaæ koniecznoœæ dzielenia za ka¿dym razem : DONE
	 */ 
	private void populate(){
		
		buttons = new CustomButton[this.x][this.y];
		double tempValue = (255/46)/2;
		
		add(buttons[0][0] = new CustomButton(0,0,tempValue));
		
		for(int y=1; y<this.y; y++){
			add(buttons[0][y] = new CustomButton(0,y,tempValue));
			buttons[0][y].addNeighbour(buttons[0][y-1].addNeighbour(buttons[0][y]));
		}
		
		CustomButton button;
		
		for(int x=1; x<this.x; x++){
			for(int y=0; y<this.y; y++){
				button = new CustomButton(x,y,tempValue);
				add(buttons[x][y] = button);
				
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
	
	public void addListeners(MouseAdapter adapter, ChangeListener listener){
		for(int x=0; x<this.x; x++){
			for(int y=0; y<this.y; y++){
				buttons[x][y].addMouseListener(adapter);
//				buttons[x][y].addChangeListener(listener);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}
	
	@Override
	public Dimension getMaximumSize() {
		return dimension;
	}
	
	@Override
	public Dimension getMinimumSize() {
		return dimension;
	}

}
