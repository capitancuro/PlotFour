package plot_four_app;

import javafx.application.Application;												//Class needed for the Application
import javafx.stage.Stage;															//Class needed for window(s) of the application
import javafx.scene.Scene;															//Class needed to work with content accessed via the window.
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class PlotFourApp extends Application { 
	
	public PlotFourApp() {
	}
	
	@Override
	public void start(Stage window){
		window.setTitle("Plot Four");
		Scene home_scene = new Scene(new Group(), 1000, 1000, Color.rgb(0,0, 0));
		window.setScene(home_scene);
		window.show();
	}
	
	public static void main (String[] args) {
		Application.launch(args);
    }  
  
}  
