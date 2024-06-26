package contexts;

import assets.AssetsManager;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class PauseMenu extends VBox {
	
	AssetsManager assetsManager = null;
	Controller controller = null;
	
	private final Button home = null;
	private final Button back = null;
	
	public void PauseMenu(AssetsManager assetsManager, Controller controller) {
		
		this.assetsManager = assetsManager;
		this.controller = controller;
		
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		setSpacing(25);
	}

}
