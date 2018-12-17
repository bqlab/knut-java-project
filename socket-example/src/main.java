import java.io.IOException;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		server ss = new server();
		try {
			ss.ServerRun();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
