package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Comment;
import it.moise.plc.uml.Operation;
import it.moise.plc.uml.Reception;
import it.moise.plc.uml.Rule;
import it.moise.plc.uml.Signal;
import it.moise.plc.uml.Variable;

import java.util.ArrayList;

public interface InterfaceActivity extends it.moise.plc.uml.interfaces.InterfaceEffect {
	public void setIsReadyOnly(boolean isReadyOnly);
	public boolean getIsReadyOnly();
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
	
	public void setAddListVariable(Variable variable);
	public ArrayList<Variable> getListVariable();
	
	public void setSignal(Signal signal);
	public Signal getSignal();
	// Operation or REception equivale al campo specification
	public void setAddListOperation(Operation operation);
	public ArrayList<Operation> getListOperation();
	
	public void setAddListReception(Reception reception);
	public ArrayList<Reception> getListReception();
	
	public void setSpecification(String specification);
	public String getSpecification();
	
	public void setComment(Comment comment);
	public Comment getComment();
}
