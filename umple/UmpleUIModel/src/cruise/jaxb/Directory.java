//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.03 at 08:03:30 PM EST 
//


package cruise.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="InputFolder" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OutputSubFolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Directory")
public class Directory {

    @XmlAttribute(name = "InputFolder", required = true)
    protected String inputFolder;
    @XmlAttribute(name = "OutputSubFolder")
    protected String outputSubFolder;

    /**
     * Gets the value of the inputFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputFolder() {
        return inputFolder;
    }

    /**
     * Sets the value of the inputFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputFolder(String value) {
        this.inputFolder = value;
    }

    /**
     * Gets the value of the outputSubFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputSubFolder() {
        return outputSubFolder;
    }

    /**
     * Sets the value of the outputSubFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputSubFolder(String value) {
        this.outputSubFolder = value;
    }

}
