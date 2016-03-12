package engine.tools.ee.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import engine.tools.ee.Component;
import engine.tools.ee.EntityEditor;

public class EditorWindow extends JFrame{

	private static final long serialVersionUID = 6181150004621045743L;

	public Canvas openglCanvas;
	public TextArea logPanel;
	public JPanel componentSelectorPanel;
	
	public List<ComponentPanel> componentPanels = new ArrayList<ComponentPanel>();
	
	public EditorWindow(){
		super("Entity Editor v0.1a");
		
		try {
			initWindows();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		EditorMenu menuBar = new EditorMenu();
		setJMenuBar(menuBar);
		
		setSize(1024, 800);
	}
	
	private void initWindows() throws LWJGLException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//setSize(800, 600);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		openglCanvas = new Canvas();
		openglCanvas.setBackground(Color.BLACK);
		springLayout.putConstraint(SpringLayout.WEST, openglCanvas, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, openglCanvas, -284, SpringLayout.EAST, getContentPane());
		openglCanvas.setSize(500, 376);
		springLayout.putConstraint(SpringLayout.NORTH, openglCanvas, 0, SpringLayout.NORTH, getContentPane());
		getContentPane().add(openglCanvas);
		Display.setParent(openglCanvas);
		Display.create();
		
		logPanel = new TextArea();
		springLayout.putConstraint(SpringLayout.WEST, logPanel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, logPanel, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, openglCanvas, -6, SpringLayout.NORTH, logPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, logPanel, 0, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(logPanel);
		logPanel.setEditable(false);
		
		componentSelectorPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, componentSelectorPanel, 6, SpringLayout.EAST, openglCanvas);
		springLayout.putConstraint(SpringLayout.SOUTH, componentSelectorPanel, 0, SpringLayout.NORTH, logPanel);
		springLayout.putConstraint(SpringLayout.EAST, componentSelectorPanel, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, componentSelectorPanel, 0, SpringLayout.NORTH, getContentPane());
		componentSelectorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY, null, null));
		getContentPane().add(componentSelectorPanel);
		componentSelectorPanel.setLayout(null);
	}
	
	public void log(String s){
		logPanel.append(s + "\n");
	}
	
	public void renderOpenGL(){
		
	}
	
	public void onComponentsUpdated(){
		componentPanels.clear();
		int i = 0;
		for(Component c : EntityEditor.getComponentList()){
			ComponentPanel panel = new ComponentPanel(c);
			panel.init(i++);
			componentSelectorPanel.add(panel);
		}
		this.updateWindow();
	}
	
	public void updateWindow(){
		this.revalidate();
		this.getContentPane().revalidate();
		this.openglCanvas.revalidate();
		this.logPanel.revalidate();
		this.componentSelectorPanel.repaint();
	}
}
