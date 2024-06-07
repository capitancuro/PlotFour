package plot_four_app;

import assets.AssetsManager;

import javafx.scene.Scene; //Needed to work with content accessed via the Port
import contexts.StartMenu;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

import plot_four_app.PlotFour;
import plot_four_app.Controller;

public class Port extends Scene {
	
	public AssetsManager assetsManager = null;
	public Controller controller = null;
	
	public Port(AssetsManager assetsManager, Controller controller) {
		super(new StartMenu(assetsManager, controller), 1000, 1000, Color.BLACK);
		
		this.assetsManager = assetsManager;
		this.controller = controller;
		
		controller.contexts = new Parent[2];
		setContexts();
	}
	
	private void setContexts() {
		controller.contexts[0] = this.getRoot();
		controller.contexts[1] = new PlotFour();
	}
}
