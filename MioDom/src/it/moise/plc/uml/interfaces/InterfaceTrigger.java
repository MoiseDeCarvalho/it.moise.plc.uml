package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.event.Event;



public interface InterfaceTrigger {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public void setType(String type);
	public String getType();
	public String getName();
	public void setIdEvent(String idEvent);
	public String getIdEvent();
	
	public void setEvent (Event event);
	public Event getEvent();
	
}
