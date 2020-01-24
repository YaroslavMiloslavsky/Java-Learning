import java.util.Calendar;
/**
 * This is the main class, it has few employees examples and shows their data in a polymorphical way
 * Each employee that has or had birthday this month (currently December but may vary) has 200$ bonus
 * Chapter 10.5 was used to help create this project
 * 
 * @author Yaroslav Miloslavsky
 *
 */
public class Main {
	public static void main(String[] args) {
		SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00,31,12,1993);
		HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.7, 40,29,2,1992);
		CommissionEmployee commisiionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06,14,1,1980);
		BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300,25,12,1986);
		PieceWorkerEmployee pieceWorkerEmployee = new PieceWorkerEmployee("Albert", "Nickson", "555-55-5555", 40, 25, 30, 12, 1985);
		
		Employee[] employees = new Employee[5]; // an array of employees
		employees[0] = salariedEmployee;
		employees[1] = hourlyEmployee;
		employees[2] = commisiionEmployee;
		employees[3] = basePlusCommissionEmployee;
		employees[4] = pieceWorkerEmployee;
		
		System.out.printf("Employees processed polymorphicaly: %n%n");
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH); // this month
		
		for(Employee currentEmployee: employees) {
			System.out.println(currentEmployee);
				
			if (currentEmployee instanceof BasePlusCommissionEmployee) {
				BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentEmployee;
				employee.setBaseSalary(1.10 * employee.getBaseSalary());
				System.out.printf("new base salary with 10%% increase is: $%,.2f%n",employee.getBaseSalary());
			}
			
			
			if(currentMonth == currentEmployee.getMonth())// each employee that has birthday this month get 200$ bonus
				System.out.printf("had birthday this month and therefore has 200$ bonus%nshould have earned $%,.2f%nearned $%,.2f%n%n", currentEmployee.earnings(),currentEmployee.earnings() + 200.0);
			else
				System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
		}
		
		for(int j=0 ; j<employees.length; j++)
			System.out.printf("Employee %d is %s%n",j,employees[j].getClass().getName());
		
	}
}
