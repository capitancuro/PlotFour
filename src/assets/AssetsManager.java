package assets;

import java.io.InputStream;
import java.io.File;  

public class AssetsManager {
	
	public InputStream getFont() {
		return AssetsManager.class.getResourceAsStream("PressStart2P-Regular.ttf");
	}
	
	public String getDropAudio() {
		return new File("audio").listFiles()[0].toURI().toString();
	}
}
