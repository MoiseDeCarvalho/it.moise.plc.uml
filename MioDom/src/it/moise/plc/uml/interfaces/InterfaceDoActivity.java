package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Comment;
import it.moise.plc.uml.Operation;
import it.moise.plc.uml.Reception;
import it.moise.plc.uml.Rule;
import it.moise.plc.uml.Signal;
import it.moise.plc.uml.Variable;

import java.util.ArrayList;

public interface InterfaceDoActivity {
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
	public void setIsReentrant(boolean isReentrant);
	public boolean getIsReentrant();
	public void setIsReadOnly(boolean isReadOnly);
	public boolean getIsReadOnly();
	public void setIsSingleExecution(boolean isSingleExecution);
	public boolean getIsSingleExecution();
	
	public void setIdPreCondition(String idPreCondition);
	public String[] getIdPreCondition();
		
	public void setAddListPreCondition(Rule preCondition);
	public  ArrayList<Rule> getListPreCondition();
	
	public void setIdPostCondition(String idPreCondition);
	public String[] getIdPostCondition();
	
	public void setAddListPostCondition(Rule postCondition);
	public ArrayList<Rule> getListPostCondition();
	
	public void setAddListOperation(Operation operation);
	public ArrayList<Operation> getListOperation();
	
	public void setAddListReception(Reception reception);
	public ArrayList<Reception> getListReception();
	
	public void setSpecification(String specification);
	public String getSpecification();
	
	public void setAddListVariable(Variable variable);
	public ArrayList<Variable> getListVariable();
	public void printListVariable(ArrayList<Variable> listVariable);
	
	public void setSignal(Signal signal);
	public Signal getSignal();
	
	public int getSizeListOperation();
	public int getSizeListReception();
	
	public void setComment(Comment comment);
	public Comment getComment();
	
	

}
