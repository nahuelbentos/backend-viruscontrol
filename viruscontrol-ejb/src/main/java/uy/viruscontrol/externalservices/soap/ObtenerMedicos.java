
package uy.viruscontrol.externalservices.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerMedicos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerMedicos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idPrestadora" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerMedicos", propOrder = {
    "idPrestadora"
})
public class ObtenerMedicos {

    protected int idPrestadora;

    /**
     * Gets the value of the idPrestadora property.
     * 
     */
    public int getIdPrestadora() {
        return idPrestadora;
    }

    /**
     * Sets the value of the idPrestadora property.
     * 
     */
    public void setIdPrestadora(int value) {
        this.idPrestadora = value;
    }

}
