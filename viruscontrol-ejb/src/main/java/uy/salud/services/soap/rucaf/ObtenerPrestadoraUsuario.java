
package uy.salud.services.soap.rucaf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerPrestadoraUsuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerPrestadoraUsuario"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerPrestadoraUsuario", propOrder = {
    "documento"
})
public class ObtenerPrestadoraUsuario {

    protected int documento;

    /**
     * Gets the value of the documento property.
     * 
     */
    public int getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     */
    public void setDocumento(int value) {
        this.documento = value;
    }

}
