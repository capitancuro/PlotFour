package plot_four_app;

//JAVA FX DEPENDENCIES
import javafx.application.Application; //Class needed for the Application.
import javafx.stage.Stage; //Class needed for window(s) of the application.

//PLOT FOUR CLASSES
import plot_four_app.*;

public class PlotFourApp extends Application {
	
	final Port port = new Port();
	
	public PlotFourApp() {
	}
	
	@Override
	public void start(Stage window){
		window.setTitle("Plot Four");
		window.setScene(port);
		window.show();
	}
	
	public static void main (String[] args) {
		Application.launch(args);
    }  
  
}  
