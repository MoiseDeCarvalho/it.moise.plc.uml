package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.attribute.Attribute;

import java.util.ArrayList;

public interface InterfaceSignal {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setVisibility(String visibility);
	public String getVisibility();
	public void setIsLeaf(boolean isLeaf);
	public boolean getIsLeaf();
	public void setIsAbstract(boolean isAbstract);
	public boolean getIsAbstract();
	public <T> void setAddListAttribute(Attribute<T> attribute);
	public ArrayList<Attribute >getListAttribute();
	
}
