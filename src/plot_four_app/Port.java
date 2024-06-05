package plot_four_app;

import javafx.scene.Scene; //Needed to work with content accessed via the Port
import contexts.StartMenu;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Port extends Scene {
	
	final private StartMenu startMenu = new StartMenu();

	public class Controller {
		
		private final Scene scene = Port.this;
		public Parent[] contexts = null;
		
	}
	
	Controller controller = new Controller();
	
	public Port() {
		super(new StartMenu(), 1000, 1000, Color.BLACK);
		this.controller.contexts = new Parent[1];
		
		
	}
	
	private void setContexts() {
		
	}
}
