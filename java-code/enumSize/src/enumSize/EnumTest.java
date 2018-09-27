package enumSize;

import java.util.Scanner;

public class EnumTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a size:");
		String input = in.next().toUpperCase();
		Size size = Enum.valueOf(Size.class, input);
		System.out.println("size ="+ size);
		System.out.println("ab="+size.getAB());
		if(size == Size.EXTRA_LARGE)
		{
			System.out.println("Good job...");
		}
		
		Size[] v = Size.values();
		System.out.println("size num:"+v.length);

	}

}

enum Size
{
	SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
	
	private Size(String ab) {this.ab = ab;}
	public String getAB() {return this.ab;}
	
	private String ab;
}
