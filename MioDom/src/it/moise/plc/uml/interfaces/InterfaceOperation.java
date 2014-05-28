package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Comment;
import it.moise.plc.uml.Parameter;
import it.moise.plc.uml.Rule;

import java.util.ArrayList;

public interface InterfaceOperation {
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
	public void setCuncurrency(String cuncurrency);
	public String getCuncurrency();
	public void setIsAbstract(boolean isAbstract);
	public boolean getIsAbstract();
	public void setMethod(String method);
	public String getMethod();
	public void setIdBodyCondition(String idBodyCondition);
	public String getIdBodyCondition();
	public void setBodyCondition(Rule bodyCondition);
	public Rule getBodyCondition();
	public void setIsQuery(boolean isQuery);
	public boolean getIsQuery();
	public void setIdPreCondition(String idPreCondition);
	public String[] getIdPreCondition();
	public void setAddListPreCondition(Rule preCondition);
	public  ArrayList<Rule> getListPreCondition();
	public void setIdPostCondition(String idPreCondition);
	public String[] getIdPostCondition();
	public void setAddListPostCondition(Rule postCondition);
	public ArrayList<Rule> getListPostCondition();
	public void setAddListParameter(Parameter parameter);
	public ArrayList<Parameter> getListParameter();
	public void setComment(Comment comment);
	public Comment getComment();
}
