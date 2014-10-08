package course.cloud.computing.rest.collections;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class reads the customer records from the xml file and ignores the Full
 * address sub element of the customer element.
 * 
 * @author vijaydialani
 * 
 */
@XmlRootElement(name = "Customer")
public class Customer {

	private String id;
	private String company;
	private String contactName;
	private String contactTitle;
	private String phone;

	public String getId() {
		return id;
	}

	@XmlAttribute(name = "CustomerID")
	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	@XmlElement(name = "CompanyName")
	public void setCompany(String company) {
		this.company = company;
	}

	public String getContactName() {
		return contactName;
	}

	@XmlElement(name = "ContactName")
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	@XmlElement(name = "ContactTitle")
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getPhone() {
		return phone;
	}

	@XmlElement(name = "Phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
