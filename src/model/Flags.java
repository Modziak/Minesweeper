package model;

import javax.swing.ButtonModel;
import javax.swing.JToggleButton;

import view.CustomButton;

public final class Flags {

	private Flags(){}
	
	private static boolean IS_STARTED = false;
	private static boolean BUTTON1_PRESSED = false;
	private static boolean BUTTON3_PRESSED = false;
	private static boolean BOTH_PRESSED = false;
	private static ButtonModel STORRED_MODEL;
	private static CustomButton LAST_ENTERED;
	
	
	public static void setButtonPressed(int buttonNumber, boolean b){
		
		switch (buttonNumber) {
			case 1: BUTTON1_PRESSED = b;
					BOTH_PRESSED = BUTTON3_PRESSED;
					break;
			case 3: BUTTON3_PRESSED = b;
					BOTH_PRESSED = BUTTON1_PRESSED;
					break;
			default: break; 
		}
	}
	
	public static boolean isButton1Pressed(){
		return BUTTON1_PRESSED;
	}
	
	public static boolean isButton3Pressed(){
		return BUTTON3_PRESSED;
	}
	
	public static boolean bothPressed(){
		return BOTH_PRESSED;
	}
	
	public static void setModel(ButtonModel model){
		STORRED_MODEL = model;
	}

	public static ButtonModel getModel(){
		return STORRED_MODEL;
	}
	
	public static void setLastEntered(CustomButton button){
		LAST_ENTERED = button;
	}
	
	public static CustomButton getLastEntered(){
		return LAST_ENTERED;
	}
	
	public static boolean isStarted(){
		return IS_STARTED;
	}
	
	public static void setStarted(boolean isStarted){
		IS_STARTED = isStarted;
	}
}
