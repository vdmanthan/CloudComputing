package course.cloud.computing.rest.collections;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement(name="ShipInfo")
public class ShipInfo {
	
	@XmlAttribute(name="ShippedDate")
	Date shipDate;
	
	@XmlElement(name = "ShipVia")
	int shipVia;
	
	@XmlElement(name = "Freight")
	int freight;
	
	@XmlElement(name = "ShipName")
	int shipName;
	
	@XmlElement(name = "ShipAddress")
	int shipAddress;
	
	@XmlElement(name = "ShipCity")
	int shipCity;
	
	@XmlElement(name = "ShipRegion")
	int shipRegion;
	
	@XmlElement(name = "ShipPostalCode")
	int shipPostalCode;
	
	@XmlElement(name = "ShipCountry")
	int shipCountry;

}
