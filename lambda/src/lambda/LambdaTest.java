package lambda;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;


public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] planets = new String[] {
				"Mercury" , "Venus", "Earth" , "Mars" ,
				"Jupiter" , "Saturn", "Uranus", "Neptune"
		};
		
		System.out.println(Arrays.toString(planets));
		
		System.out.println("Sorted in dictionary order:");
		Arrays.sort(planets);
		System.out.println(Arrays.toString(planets));
		
		System.out.println("Sorted by length:");
		Arrays.sort(planets, (first, second)->first.length()-second.length());
		System.out.println(Arrays.toString(planets));
		
//		Timer t = new Timer(1000, event->{
//			System.out.println("time is "+new Date());
//			Toolkit.getDefaultToolkit().beep();
//		}
//		);
		
		//Timer t = new Timer(1000, event->System.out.println("time is "+new Date()));
		
		//Timer t = new Timer(1000, event->System.out.println(event));
		// ��������
		Timer t = new Timer(1000, System.out::println);
		
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);

	}

}
