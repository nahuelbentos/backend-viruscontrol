package uy.salud.services.soap.rucaf;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.3
 * 2020-06-18T18:42:35.831-03:00
 * Generated source version: 3.3.3
 *
 */
@WebServiceClient(name = "RucafService",
                  wsdlLocation = "http://localhost:8080/viruscontrol-web/openshift-wsdl/MiNubeGr14RucafService.wsdl",
                  targetNamespace = "http://soap.salud.uy/")
public class RucafService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.salud.uy/", "RucafService");
    public final static QName RucafServicePort = new QName("http://soap.salud.uy/", "RucafServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/viruscontrol-web/openshift-wsdl/MiNubeGr14RucafService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(RucafService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/viruscontrol-web/openshift-wsdl/MiNubeGr14RucafService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public RucafService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RucafService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RucafService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public RucafService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public RucafService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public RucafService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns IRucafService
     */
    @WebEndpoint(name = "RucafServicePort")
    public IRucafService getRucafServicePort() {
        return super.getPort(RucafServicePort, IRucafService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IRucafService
     */
    @WebEndpoint(name = "RucafServicePort")
    public IRucafService getRucafServicePort(WebServiceFeature... features) {
        return super.getPort(RucafServicePort, IRucafService.class, features);
    }

}
