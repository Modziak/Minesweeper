package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import controller.Tile;
import model.SavedVariables;

public class CustomButton extends JToggleButton implements Tile{
	
	/*
	 * GUI variables
	 */
	
	private int x;
	private int y;
	private double range;
	/*
	 * currState:
	 * 0 - CLEAR
	 * 1 - FLAGGED
	 * 2 - MARKED
	 */
	private int currState;

	private int rectSize;
	private ArrayList<CustomButton> neighbours;
	
	private static final int TRANSPARENT = 0;
	private static final int HALF_TRANSPARENT = 127;
	private static final int OPAQUE = 255;
	
	private static final Color[] colors = new Color[]{
			Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.CYAN, Color.ORANGE, Color.MAGENTA, Color.DARK_GRAY
	};

	/*
	 * Logic variables
	 */
	
	private boolean isMine;
	private int minesAround = 0;
	
	public CustomButton(int x, int y, double range){
		super();
		
		this.x = x;
		this.y = y;
		this.range = range;
		this.currState = 0;
		neighbours = new ArrayList<CustomButton>();
		
		rectSize = SavedVariables.getButtonSize();
		
		setMargin(new Insets(0,0,0,0));
		setIcons();
		setRolloverEnabled(true);
		setBorderPainted(false);
		
	}
	
	public void reset(){
		setState(true);
		setState(false);
	}
	
	public void setState(boolean state){
		if(isEnabled()){
			ButtonModel model = getModel();
			model.setPressed(state);
			model.setArmed(state);
		}
	}
	
	
	public int getRelativeX(){
		return x;
	}
	
	public int getRelativeY(){
		return y;
	}
	
	public void setNextState(){
		currState += currState != 2 ? 1 : -2;
		System.out.println(currState);
		setIcons();
	}
	
	public void setStateZero(){
		currState = 0;
		setIcons();
	}
	
	public boolean isFlagged(){
		return currState == 1;
	}
	
	public CustomButton addNeighbour(CustomButton button){
		neighbours.add(button);
		return this;
	}
	
	public ArrayList<CustomButton> getNeighbours(){
		return neighbours;
	}
	
	public void setAsMine(boolean flag){
		isMine = flag;
	}
	
	public void incrementMineCount(){
		minesAround++;
	}
	
	public boolean isMine(){
		return isMine;
	}
	
	public void revealNumber(){
		setHorizontalTextPosition(SwingConstants.CENTER);
		if(!isMine){
			if(minesAround != 0){
	//			setForeground(colors[minesAround]);
				setForeground(Color.RED);
				setText(Integer.toString(minesAround));
			}
		}
		else setText("M");
	}
	
	/*
	@Override
	public boolean isEnabled(){
		return super.isEnabled() && currState != 1;
	}
	*/
	
	
	/*
	 * Metoda nie uwzglêdnia mo¿liwoœci zmiany wielkoœci kafelków
	 * Rozmiar: 25x25
	 * 
	 * Wszystkie kafelki wygl¹daj¹ tak samo: zmieniæ: DONE
	 * Podzieliæ kafelki na typy : DONE
	 * 
	 * Typ image jest RGB: dostosowaæ kod do ARGB : DONE 
	 *
	 */
	private void setIcons(){
		setIcon(createGraphics(OPAQUE));
		setRolloverIcon(createGraphics(HALF_TRANSPARENT));
		setPressedIcon(createGraphics(TRANSPARENT));
	}
	
	private ImageIcon createGraphics(int opacity){
		BufferedImage image = new BufferedImage(rectSize, rectSize, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		
		Point a = new Point(0,0);
		Point b = new Point(rectSize, rectSize);
		int rG = (int)(127 - (x+y) * range);
		
		Color colorA = new Color(rG + 127, rG + 127, 255, opacity);
		Color colorB = new Color(rG, rG, 255, opacity);
		
		g2.setPaint(new GradientPaint(a, colorA, b, colorB ));
		g2.fillRect(0, 0, 25, 25);
		
		
		switch (this.currState) {
		
		case 0:
			break;
		case 1:
			
			int r = rectSize/10;
			g2.setColor(Color.RED);
			g2.fillPolygon(new int[]{r, rectSize/2, rectSize/2}
						, new int[]{rectSize/4+r, r, rectSize/2+r}
						, 3);
			g2.setColor(Color.WHITE);
			g2.fillRect(rectSize/2, r, r, rectSize);
			g2.fillRect(rectSize/4, rectSize-r, rectSize/2, r);
			break;
		case 2:
			
			g2.setColor(Color.WHITE);
			g2.setFont(new Font(null, Font.PLAIN, rectSize - 5));
			
			FontMetrics fm = g2.getFontMetrics();
			int xPosition = (rectSize - fm.stringWidth("?"))/2;
            int yPosition = rectSize/2 + g2.getFont().getSize()/2;
            
			g2.drawString("?", xPosition, yPosition);
			break;
		}
		
		g2.dispose();
	
		return new ImageIcon(image);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return SavedVariables.getDimension();
	}
	
	@Override
	public Dimension getMaximumSize() {
		return SavedVariables.getDimension();
	}
	
	@Override
	public Dimension getMinimumSize() {
		return SavedVariables.getDimension();
	}
			
}
