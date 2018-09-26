import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class RecieveThread extends Thread {
	private Socket s;

	public RecieveThread(Socket s) {
		super();
		this.s = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			while(true) {
				String msg = dis.readUTF();
				System.out.println(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
