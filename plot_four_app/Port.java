package plot_four_app;

import javafx.scene.Scene; //Needed to work with content accessed via the Port
import javafx.scene.Parent;
import javafx.scene.control.Button;

import plot_four_app.StartMenu;

public class Port extends Scene {
	

	public class Controller {
		
		private final Scene scene = null;
		private final Parent[] contexts = null;
		
		public Controller(Scene scene) {
		}
	}
	
	public Port() {
		super(new StartMenu(), 1000, 1000);
	}

}
