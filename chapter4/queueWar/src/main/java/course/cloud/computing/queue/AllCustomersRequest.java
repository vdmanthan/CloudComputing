package course.cloud.computing.queue;

//import javax.ws.rs.container.AsyncResponse;

import course.cloud.computing.rest.Resource;
import course.cloud.computing.rest.collections.Customers;

public class AllCustomersRequest implements WorkItem {

	boolean isProcessed = false;
	
	Customers response = null;
	
	@Override
	public boolean process() {
		response = Resource.getCustomers();
		isProcessed = true;
		return isProcessed;
	}
	
	public boolean isCompleted(){
		return isProcessed;
	}
	
	public Customers getResponse(){
		return response;
	}
	

}
