package course.cloud.computing.queue;

import java.util.Properties;

import javax.servlet.ServletContextEvent;

public class ServletContextListener implements
		javax.servlet.ServletContextListener {

	Properties properties = new Properties();

	public Properties getProperties() {
		return properties;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// Stop the thread factory here
		ProcessingFactory.destroy("processing-queue");

		// print the message to indicate ...
		System.out
				.println("Servlet context has been destroyed, stopped the workers and emptied the queue");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Read the properties from here
		properties.put(
				"course.cloud.computing.workers",
				event.getServletContext().getInitParameter(
						"course.cloud.computing.workers"));
		properties.put(
				"course.cloud.computing.queuedepth",
				event.getServletContext().getInitParameter(
						"course.cloud.computing.queuedepth"));

		System.out.println("Read the following properties from web.xml: "
				+ properties);

		// Start the queue and the thread factory here
		ProcessingFactory.create("processing-queue", properties);

	}

}
