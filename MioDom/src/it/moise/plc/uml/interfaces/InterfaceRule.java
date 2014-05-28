package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Comment;
import it.moise.plc.uml.Specification;

public interface InterfaceRule {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setConstrainedElement(String contrainedElement);
	public String[] getConstrainedElement();
	public void setComment(Comment comment);
	public Comment getComment();
	public void setSpecification(Specification specification);
	public Specification getSpecification();
	
}
