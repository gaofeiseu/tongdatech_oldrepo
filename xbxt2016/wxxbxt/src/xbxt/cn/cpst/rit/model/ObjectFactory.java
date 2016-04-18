package cn.cpst.rit.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the cn.cpst.rit.model package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _MailMailCode_QNAME = new QName(
			"http://model.rit.cpst.cn", "mailCode");
	private final static QName _MailActionInfoOut_QNAME = new QName(
			"http://model.rit.cpst.cn", "actionInfoOut");
	private final static QName _MailRelationOfficeDesc_QNAME = new QName(
			"http://model.rit.cpst.cn", "relationOfficeDesc");
	private final static QName _MailOfficeName_QNAME = new QName(
			"http://model.rit.cpst.cn", "officeName");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: cn.cpst.rit.model
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Mail }
	 * 
	 */
	public Mail createMail() {
		return new Mail();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://model.rit.cpst.cn", name = "mailCode", scope = Mail.class)
	public JAXBElement<String> createMailMailCode(String value) {
		return new JAXBElement<String>(_MailMailCode_QNAME, String.class,
				Mail.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://model.rit.cpst.cn", name = "actionInfoOut", scope = Mail.class)
	public JAXBElement<String> createMailActionInfoOut(String value) {
		return new JAXBElement<String>(_MailActionInfoOut_QNAME, String.class,
				Mail.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://model.rit.cpst.cn", name = "relationOfficeDesc", scope = Mail.class)
	public JAXBElement<String> createMailRelationOfficeDesc(String value) {
		return new JAXBElement<String>(_MailRelationOfficeDesc_QNAME,
				String.class, Mail.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://model.rit.cpst.cn", name = "officeName", scope = Mail.class)
	public JAXBElement<String> createMailOfficeName(String value) {
		return new JAXBElement<String>(_MailOfficeName_QNAME, String.class,
				Mail.class, value);
	}

}
