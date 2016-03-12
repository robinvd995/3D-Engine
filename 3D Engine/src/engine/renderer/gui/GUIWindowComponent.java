package engine.renderer.gui;

public class GUIWindowComponent {

	protected GUIWindow parentWindow;
	
	public void setParentWindow(GUIWindow window){
		parentWindow = window;
	}
	
	public boolean hasParentWindow(){
		return parentWindow != null;
	}
}