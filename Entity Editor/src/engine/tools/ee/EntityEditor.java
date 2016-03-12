package engine.tools.ee;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import engine.tools.ee.ui.EditorWindow;

public class EntityEditor {

	public static EditorWindow theWindow;
	
	private static List<Component> components = new ArrayList<Component>();
	
	public static void main(String[] args) {
		
		theWindow = new EditorWindow();
		
		ComponentBase.loadBaseComponents();
		
		while(true){
			theWindow.renderOpenGL();
			Display.update();
		}
	}
	
	public static void close(){
		System.exit(0);
	}
	
	public static void addComponentFromBase(String base){
		
	}
	
	public static void addComponent(Component c){
		components.add(c);
		theWindow.onComponentsUpdated();
	}
	
	public static List<Component> getComponentList(){
		return components;
	}
}