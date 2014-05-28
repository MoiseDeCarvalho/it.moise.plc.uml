package it.moise.plc.uml.interfaces.attribute;

import it.moise.plc.uml.value.Value;

public interface InterfaceExtensionEnd extends it.moise.plc.uml.interfaces.attribute.InterfaceAttribute {
	public void setIsStatic(boolean isBehavior);
	public boolean getIsStatic();
	public void setSubsettedProperty(String subsettedProperty);
	public String getSubsettedProperty();
	public void setIsReadOnly(boolean isReadOnly);
	public boolean getIsReadOnly();
}
