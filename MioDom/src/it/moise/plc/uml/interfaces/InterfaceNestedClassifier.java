package it.moise.plc.uml.interfaces;

import java.util.ArrayList;

public interface InterfaceNestedClassifier {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setIsActive(boolean isActive);
	public boolean getIsActive();
	public void setTypeNestedClassifier(Object nestedClassifier);
	public Object getTypeNestedClassifier();
}
