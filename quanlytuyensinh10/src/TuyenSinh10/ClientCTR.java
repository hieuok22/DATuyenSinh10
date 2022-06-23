package TuyenSinh10;

import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCTR {
	private int port;
	private String host;
	private Socket mySocket;
	
	public ClientCTR() {
		host = "localhost";
		port = 8888;
	}
	public void openSocket() {
		try {
			mySocket = new Socket(host, port);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	public void sendStudent(hoso sv) {
		try {
		ObjectOutputStream os = new ObjectOutputStream(mySocket.getOutputStream());
		os.writeObject(sv);//gui student sang ben server
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getResult() {
		String res = "";
		try {
			ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
			res = (String)ois.readObject();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}
	public void closeConnection() {
		try {
			mySocket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
