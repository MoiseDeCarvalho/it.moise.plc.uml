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
 * <p>Java class for storageModifierType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="storageModifierType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="none"/>
 *     &lt;enumeration value="set"/>
 *     &lt;enumeration value="reset"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "storageModifierType")
@XmlEnum
public enum StorageModifierType {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("set")
    SET("set"),
    @XmlEnumValue("reset")
    RESET("reset");
    private final String value;

    StorageModifierType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StorageModifierType fromValue(String v) {
        for (StorageModifierType c: StorageModifierType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
