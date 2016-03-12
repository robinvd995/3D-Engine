package engine.tools.ee;

public class Renderer {

	public static final float[] CENTER_VERTICES = {
		-1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
		0.0f -1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
		0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f
	};
	
	public static final int[] CENTER_INDICES = {
		0,1,
		2,3,
		4,5
	};
	
	/*Canvas openGLSurface = new Canvas();
	JPanel pss = new JPanel();
	JPanel pes = new JPanel();
	JFrame frame = new JFrame();
	frame.setSize(1400, 800);
	frame.add(openGLSurface);
	frame.setVisible(true);
	frame.add(new JTextField("Hello World!"));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	openGLSurface.setSize(800, 800);
	Display.setParent(openGLSurface);
	Display.create();
	pss.setBounds(800, 0, 300, 800);
	pss.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	pss.setBackground(Color.LIGHT_GRAY);
	pss.setVisible(true);
	frame.add(pss);
	pes.setBounds(1100, 0, 300, 800);
	pes.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	pes.setBackground(Color.LIGHT_GRAY);
	pes.setVisible(true);
	frame.add(pes);
	JButton hello = new JButton("Collider");
	pss.add(hello);
	pss.repaint();
	pes.repaint();*/
}
