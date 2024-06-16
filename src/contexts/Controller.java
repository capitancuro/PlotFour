package contexts;

import java.util.HashMap;

import javafx.scene.Parent;
import plot_four_app.Port;
public class Controller {
	
	public Port port = null;
	public HashMap<String, Parent> contexts = null;
	
	public void changeContext() {
		port.setRoot(contexts.get("Plot Four"));
	}
}
