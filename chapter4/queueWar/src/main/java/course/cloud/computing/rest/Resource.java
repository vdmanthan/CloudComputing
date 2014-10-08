package course.cloud.computing.rest;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import course.cloud.computing.rest.collections.Customers;
import course.cloud.computing.rest.collections.Orders;

public class Resource {

	static Customers customers = new Customers();
	static Orders orders = new Orders();
	static boolean isPopulated = false;

	public static Customers getCustomers() {
		if(!isPopulated){
			populateData();
			isPopulated = true;
		}
		return customers;
	}

	public static Orders getOrders() {
		if(!isPopulated){
			populateData();
			isPopulated = true;
		}
		return orders;
	}

	public static void populateData() {
		InputStream customerStream = Resource.class
				.getResourceAsStream("/customers.xml");
		if (customerStream == null) {
			System.out.println("Could not find the stream ... exiting");
			System.exit(-1);
		}

		InputStream orderStream = Resource.class
				.getResourceAsStream("/orders.xml");
		if (orderStream == null) {
			System.out.println("Could not find the stream ... exiting");
			System.exit(-1);
		}

		JAXBContext jaxbContext;
		try {
			// Populate the Customer records
			jaxbContext = JAXBContext.newInstance(Customers.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			customers = (Customers) jaxbUnmarshaller.unmarshal(customerStream);

			// Populate the Orders
			jaxbContext = JAXBContext.newInstance(Orders.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			orders = (Orders) jaxbUnmarshaller.unmarshal(orderStream);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
