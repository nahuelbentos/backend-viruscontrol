
package uy.salud.services.soap.rucaf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uy.salud.services.soap.rucaf package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ObtenerPrestadoraUsuario_QNAME = new QName("http://soap.salud.uy/", "obtenerPrestadoraUsuario");
    private final static QName _ObtenerPrestadoraUsuarioResponse_QNAME = new QName("http://soap.salud.uy/", "obtenerPrestadoraUsuarioResponse");
    private final static QName _ObtenerPrestadoras_QNAME = new QName("http://soap.salud.uy/", "obtenerPrestadoras");
    private final static QName _ObtenerPrestadorasResponse_QNAME = new QName("http://soap.salud.uy/", "obtenerPrestadorasResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uy.salud.services.soap.rucaf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerPrestadoraUsuario }
     * 
     */
    public ObtenerPrestadoraUsuario createObtenerPrestadoraUsuario() {
        return new ObtenerPrestadoraUsuario();
    }

    /**
     * Create an instance of {@link ObtenerPrestadoraUsuarioResponse }
     * 
     */
    public ObtenerPrestadoraUsuarioResponse createObtenerPrestadoraUsuarioResponse() {
        return new ObtenerPrestadoraUsuarioResponse();
    }

    /**
     * Create an instance of {@link ObtenerPrestadoras }
     * 
     */
    public ObtenerPrestadoras createObtenerPrestadoras() {
        return new ObtenerPrestadoras();
    }

    /**
     * Create an instance of {@link ObtenerPrestadorasResponse }
     * 
     */
    public ObtenerPrestadorasResponse createObtenerPrestadorasResponse() {
        return new ObtenerPrestadorasResponse();
    }

    /**
     * Create an instance of {@link PrestadoraSalud }
     * 
     */
    public PrestadoraSalud createPrestadoraSalud() {
        return new PrestadoraSalud();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadoraUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadoraUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.salud.uy/", name = "obtenerPrestadoraUsuario")
    public JAXBElement<ObtenerPrestadoraUsuario> createObtenerPrestadoraUsuario(ObtenerPrestadoraUsuario value) {
        return new JAXBElement<ObtenerPrestadoraUsuario>(_ObtenerPrestadoraUsuario_QNAME, ObtenerPrestadoraUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadoraUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadoraUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.salud.uy/", name = "obtenerPrestadoraUsuarioResponse")
    public JAXBElement<ObtenerPrestadoraUsuarioResponse> createObtenerPrestadoraUsuarioResponse(ObtenerPrestadoraUsuarioResponse value) {
        return new JAXBElement<ObtenerPrestadoraUsuarioResponse>(_ObtenerPrestadoraUsuarioResponse_QNAME, ObtenerPrestadoraUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadoras }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadoras }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.salud.uy/", name = "obtenerPrestadoras")
    public JAXBElement<ObtenerPrestadoras> createObtenerPrestadoras(ObtenerPrestadoras value) {
        return new JAXBElement<ObtenerPrestadoras>(_ObtenerPrestadoras_QNAME, ObtenerPrestadoras.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadorasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadorasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.salud.uy/", name = "obtenerPrestadorasResponse")
    public JAXBElement<ObtenerPrestadorasResponse> createObtenerPrestadorasResponse(ObtenerPrestadorasResponse value) {
        return new JAXBElement<ObtenerPrestadorasResponse>(_ObtenerPrestadorasResponse_QNAME, ObtenerPrestadorasResponse.class, null, value);
    }

}
