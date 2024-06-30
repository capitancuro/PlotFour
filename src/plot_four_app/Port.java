package plot_four_app;

import java.util.HashMap;

import assets.AssetsManager;

import javafx.scene.Scene; //Needed to work with content accessed via the Port
import javafx.scene.Parent; //Needed to store subclasses of parent
import javafx.scene.paint.Color; //Needed to set color of the root node

import contexts.Controller; //Needed to control interactions between different contexts
import contexts.PlotFour;
import contexts.StartMenu;
import contexts.PauseMenu;

//The scene used to store different contexts of the Plot Four app
public class Port extends Scene {
	
	private double width = 0;
	private double height = 0;
	
	public AssetsManager assetsManager = null;
	public Controller controller = null;

	public Port(double width, double height, AssetsManager assetsManager, Controller controller) {
		super(new StartMenu(assetsManager, controller), width, height, Color.BLACK);
		
		this.width = width;
		this.height = height;
		
		this.assetsManager = assetsManager; 
		this.controller = controller;
		
		controller.contexts = new HashMap<String, Parent>();
		setContexts();
	}
	
	//Creates an instance of every context used in the app
	public void setContexts() {
		controller.contexts.put("Start Menu", getRoot());
		controller.contexts.put("Pause Menu", new PauseMenu(assetsManager, controller));
		controller.contexts.put("Plot Four", new PlotFour(width, height, assetsManager, controller));
	}
}
