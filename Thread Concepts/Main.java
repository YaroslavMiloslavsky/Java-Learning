import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ThreadGui gui = new ThreadGui();
		frame.add(gui);
		frame.setVisible(true);
	}

}
