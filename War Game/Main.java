import javax.swing.JFrame;

/**
 * @author Yaroslav Miloslavsky
 * The main file that connects the JPanel to the JFrame and starts the game loop
 */
public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("War Game");
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameProcess panel = new GameProcess();
		frame.add(panel);
		frame.setVisible(true);
	}
}
