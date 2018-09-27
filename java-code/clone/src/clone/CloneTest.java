package clone;

public class CloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee original = new Employee("Jone ", 5000);
		original.setHireDay(2018, 8, 20);
		
		Employee copy;
		try {
			copy = original.clone();
			copy.raiseSalary(10);
			copy.setHireDay(2018, 9, 4);
			System.out.println("original="+original);
			System.out.println("copy="+copy);
			
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
