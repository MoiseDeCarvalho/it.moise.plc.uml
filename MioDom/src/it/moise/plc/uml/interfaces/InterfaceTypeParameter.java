package it.moise.plc.uml.interfaces;

public interface InterfaceTypeParameter  extends InterfaceSignal{
	public void setIsActive(boolean isActive);
	public boolean getIsActive();
	public void setVisibility(String visibility);
	public String getVisibility();
}
