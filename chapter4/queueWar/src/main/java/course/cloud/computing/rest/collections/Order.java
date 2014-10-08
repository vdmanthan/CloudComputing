package course.cloud.computing.rest.collections;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name ="Order")
public class Order {
	
	@XmlElement(name="CustomerID")
	private String customerId;
	
	@XmlElement(name="EmployeeID")
	private String employeeId;
	
	@XmlElement(name="OrderDate")
	private Date orderDate;
	
	@XmlElement(name="ShipInfo")
	private ShipInfo shipInfo;

}
