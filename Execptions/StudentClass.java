
public class StudentClass {
	private Graph<Student> studentClass;
	
	public StudentClass() {
		studentClass = new Graph<Student>();
		System.out.println("Array was created");
	}
	public void addStudent(Student student) throws StudentExistsException{
		try {
			studentClass.addObject(student);
		}
		catch(ObjectExistsException e) {			
			throw new StudentExistsException(student.getName() + " Already exists");	
		}
	}
	
	public String toString() {
		String co = new String();
		for(Student std : studentClass.getArr())
			co += std.getName() + " ";
		
		return co;
	}
}
