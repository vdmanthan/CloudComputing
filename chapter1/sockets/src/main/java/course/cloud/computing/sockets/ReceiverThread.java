package course.cloud.computing.sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ReceiverThread implements Runnable {

	private BufferedReader in;
	private BufferedWriter writer;

	public ReceiverThread(BufferedReader in, BufferedWriter writer) {
		this.in = in;
		this.writer = writer;
	}

	public void run() {
		String inputLine;
		try {
			while ((inputLine = in.readLine()) != null) {
				writer.write("client: " + inputLine + "\n");
				writer.flush();
			}
			System.out.println("exiting receiver thread ....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
