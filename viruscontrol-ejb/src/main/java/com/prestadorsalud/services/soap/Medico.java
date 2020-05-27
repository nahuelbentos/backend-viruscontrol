
package com.prestadorsalud.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for medico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="medico"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://soap.services.prestador.com/}usuario"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="conectado" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="prestadoraSalud" type="{http://soap.services.prestador.com/}prestadoraSalud" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medico", propOrder = {
    "conectado",
    "prestadoraSalud"
})
public class Medico
    extends Usuario
{

    protected boolean conectado;
    protected PrestadoraSalud prestadoraSalud;

    /**
     * Gets the value of the conectado property.
     * 
     */
    public boolean isConectado() {
        return conectado;
    }

    /**
     * Sets the value of the conectado property.
     * 
     */
    public void setConectado(boolean value) {
        this.conectado = value;
    }

    /**
     * Gets the value of the prestadoraSalud property.
     * 
     * @return
     *     possible object is
     *     {@link PrestadoraSalud }
     *     
     */
    public PrestadoraSalud getPrestadoraSalud() {
        return prestadoraSalud;
    }

    /**
     * Sets the value of the prestadoraSalud property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrestadoraSalud }
     *     
     */
    public void setPrestadoraSalud(PrestadoraSalud value) {
        this.prestadoraSalud = value;
    }

}
