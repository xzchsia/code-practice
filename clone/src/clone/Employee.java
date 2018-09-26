package clone;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Cloneable {

	private String name;
	private double salary;
	private Date hireDay;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		this.hireDay = new Date();
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month-1, day).getTime();
		this.hireDay.setTime(newHireDay.getTime());
	}

	@Override
	protected Employee clone() throws CloneNotSupportedException {
		Employee cloned = (Employee) super.clone();
		cloned.hireDay = (Date) this.hireDay.clone();
		
		return cloned;
	}

	@Override
	public String toString() {
		return "Employee[name="+name+", Salary="+this.salary+", HireDay="+this.hireDay+"]";
	}
	
	


}
