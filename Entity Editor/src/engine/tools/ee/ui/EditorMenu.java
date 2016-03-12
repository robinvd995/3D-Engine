package engine.tools.ee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import engine.tools.ee.EntityEditor;

public class EditorMenu extends JMenuBar {

	private static final long serialVersionUID = -9074688500473273818L;
	
	private JMenu file;
	private JMenu component;
	
	private JMenuItem newFile;
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem exit;
	
	private JMenuItem addComponent;
	
	public EditorMenu(){
		
		MenuListener listener = new MenuListener();
		
		file = new JMenu("File");
		add(file);
		
		newFile = new JMenuItem("New");
		newFile.setActionCommand(ActionEnum.NEW.name());
		newFile.addActionListener(listener);
		file.add(newFile);
		
		openFile = new JMenuItem("Open");
		openFile.setActionCommand(ActionEnum.OPEN.name());
		openFile.addActionListener(listener);
		file.add(openFile);
		
		saveFile = new JMenuItem("Save");
		saveFile.setActionCommand(ActionEnum.SAVE.name());
		saveFile.addActionListener(listener);
		file.add(saveFile);
		
		exit = new JMenuItem("Exit");
		exit.setActionCommand(ActionEnum.EXIT.name());
		exit.addActionListener(listener);
		file.add(exit);
		
		component = new JMenu("Component");
		add(component);
		
		addComponent = new JMenuItem("Add Component");
		addComponent.setActionCommand(ActionEnum.ADD_COMPONENT.name());
		addComponent.addActionListener(listener);
		component.add(addComponent);
	}

	private class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ActionEnum action = ActionEnum.valueOf(e.getActionCommand());
			EntityEditor.theWindow.log(action.name());
			
			switch(action){
			case NEW:
				
				break;
				
			case OPEN:
				
				break;
				
			case SAVE:
				
				break;
				
			case EXIT:
				EntityEditor.close();
				break;
				
			case ADD_COMPONENT:
				EntityEditor.addComponentFromBase(e.getActionCommand());
			}
		}
	}
	
	private enum ActionEnum {
		NEW, OPEN, SAVE, EXIT, ADD_COMPONENT;
	}
}
