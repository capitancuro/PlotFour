package plot_four_app;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class Controller {
	
	public Scene scene = null;
	public Parent[] contexts = null;
	
	public void changeContext() {
		scene.setRoot(contexts[1]);
	}
}
