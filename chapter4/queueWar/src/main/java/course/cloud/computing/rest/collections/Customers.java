package course.cloud.computing.rest.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "Customers")
public class Customers {

	@XmlElement(name = "Customer")
	private List<Customer> CustomerList = new ArrayList<Customer>();

	public Collection<Customer> getCollection() {
		return CustomerList;
	}

	public void addCustomer(Customer cust) {
		CustomerList.add(cust);
	}

}
