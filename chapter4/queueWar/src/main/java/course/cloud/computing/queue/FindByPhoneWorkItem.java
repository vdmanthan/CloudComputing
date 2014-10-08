package course.cloud.computing.queue;

import course.cloud.computing.rest.collections.Customer;

public class FindByPhoneWorkItem implements WorkItem {

	String ph = null;
	boolean isProcessed = false;
	private Customer results = null;

	public FindByPhoneWorkItem(String phone) {
		ph = phone;
	}

	@Override
	public boolean process() {
		for (Customer cust : Resource.getCustomers().getCollection()) {
			if (cust.getPhone().equals(ph)) {
				results = cust;
			}
		}
		isProcessed = true;
		return isProcessed;
	}

	public boolean isCompleted() {
		return isProcessed;
	}

	public Customer getResponse() {
		return results;
	}

}
