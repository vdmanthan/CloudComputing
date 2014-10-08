package course.cloud.computing.queue;

//import javax.ejb.Asynchronous;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import course.cloud.computing.rest.collections.Customer;
import course.cloud.computing.rest.collections.Customers;

@Path("/customers")
public class CustomerService {

	private final static String queueName = "processing-queue";

	@GET
	@Produces("application/xml")
	@Path("all")
	public Customers getCustomers() throws InterruptedException {
		System.out.println("Entering get customers");
		AllCustomersRequest request = new AllCustomersRequest();
		System.out.println("Created Request");
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		System.out.println("Added to the queue");
		if (queue != null) {
			queue.add(request);
		}
		while (!request.isCompleted()) {
			Thread.currentThread().sleep(5);
		}
		return request.getResponse();
	}

	@GET
	@Produces("application/xml")
	@Path("find")
	public Customer getCustomersWithPhoneNumber(
			@QueryParam("phone") String phone)
			throws CustomerNotFoundException, InterruptedException {
		FindByPhoneWorkItem request = new FindByPhoneWorkItem(phone);
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
		}
		while (!request.isCompleted()) {
			Thread.currentThread().sleep(5);
		}
		Customer cust = request.getResponse();
		if (cust != null) {
			return cust;
		} else {
			throw new CustomerNotFoundException(" Customer with Phone, "
					+ phone + ", is not found");
		}
	}

	// This method is not implemented for servicing by the queue
	// It is an example that different edge handling within the container
	// can coexist
	@GET
	@Produces("application/xml")
	@Path("find2")
	public Customers getCustomersWithID(@QueryParam("id") String id)
			throws CustomerNotFoundException {
		Customers customers = new Customers();
		for (Customer cust : Resource.getCustomers().getCollection()) {
			if (cust.getId().contains(id)) {
				customers.addCustomer(cust);
			}
		}
		if (customers.getCollection().size() > 0) {
			return customers;
		} else {
			throw new CustomerNotFoundException(
					" Customer with partial match in id with: " + id
							+ ", is not found");
		}
	}

}