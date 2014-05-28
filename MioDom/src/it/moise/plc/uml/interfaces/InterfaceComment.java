package it.moise.plc.uml.interfaces;

public interface InterfaceComment {
	public void setId(String id);
	public String getId();
	public void setType(String type);
	public String getType();
	
	public void setBody(String body);
	public String getBody();
	public void setAnnotatedElement(String annotatedElement);
	public String[] getAnnotatedElement();
	public void setIdRegionAppartenenza(String value);
	public String getIdRegionAppartenenza();
	public void setIdStateAppartenenza(String value);
	public String getIdStateAppartenenza();
	
}
