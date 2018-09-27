package bounce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bounce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(() -> {
			JFrame frame = new BounceFrame();
			frame.setTitle("Bounce");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}

class BounceFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;

	/**
	 * Constructs the frame with the component for showing the bouncing ball and
	 * Start and Close buttons
	 */
	public BounceFrame() {
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", event -> addBall());
		addButton(buttonPanel, "Close", event -> System.exit(0));
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}

	/**
	 * Adds a button to a container.
	 * 
	 * @param c
	 *            the container
	 * @param title
	 *            the button title
	 * @param listener
	 *            the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	/**
	 * Adds a bouncing ball to the panel and makes it bounce 1,000 times .
	 */
	/*
	 * 单线程版本 
	 * public void addBall() { Ball ball = new Ball(); comp.add(ball); try {
	 * for (int i = 1; i <= STEPS; i++) { ball.move(comp.getBounds());
	 * comp.paint(comp.getGraphics()); Thread.sleep(DELAY); } } catch
	 * (InterruptedException e) { } }
	 */

	// 多线程版本
	public void addBall() {
		Ball ball = new Ball();
		comp.add(ball);
		Runnable r = () -> {
			try {
				for (int i = 1; i <= STEPS; i++) {
					ball.move(comp.getBounds());
					//comp.paint(comp.getGraphics());
					comp.repaint();
					Thread.sleep(DELAY);
				}
			} catch (InterruptedException e) {
			}
			finally {
			}
		};

		Thread t = new Thread(r);
		t.start();
	}

}