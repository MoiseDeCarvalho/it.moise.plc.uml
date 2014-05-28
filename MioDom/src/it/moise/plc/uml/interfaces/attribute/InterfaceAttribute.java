package it.moise.plc.uml.interfaces.attribute;

import it.moise.plc.uml.value.Value;

public interface InterfaceAttribute <T>{
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setIsLeaf(boolean isLeaf);
	public boolean getIsLeaf();
	public void setIsOrdered(boolean isOrdered);
	public boolean getIsOrdered();
	public void setIsDerived(boolean isDerived);
	public boolean getIsDerived();
	public void setIsDerivedUnion(boolean isDerivedUnion);
	public boolean getIsDerivedUnion();
	public void setDefaulValue(Value defaultValue);
	public Value getDefalutValue();
}
