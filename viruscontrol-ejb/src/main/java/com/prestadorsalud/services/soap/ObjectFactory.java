
package com.prestadorsalud.services.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.prestadorsalud.services.soap package. 
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

    private final static QName _EstaDisponible_QNAME = new QName("http://soap.services.prestador.com/", "estaDisponible");
    private final static QName _EstaDisponibleResponse_QNAME = new QName("http://soap.services.prestador.com/", "estaDisponibleResponse");
    private final static QName _ObtenerMedicoAsignado_QNAME = new QName("http://soap.services.prestador.com/", "obtenerMedicoAsignado");
    private final static QName _ObtenerMedicoAsignadoResponse_QNAME = new QName("http://soap.services.prestador.com/", "obtenerMedicoAsignadoResponse");
    private final static QName _ObtenerMedicos_QNAME = new QName("http://soap.services.prestador.com/", "obtenerMedicos");
    private final static QName _ObtenerMedicosResponse_QNAME = new QName("http://soap.services.prestador.com/", "obtenerMedicosResponse");
    private final static QName _ObtenerPrestadorDeSaludAlternativo_QNAME = new QName("http://soap.services.prestador.com/", "obtenerPrestadorDeSaludAlternativo");
    private final static QName _ObtenerPrestadorDeSaludAlternativoResponse_QNAME = new QName("http://soap.services.prestador.com/", "obtenerPrestadorDeSaludAlternativoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.prestadorsalud.services.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EstaDisponible }
     * 
     */
    public EstaDisponible createEstaDisponible() {
        return new EstaDisponible();
    }

    /**
     * Create an instance of {@link EstaDisponibleResponse }
     * 
     */
    public EstaDisponibleResponse createEstaDisponibleResponse() {
        return new EstaDisponibleResponse();
    }

    /**
     * Create an instance of {@link ObtenerMedicoAsignado }
     * 
     */
    public ObtenerMedicoAsignado createObtenerMedicoAsignado() {
        return new ObtenerMedicoAsignado();
    }

    /**
     * Create an instance of {@link ObtenerMedicoAsignadoResponse }
     * 
     */
    public ObtenerMedicoAsignadoResponse createObtenerMedicoAsignadoResponse() {
        return new ObtenerMedicoAsignadoResponse();
    }

    /**
     * Create an instance of {@link ObtenerMedicos }
     * 
     */
    public ObtenerMedicos createObtenerMedicos() {
        return new ObtenerMedicos();
    }

    /**
     * Create an instance of {@link ObtenerMedicosResponse }
     * 
     */
    public ObtenerMedicosResponse createObtenerMedicosResponse() {
        return new ObtenerMedicosResponse();
    }

    /**
     * Create an instance of {@link ObtenerPrestadorDeSaludAlternativo }
     * 
     */
    public ObtenerPrestadorDeSaludAlternativo createObtenerPrestadorDeSaludAlternativo() {
        return new ObtenerPrestadorDeSaludAlternativo();
    }

    /**
     * Create an instance of {@link ObtenerPrestadorDeSaludAlternativoResponse }
     * 
     */
    public ObtenerPrestadorDeSaludAlternativoResponse createObtenerPrestadorDeSaludAlternativoResponse() {
        return new ObtenerPrestadorDeSaludAlternativoResponse();
    }

    /**
     * Create an instance of {@link PrestadoraSalud }
     * 
     */
    public PrestadoraSalud createPrestadoraSalud() {
        return new PrestadoraSalud();
    }

    /**
     * Create an instance of {@link Medico }
     * 
     */
    public Medico createMedico() {
        return new Medico();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstaDisponible }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EstaDisponible }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "estaDisponible")
    public JAXBElement<EstaDisponible> createEstaDisponible(EstaDisponible value) {
        return new JAXBElement<EstaDisponible>(_EstaDisponible_QNAME, EstaDisponible.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstaDisponibleResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EstaDisponibleResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "estaDisponibleResponse")
    public JAXBElement<EstaDisponibleResponse> createEstaDisponibleResponse(EstaDisponibleResponse value) {
        return new JAXBElement<EstaDisponibleResponse>(_EstaDisponibleResponse_QNAME, EstaDisponibleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMedicoAsignado }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerMedicoAsignado }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "obtenerMedicoAsignado")
    public JAXBElement<ObtenerMedicoAsignado> createObtenerMedicoAsignado(ObtenerMedicoAsignado value) {
        return new JAXBElement<ObtenerMedicoAsignado>(_ObtenerMedicoAsignado_QNAME, ObtenerMedicoAsignado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMedicoAsignadoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerMedicoAsignadoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "obtenerMedicoAsignadoResponse")
    public JAXBElement<ObtenerMedicoAsignadoResponse> createObtenerMedicoAsignadoResponse(ObtenerMedicoAsignadoResponse value) {
        return new JAXBElement<ObtenerMedicoAsignadoResponse>(_ObtenerMedicoAsignadoResponse_QNAME, ObtenerMedicoAsignadoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMedicos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerMedicos }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "obtenerMedicos")
    public JAXBElement<ObtenerMedicos> createObtenerMedicos(ObtenerMedicos value) {
        return new JAXBElement<ObtenerMedicos>(_ObtenerMedicos_QNAME, ObtenerMedicos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerMedicosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerMedicosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "obtenerMedicosResponse")
    public JAXBElement<ObtenerMedicosResponse> createObtenerMedicosResponse(ObtenerMedicosResponse value) {
        return new JAXBElement<ObtenerMedicosResponse>(_ObtenerMedicosResponse_QNAME, ObtenerMedicosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadorDeSaludAlternativo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadorDeSaludAlternativo }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "obtenerPrestadorDeSaludAlternativo")
    public JAXBElement<ObtenerPrestadorDeSaludAlternativo> createObtenerPrestadorDeSaludAlternativo(ObtenerPrestadorDeSaludAlternativo value) {
        return new JAXBElement<ObtenerPrestadorDeSaludAlternativo>(_ObtenerPrestadorDeSaludAlternativo_QNAME, ObtenerPrestadorDeSaludAlternativo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadorDeSaludAlternativoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerPrestadorDeSaludAlternativoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.services.prestador.com/", name = "obtenerPrestadorDeSaludAlternativoResponse")
    public JAXBElement<ObtenerPrestadorDeSaludAlternativoResponse> createObtenerPrestadorDeSaludAlternativoResponse(ObtenerPrestadorDeSaludAlternativoResponse value) {
        return new JAXBElement<ObtenerPrestadorDeSaludAlternativoResponse>(_ObtenerPrestadorDeSaludAlternativoResponse_QNAME, ObtenerPrestadorDeSaludAlternativoResponse.class, null, value);
    }

}
