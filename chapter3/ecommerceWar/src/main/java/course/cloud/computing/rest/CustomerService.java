package course.cloud.computing.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import course.cloud.computing.rest.collections.Customer;
import course.cloud.computing.rest.collections.Customers;

@Path("/customers")
public class CustomerService {
	@GET
	@Produces("application/xml")
	@Path("all")
	public Customers getCustomers() {
		return Resource.getCustomers();
	}

	@GET
	@Produces("application/xml")
	@Path("find")
	public Customer getCustomersWithPhoneNumber(
			@QueryParam("phone") String phone) throws CustomerNotFoundException {
		for (Customer cust : Resource.getCustomers().getCollection()) {
			if (cust.getPhone().equals(phone)) {
				return cust;
			}
		}
		throw new CustomerNotFoundException(" Customer with Phone, " + phone
				+ ", is not found");
	}

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
			throw new CustomerNotFoundException(" Customer with partial match in id with: "
					+ id + ", is not found");
		}
	}

}