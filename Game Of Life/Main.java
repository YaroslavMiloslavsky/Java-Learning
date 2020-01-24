import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Game of Life");
		frame.setSize(518, 540);	// the size is like this for convenience
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameMatrix matrix = new GameMatrix();
;		frame.add(matrix);
		frame.setVisible(true);
		matrix.begin(); // here we begin the game loop
	}
}
