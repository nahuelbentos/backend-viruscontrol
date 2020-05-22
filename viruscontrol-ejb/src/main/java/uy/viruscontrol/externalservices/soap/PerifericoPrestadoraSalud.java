package uy.viruscontrol.externalservices.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.3
 * 2020-05-22T13:58:04.741-03:00
 * Generated source version: 3.3.3
 *
 */
@WebServiceClient(name = "PerifericoPrestadoraSalud",
                  wsdlLocation = "http://localhost:8080/viruscontrol-web/PerifericoPrestadoraSalud?wsdl",
                  targetNamespace = "http://soap.ws.drivers.viruscontrol.uy/")
public class PerifericoPrestadoraSalud extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.ws.drivers.viruscontrol.uy/", "PerifericoPrestadoraSalud");
    public final static QName WSPerifericoPrestadoraSaludPort = new QName("http://soap.ws.drivers.viruscontrol.uy/", "WSPerifericoPrestadoraSaludPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/viruscontrol-web/PerifericoPrestadoraSalud?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PerifericoPrestadoraSalud.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/viruscontrol-web/PerifericoPrestadoraSalud?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PerifericoPrestadoraSalud(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PerifericoPrestadoraSalud(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PerifericoPrestadoraSalud() {
        super(WSDL_LOCATION, SERVICE);
    }

    public PerifericoPrestadoraSalud(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public PerifericoPrestadoraSalud(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public PerifericoPrestadoraSalud(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns IWSPerifericoPrestadoraSalud
     */
    @WebEndpoint(name = "WSPerifericoPrestadoraSaludPort")
    public IWSPerifericoPrestadoraSalud getWSPerifericoPrestadoraSaludPort() {
        return super.getPort(WSPerifericoPrestadoraSaludPort, IWSPerifericoPrestadoraSalud.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IWSPerifericoPrestadoraSalud
     */
    @WebEndpoint(name = "WSPerifericoPrestadoraSaludPort")
    public IWSPerifericoPrestadoraSalud getWSPerifericoPrestadoraSaludPort(WebServiceFeature... features) {
        return super.getPort(WSPerifericoPrestadoraSaludPort, IWSPerifericoPrestadoraSalud.class, features);
    }

}