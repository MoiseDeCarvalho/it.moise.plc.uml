package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Parameter;

import java.util.ArrayList;

public interface InterfaceReception {
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
	public void setIsStatic(boolean isStatic);
	public boolean getIsStatic();
	public void setIsAbstract(boolean isAbstract);
	public boolean getIsAbstract();
	public void setMethod(String idEffect);
	public String getMethod();
	public void setSignalId(String idSignal);
	public String getSignalId();
	
	public void setAddListParameter(Parameter parameter);
	public ArrayList<Parameter> getListParameter();
}
