package it.moise.plc.uml.interfaces.event;

public interface InterfaceEvent <T> {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setSignal(String signal);
	public String getSignal();
	public void setVisibility(String visibility);
	public String getVisibility();

}
