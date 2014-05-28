package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Comment;
import it.moise.plc.uml.value.Value;

public interface InterfaceSpecification {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setValue(String value);
	public String getValue();
	public void setComment(Comment comment);
	public Comment getComment();
}
