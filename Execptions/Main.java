import javax.swing.JOptionPane;

public class Main {
	public static void main(String []args){
		StudentClass class101 = new StudentClass();
		Student yaro = new Student("Yaroslav", 1234);
		Student vitaly = new Student("Vitaly",1134);
		try {
			class101.addStudent(yaro);
			class101.addStudent(vitaly);
		}
		catch(StudentExistsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		try {
			class101.addStudent(yaro);
		}
		catch(StudentExistsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
}
