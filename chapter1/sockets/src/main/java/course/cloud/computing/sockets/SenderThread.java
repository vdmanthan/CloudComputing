package course.cloud.computing.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SenderThread implements Runnable {

	private PrintWriter out;
	private BufferedReader stdIn;

	public SenderThread(BufferedReader stdIn, PrintWriter out) {
		this.stdIn = stdIn;
		this.out = out;
	}

	public void run() {
		String reply = null;
		try {
			while ((reply = stdIn.readLine()) != null) {
				out.println(reply);
			}
			System.out.println("exiting sender thread ....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
