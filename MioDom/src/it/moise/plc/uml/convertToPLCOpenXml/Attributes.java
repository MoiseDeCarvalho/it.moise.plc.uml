package it.moise.plc.uml.convertToPLCOpenXml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attributes", propOrder = {
		"attribute"
})

public class Attributes {
	 protected List<Attributes.Attribute> data;
	 /**
	     * Gets the value of the data property.
	     * 
	     * <p>
	     * This accessor method returns a reference to the live list,
	     * not a snapshot. Therefore any modification you make to the
	     * returned list will be present inside the JAXB object.
	     * This is why there is not a <CODE>set</CODE> method for the data property.
	     * 
	     * <p>
	     * For example, to add a new item, do as follows:
	     * <pre>
	     *    getAttribute().add(newItem);
	     * </pre>
	     * 
	     * 
	     * <p>
	     * Objects of the following type(s) are allowed in the list
	     * {@link Attributes.Attribute }
	     * 
	     * 
	     */
	    public List<Attributes.Attribute> getAttribute() {
	        if (data == null) {
	            data = new ArrayList<Attributes.Attribute>();
	        }
	        return this.data;
	    }
	    
	    @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "any"
	    })
	    public static class Attribute {

	        @XmlAnyElement(lax = true)
	        protected Object any;
	        @XmlAttribute(name = "gui", required = false)
	        protected String gui;
	        
	        /**
	         * Gets the value of the any property.
	         * 
	         * @return
	         *     possible object is
	         *     {@link Element }
	         *     {@link Object }
	         *     
	         */
	        public Object getAny() {
	            return any;
	        }

	        /**
	         * Sets the value of the any property.
	         * 
	         * @param value
	         *     allowed object is
	         *     {@link Element }
	         *     {@link Object }
	         *     
	         */
	        public void setAny(Object value) {
	            this.any = value;
	        }

	        /**
	         * Gets the value of the name property.
	         * 
	         * @return
	         *     possible object is
	         *     {@link String }
	         *     
	         */
	        public String getGui() {
	            return gui;
	        }

	        /**
	         * Sets the value of the name property.
	         * 
	         * @param value
	         *     allowed object is
	         *     {@link String }
	         *     
	         */
	        public void setGui(String value) {
	            this.gui = value;
	        }
	    }

}
