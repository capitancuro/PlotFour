package contexts;

import assets.AssetsManager;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;

public class StartMenu extends VBox {
	
	public AssetsManager assetsManager = null;
	public Controller controller = null;

	
	private final Image plot_four_icon = new Image("/assets/Plot_Four.png");
	private final ImageView plot_four_view = new ImageView(plot_four_icon);
	
	private Text plot_four_title = new Text("PLOT FOUR");
	
	private final Button startButton = new Button("START");
	
	public StartMenu(AssetsManager assetsManager, Controller controller) {
		this.assetsManager = assetsManager;
		this.controller = controller;
		
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		this.setSpacing(25);
		
		plot_four_view.setFitWidth(200);
		plot_four_view.setFitHeight(100); 	
		
		plot_four_title.setFont(Font.loadFont(assetsManager.getFont(), 32));
		plot_four_title.setFill(Color.WHITE);
		
		startButton.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 16));
		startButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		startButton.setTextFill(Color.WHITE);
		
		startButton.setOnMouseClicked(event -> {
			controller.changeContext("Plot Four");
			PlotFour plot_four = (PlotFour) this.controller.contexts.get("Plot Four");
			plot_four.startGame();
		});
		
		startButton.setOnMouseEntered(event -> {
			startButton.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			startButton.setTextFill(Color.BLACK);
		});
		
		startButton.setOnMouseExited(event -> {
			startButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			startButton.setTextFill(Color.WHITE);
		});
		
		this.getChildren().add(plot_four_view);
		this.getChildren().add(plot_four_title);
		this.getChildren().add(startButton);
	}
}
