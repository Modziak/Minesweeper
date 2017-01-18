package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JPanel{
	
	private static InfoPanel panel;
	
	private Font font1 = new Font("SansSerif", Font.BOLD, 20);
	private JTextField timerField;
	private JButton restartButton;
	private JTextField mineCountField;
	
	private InfoPanel(){
		setLayout(new MigLayout("",
								"[grow][grow][grow]",
								"[]"));
		timerField = new JTextField();
		timerField.setColumns(3);
		timerField.setFont(font1);
		timerField.setBackground(Color.WHITE);
		timerField.setHorizontalAlignment(JTextField.CENTER);
		timerField.setEditable(false);
		timerField.setFocusable(false);
		timerField.setText("0");
		add(timerField, "cell 0 0, center");
		
		restartButton = new JButton("Restart");
		add(restartButton, "cell 1 0, center");
		
		mineCountField = new JTextField();
		mineCountField.setColumns(3);
		mineCountField.setFont(font1);
		mineCountField.setBackground(Color.white);
		mineCountField.setHorizontalAlignment(JTextField.CENTER);
		mineCountField.setEditable(false);
		mineCountField.setFocusable(false);
		add(mineCountField, "cell 2 0, center");
		
	}
	
	public static InfoPanel getPanel(){
		return panel != null ? panel : (panel = new InfoPanel());
	}
	
	public void updateTimer(int time){
		timerField.setText(Integer.toString(time));
	}
	
	public void updateMinesCount(int mines){
		mineCountField.setText(Integer.toString(mines));
	}
	
	public void addRestartListener(ActionListener l){
		restartButton.addActionListener(l);
	}
	
	

}
