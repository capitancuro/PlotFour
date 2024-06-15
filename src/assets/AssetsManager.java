package assets;

import java.io.InputStream;

public class AssetsManager {
	
	public InputStream font = null;
	
	public AssetsManager() {
		font = AssetsManager.class.getResourceAsStream("PressStart2P-Regular.ttf");
	}
	
	public InputStream getFont() {
		return AssetsManager.class.getResourceAsStream("PressStart2P-Regular.ttf");
	}
}
