package engine.tools.ee.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.tools.ee.Component;

public class ComponentPanel extends JPanel{
	
	private static final long serialVersionUID = -1009926161147541294L;
	
	private static final int OFFSET_X = 2;
	private static final int OFFSET_Y = 2;
	private static final int SIZE_X = 275;
	private static final int SIZE_Y = 24;
	private static final int BUTTON_WIDTH = 46;
	
	public Component component;
	
	private JLabel label;
	private JButton button;
	private JButton remove;
	
	public ComponentPanel(Component component){
		this.component = component;
		this.setBackground(Color.GRAY);
		this.setLayout(null);
		
		label = new JLabel(component.getLocalizedName());
		label.setBounds(2, 0, SIZE_X - BUTTON_WIDTH * 2 - 3, 24);
		this.add(label);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.GRAY);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		button = new JButton("...");
		button.setBounds(SIZE_X - BUTTON_WIDTH * 2, 0, BUTTON_WIDTH, 24);
		this.add(button);
		
		remove = new JButton("x");
		remove.setBounds(SIZE_X - BUTTON_WIDTH - 1, 0, BUTTON_WIDTH, 24);
		this.add(remove);
	}
	
	public void init(int index){
		this.setPosition(index);
	}
	
	private void setPosition(int index){
		int yPos = OFFSET_Y + index * SIZE_Y;
		this.setBounds(OFFSET_X, yPos, SIZE_X, SIZE_Y);
	}
	
	public boolean equals(Object o){
		return (o instanceof ComponentPanel) ? ((ComponentPanel)o).component.equals(component) : false;
	}
}