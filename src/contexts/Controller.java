package contexts;

import javafx.scene.Parent;
import plot_four_app.Port;
public class Controller {
	
	public Port port = null;
	public Parent[] contexts = null;
	
	public void changeContext() {
		port.setRoot(contexts[1]);
	}
}
