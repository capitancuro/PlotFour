package contexts;


import assets.AssetsManager;


import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;


public class StartMenu extends VBox {
	private final AssetsManager assets = new AssetsManager();
	private final AssetsManager assets1 = new AssetsManager();
	
	private final Image plot_four_icon = new Image("/assets/Plot_Four.png");
	private final ImageView plot_four_view = new ImageView(plot_four_icon);
	private Text plot_four_logo = new Text("PLOT FOUR");
	private final Button startButton = new Button("START");
	
	public StartMenu() {
		this.setAlignment(this.getAlignment().CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		this.setSpacing(25);
		
		plot_four_view.setFitWidth(200);
		plot_four_view.setFitHeight(100); 	
		
		plot_four_logo.setFont(Font.loadFont(assets.font, 32));
		plot_four_logo.setFill(Color.WHITE);
		
		startButton.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 16));
		startButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		startButton.setTextFill(Color.WHITE);
		
		this.getChildren().add(plot_four_view);
		this.getChildren().add(plot_four_logo);
		this.getChildren().add(startButton);
	}
	
}
