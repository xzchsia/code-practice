import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			InetAddress host = InetAddress.getLocalHost();
			String ip = host.getHostAddress();
			System.out.println("host ip:"+ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
