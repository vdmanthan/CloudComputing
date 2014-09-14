package course.cloud.computing.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoadGenerator {

	AtomicBoolean isRunning = new AtomicBoolean(false);

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("A valid url needs to be specified.");
			System.exit(-1);
		}

		URL url = null;
		try {
			url = new URL(args[0]);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}

		LoadGenerator generator = new LoadGenerator();
		generator.run(url);
		System.out.println("Running the load generator hit enter to stop ... ");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		generator.stop();
	}

	private void stop() {
		isRunning.compareAndSet(true, false);
	}

	private void run(URL url) {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		int count = 0;
		if (isRunning.compareAndSet(false, true)) {
			while (isRunning.get()) {
				String content = fetchURL(url);
				if (count % 1000 == 0) {
					print(counts);
				}
				count++;
				if (counts.containsKey(content)) {
					counts.put(content, counts.get(content) + 1);
				} else {
					counts.put(content, 1);
				}
			}
		}

	}

	private void print(Map<String, Integer> counts) {
		System.out.println(counts);
	}

	private String fetchURL(URL url) {
		String content = new String();
		InputStream is = null;
		try {
			is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				content = content + line;
			}

		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		return content;
	}

}
