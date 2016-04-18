package cn.cpst.rit.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for Mail complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Mail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="actionInfoOut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationOfficeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mail", propOrder = { "actionDateTime", "actionInfoOut",
		"mailCode", "officeName", "relationOfficeDesc" })
public class Mail {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar actionDateTime;
	@XmlElementRef(name = "actionInfoOut", namespace = "http://model.rit.cpst.cn", type = JAXBElement.class)
	protected JAXBElement<String> actionInfoOut;
	@XmlElementRef(name = "mailCode", namespace = "http://model.rit.cpst.cn", type = JAXBElement.class)
	protected JAXBElement<String> mailCode;
	@XmlElementRef(name = "officeName", namespace = "http://model.rit.cpst.cn", type = JAXBElement.class)
	protected JAXBElement<String> officeName;
	@XmlElementRef(name = "relationOfficeDesc", namespace = "http://model.rit.cpst.cn", type = JAXBElement.class)
	protected JAXBElement<String> relationOfficeDesc;

	/**
	 * Gets the value of the actionDateTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getActionDateTime() {
		return actionDateTime;
	}

	/**
	 * Sets the value of the actionDateTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setActionDateTime(XMLGregorianCalendar value) {
		this.actionDateTime = value;
	}

	/**
	 * Gets the value of the actionInfoOut property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getActionInfoOut() {
		return actionInfoOut;
	}

	/**
	 * Sets the value of the actionInfoOut property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setActionInfoOut(JAXBElement<String> value) {
		this.actionInfoOut = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the mailCode property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getMailCode() {
		return mailCode;
	}

	/**
	 * Sets the value of the mailCode property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setMailCode(JAXBElement<String> value) {
		this.mailCode = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the officeName property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getOfficeName() {
		return officeName;
	}

	/**
	 * Sets the value of the officeName property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setOfficeName(JAXBElement<String> value) {
		this.officeName = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the relationOfficeDesc property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getRelationOfficeDesc() {
		return relationOfficeDesc;
	}

	/**
	 * Sets the value of the relationOfficeDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setRelationOfficeDesc(JAXBElement<String> value) {
		this.relationOfficeDesc = ((JAXBElement<String>) value);
	}

}
