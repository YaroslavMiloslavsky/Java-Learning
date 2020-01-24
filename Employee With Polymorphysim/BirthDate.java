import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Birth day class
 * Used for the employees birth dates and for bonus purposes
 */
public class BirthDate {
	private Calendar dayOfBirth;
	private int year;
	private int month;
	
	public BirthDate(int day, int month, int year) {
		GregorianCalendar c = new GregorianCalendar();
		
		if(month==1 ||month==3 || month==5 || month==7 || month==8 || month==10 ||month==12) // months with 31 days
			if(day<1 || day >31)
				throw new IllegalArgumentException("This day does not exist");
		if(month==4 || month==6 || month==9 || month==11) // days with 30 days 
			if(day<1 || day >30)
				throw new IllegalArgumentException("This day does not exist");
		if(month==2 && !c.isLeapYear(year)) // February has 28 days
			if(day<1 || day >28)
				throw new IllegalArgumentException("This day does not exist");
		if(month==2 && c.isLeapYear(year))
			if(day<1 || day >29) // during leap year February has 29 days
				throw new IllegalArgumentException("This day does not exist");
		
		this.year = year;
		this.month = month;
		dayOfBirth = new GregorianCalendar(year,month-1,day);
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	// Standard (Israel) date format 
		return sdf.format(dayOfBirth.getTime()) ;
	}
	
	
	
}
