package contexts;

import assets.AssetsManager;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class PauseMenu extends VBox {
	
	AssetsManager assetsManager = null;
	Controller controller = null;
	
	private Button home = null;
	private Button back = null;
	
	public PauseMenu(AssetsManager assetsManager, Controller controller) {
		
		this.assetsManager = assetsManager;
		this.controller = controller;
		
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		setSpacing(25);
		
		home = new Button("HOME");
		home.setFont(Font.loadFont(assetsManager.getFont(), 15));
		home.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		home.setTextFill(Color.WHITE);
		
		home.setOnMouseClicked(event -> {
			controller.changeContext("Start Menu");
			
			controller.port.setOnMouseMoved(null);
			
			controller.port.setOnMouseDragged(null);
			
			controller.port.setOnMouseClicked(null);
			
			controller.contexts.clear();
			
			controller.port.setContexts();
		});
		
		home.setOnMouseEntered(event -> {
			home.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			home.setTextFill(Color.BLACK);
		});
		
		home.setOnMouseExited(event -> {
			home.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			home.setTextFill(Color.WHITE);
		});
		
		back = new Button("BACK");
		back.setFont(Font.loadFont(assetsManager.getFont(), 15));
		back.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		back.setTextFill(Color.WHITE);
		
		back.setOnMouseClicked(event -> {
			controller.changeContext("Plot Four");
		});
		
		back.setOnMouseEntered(event -> {
			back.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			back.setTextFill(Color.BLACK);
		});
		
		back.setOnMouseExited(event -> {
			back.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			back.setTextFill(Color.WHITE);
		});
		
		getChildren().add(home);
		getChildren().add(back);
	}

}
