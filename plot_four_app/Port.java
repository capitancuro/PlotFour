package plot_four_app;

import javafx.scene.Scene; //Needed to work with content accessed via the Port
import javafx.scene.paint.Color;


import plot_four_app.StartMenu;

public class Port extends Scene {
	
	public Port() {
		super(new StartMenu(), 1000, 1000, Color.rgb(0,0, 0));
	}

}
