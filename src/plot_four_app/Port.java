package plot_four_app;

import java.util.HashMap;

import assets.AssetsManager;

import javafx.scene.Scene; //Needed to work with content accessed via the Port
import contexts.Controller;
import contexts.PlotFour;
import contexts.StartMenu;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

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
	
	private void setContexts() {
		controller.contexts.put("Start Menu", getRoot());
		controller.contexts.put("Plot Four", new PlotFour(width, height, assetsManager, controller));
	}
}
