package course.cloud.computing.rest.collections;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Orders")
public class Orders {

	@XmlElement(name = "Order")
	private List<Order> orderList;

	public List<Order> getOrders() {
		return orderList;
	}
}
