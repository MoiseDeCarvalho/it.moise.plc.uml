package it.moise.plc.uml.interfaces;

public interface InterfaceEffect <T> {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setIsLeaf(boolean isLeaf);
	public boolean getIsLeaf();
	public void setIsAbstract(boolean isAbstract);
	public boolean getIsAbstract();
	public void setIsActive(boolean isActive);
	public boolean getIsActive();
	public void setVisibility(String visibility);
	public String getVisibility();
}
