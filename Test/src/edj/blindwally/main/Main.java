package edj.blindwally.main;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import edj.blindwally.data.Map;
import edj.blindwally.data.Settings;


public class Main {

	/**
	 * @param args
	 * @throws LWJGLException 
	 */
	public static void main(String[] args) throws LWJGLException {
		Map scenario = new Map();
		Settings settings = new Settings();
		Display.setDisplayMode(new DisplayMode(600,240));
		Display.create();
		Keyboard.create();
		Keyboard.enableRepeatEvents(true);
		Tutorial engine = new Tutorial(scenario, settings);
		engine.execute();
		
		
		while (settings.isContinuing()){
			engine.update();
//			pollInput();
			Display.update();
			
			
		}
		engine.destroy();
		Display.destroy();
		Keyboard.destroy();
		
	}
	
//	static void pollInput(){
//		if(Keyboard.isKeyDown(Keyboard.KEY_A))			
//			System.out.println("A PRESSED");
//	}

}
