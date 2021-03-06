package cn.cpst.rit.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * MailCsService_Gn service = new MailCsService_Gn();
 * MailCsServiceGnPortType portType = service.getMailCsServiceGnHttpPort();
 * portType.getMails(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "MailCsService_Gn", targetNamespace = "http://service.rit.cpst.cn", wsdlLocation = "http://211.156.198.97/zdxtJkServer/zhddws/MailCsService_Gn?wsdl")
public class MailCsServiceGn extends Service {

	private final static URL MAILCSSERVICEGN_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(cn.cpst.rit.service.MailCsServiceGn.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = cn.cpst.rit.service.MailCsServiceGn.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://211.156.198.97/zdxtJkServer/zhddws/MailCsService_Gn?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://211.156.198.97/zdxtJkServer/zhddws/MailCsService_Gn?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		MAILCSSERVICEGN_WSDL_LOCATION = url;
	}

	public MailCsServiceGn(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public MailCsServiceGn() {
		super(MAILCSSERVICEGN_WSDL_LOCATION, new QName(
				"http://service.rit.cpst.cn", "MailCsService_Gn"));
	}

	/**
	 * 
	 * @return returns MailCsServiceGnPortType
	 */
	@WebEndpoint(name = "MailCsService_GnHttpPort")
	public MailCsServiceGnPortType getMailCsServiceGnHttpPort() {
		return super.getPort(new QName("http://service.rit.cpst.cn",
				"MailCsService_GnHttpPort"), MailCsServiceGnPortType.class);
	}

}
