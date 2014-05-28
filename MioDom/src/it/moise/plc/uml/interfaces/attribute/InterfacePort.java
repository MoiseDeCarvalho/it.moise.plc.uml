package it.moise.plc.uml.interfaces.attribute;

import it.moise.plc.uml.value.Value;

public interface InterfacePort extends it.moise.plc.uml.interfaces.attribute.InterfaceAttribute {
	public void setIsBehavior(boolean isBehavior);
	public boolean getIsBehavior();
	public void setIsConjugated(boolean isConjugaded);
	public boolean getIsConjugated();
}
