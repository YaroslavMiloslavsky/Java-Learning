import javax.swing.JFrame;
/**
 * 
 * @author Yaroslav Miloslavsky 
 *
 */
public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Matrix configuration");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MatrixConfigure());
		frame.setVisible(true);
	}
}
