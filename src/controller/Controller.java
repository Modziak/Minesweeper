package controller;

import java.awt.event.MouseAdapter;

import view.*;
import model.*;

public class Controller {

	Model model;
	MainFrame frame;
	
	public Controller(Model model, MainFrame frame){
		this.model = model;
		this.frame = frame;
		frame.setVisible(true);
		
		addListeners();
	}
	
	public void addModel(Model model){
		this.model = model;
	}
	
	public void addFrame(MainFrame frame){
		this.frame = frame;
	}
	
	public void addListeners(){
		
		MouseAdapter adapter = new CustomMouseAdapter();
		frame.addListeners(adapter);
		
	}
}
