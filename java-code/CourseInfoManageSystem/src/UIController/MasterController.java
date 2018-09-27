package UIController;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public abstract class MasterController extends JFrame implements ActionListener{
	protected Container pane;
	protected HashSet<Object> list_check_fill = new HashSet<Object>();
	
	public MasterController (int width, int height, String title) {
		this.pane = this.getContentPane();
		
		this.addToPane();
		this.setResizable(false);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();//以下，让窗口居中显示
		this.setSize(width, height);
		int x = (int)(toolkit.getScreenSize().getWidth() - this.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight() - this.getHeight())/2;
		this.setLocation(x, y);
		
		this.setVisible(true);
		this.setTitle(title);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	public abstract void addToPane();
	
	public void showDialog(String strInfo) {
		JOptionPane pane = new JOptionPane();
	    pane.showMessageDialog(null, strInfo);
	}
	
	public boolean isFill() {
		Iterator<Object> iterator = list_check_fill.iterator();
		while(iterator.hasNext()) {
			try {
				JTextField nextObject = (JTextField) iterator.next();
				if(nextObject.getText().equals("")) {
					return false;
				}
				
				if(nextObject.getText().indexOf('|') != -1) {
					return false;
				}
			} catch (Exception e){
				return false;
			}
		}
		
		return true;
	}
	
}
