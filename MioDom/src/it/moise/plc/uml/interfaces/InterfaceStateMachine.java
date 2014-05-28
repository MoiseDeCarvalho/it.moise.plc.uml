package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Operation;
import it.moise.plc.uml.Region;
import it.moise.plc.uml.Signal;
import it.moise.plc.uml.Specification;
import it.moise.plc.uml.State;
import it.moise.plc.uml.Transition;
import it.moise.plc.uml.event.SignalEvent;
import it.moise.plc.uml.pseudostate.Choice;
import it.moise.plc.uml.pseudostate.DeepHistory;
import it.moise.plc.uml.pseudostate.EntryPoint;
import it.moise.plc.uml.pseudostate.ExitPoint;
import it.moise.plc.uml.pseudostate.Final;
import it.moise.plc.uml.pseudostate.Fork;
import it.moise.plc.uml.pseudostate.Initial;
import it.moise.plc.uml.pseudostate.Join;
import it.moise.plc.uml.pseudostate.Junction;
import it.moise.plc.uml.pseudostate.ShallowHistory;
import it.moise.plc.uml.pseudostate.Terminate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public interface InterfaceStateMachine {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setIdSpecification(String idSpecification);
	public String getIdSpecification();
	
	public void setSpecification(Specification specification);
	public Specification getSpecification();
	
	public void setAddRegion(Region region);
	public ArrayList<Region> getListRegion();
	public void printListRegion(ArrayList<Region> listRegion);	
	
	public void setAddState(State state);
	public ArrayList<State> getListState();
	public void printListState(ArrayList<State> listState);
	
	public void setAddMapRegion(String id, String name);
	public HashMap getMapRegion();
	
	public void setAddMapState(String id, String name);
	public HashMap<String,String> getMapState();
	
	public void setAddOperation(Operation operation);
	public ArrayList<Operation> getListOperation();
	public void printListOperation(ArrayList<Operation> listOperation);
	
	public void setAddSignal(Signal signal);
	public ArrayList<Signal> getListSignal();
	public void printListSignal(ArrayList<Signal> listSignal);
	
	public void setAddTransition(Transition transition);
	public ArrayList<Transition> getListTransition();
	public void printListTransition(ArrayList<Transition> listTransition);
	
	public void setAddInitial(Initial initial);
	public ArrayList<Initial> getListInitial();
	public void printListInitial(ArrayList<Initial> listInitial);
	
	public Region getRegionFromListRegion(String idRegion);
	
	public void setAddChoice(Choice choice);
	public Vector<Choice> getVectorChoice();
	
	public void setAddDeepHistory(DeepHistory deepHistory);
	public Vector<DeepHistory> getVectorDeepHistory();
	
	public void setAddEntryPoint(EntryPoint entryPoint);
	public Vector<EntryPoint> getVectorEntryPoint();
	
	public void setAddExitPoint(ExitPoint exitPoint);
	public Vector<ExitPoint> getVectorExitPoint();
	
	public void setAddFinal(Final finals);
	public Vector<Final> getVectorFinal();
	
	public void setAddJoin(Join join);
	public Vector<Join> getVectorJoin();
	
	public void setAddFork(Fork fork);
	public Vector<Fork> getVectorFork();
	
	public void setAddShallowHistory(ShallowHistory shallowHistory);
	public Vector<ShallowHistory> getVectorShallowHistory();
	
	public void setAddTerminate(Terminate terminate);
	public Vector<Terminate> getVectorTerminate();
	
	public void setAddJunction(Junction junction);
	public Vector<Junction> getVectorJunction();
}
