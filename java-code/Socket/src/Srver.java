import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Srver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(8886);
			System.out.println("listening port 8886");
			Socket s = ss.accept();
			System.out.println("we have a connection:"+s);
			
			/*
			 * 启动发送和接收线程
			 */
			new SendThread(s).start();
			new RecieveThread(s).start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
