//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.03 at 01:06:50 PM CET 
//


package org.plcopen.xml.tc6_0201;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for edgeModifierType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="edgeModifierType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="none"/>
 *     &lt;enumeration value="falling"/>
 *     &lt;enumeration value="rising"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "edgeModifierType")
@XmlEnum
public enum EdgeModifierType {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("falling")
    FALLING("falling"),
    @XmlEnumValue("rising")
    RISING("rising");
    private final String value;

    EdgeModifierType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EdgeModifierType fromValue(String v) {
        for (EdgeModifierType c: EdgeModifierType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
