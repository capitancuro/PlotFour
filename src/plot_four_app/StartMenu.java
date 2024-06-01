package plot_four_app;

import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class StartMenu extends VBox {
	
	final Button startButton = new Button("START");
	
	public StartMenu() {
		this.setAlignment(this.getAlignment().CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
		startButton.setStyle("-fx-border-color: #fff; -fx-border-width: 4px; -fx-background-color: #000; -fx-text-fill: #fff");
		this.getChildren().add(startButton);
	}
	
}
