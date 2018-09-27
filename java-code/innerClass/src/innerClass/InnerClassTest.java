package innerClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;


public class InnerClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalkingClock tc = new TalkingClock(1000, true);
		tc.start();
		
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);

	}

}


class TalkingClock {
	private int interval;
	private boolean beep;
	
	public TalkingClock(int interval, boolean beep) {
		//super();
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start() {
		class TimePrinter implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("At the tone, the time is "+ new Date());
				if (TalkingClock.this.beep) {
					Toolkit.getDefaultToolkit().beep();
				}
				
			}
			
		}
		
		ActionListener lin = new TimePrinter();
		Timer t = new Timer(this.interval, lin);
		
		t.start();
	}
	
	/// Inner Class
//	public class TimePrinter implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			// TODO Auto-generated method stub
//			System.out.println("At the tone, the time is "+ new Date());
//			if (TalkingClock.this.beep) {
//				Toolkit.getDefaultToolkit().beep();
//			}
//			
//		}
//		
//	}
	
	
}