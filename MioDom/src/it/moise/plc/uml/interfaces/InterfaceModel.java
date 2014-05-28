package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.ClassDiagram;
import it.moise.plc.uml.PackageImport;
import it.moise.plc.uml.StateMachine;
import it.moise.plc.uml.event.AnyReceiveEvent;
import it.moise.plc.uml.event.CallEvent;
import it.moise.plc.uml.event.ChangeEvent;
import it.moise.plc.uml.event.SignalEvent;
import it.moise.plc.uml.event.TimeEvent;

import java.util.ArrayList;



public interface InterfaceModel {
	public void setName(String name);
	public String getName();
	
	public void setId(String id);
	public String getId();
	
	public void setVersion(String version);
	public String getVersion();
	
	public void setUml(String uml);
	public String getUml();
	
	public void setXmi(String xmi);
	public String getXmi();
	
	public void setPackageImport(PackageImport packageImport);
	public PackageImport getPackageImport();
	
	public void setStateMachine(StateMachine stateMachine);
	public StateMachine getStateMachine();
	
	public void setAddListCallEvent(CallEvent callEvent);
	public ArrayList<CallEvent> getListCallEvent();
	public void printListCallEvent(ArrayList<CallEvent> callEvent);
	
	public void setAddListChangeEvent(ChangeEvent changeEvent);
	public ArrayList<ChangeEvent> getListChangeEvent();
	
	
	public void setAddListSignalEvent(SignalEvent signalEvent);
	public ArrayList<SignalEvent> getListSignalEvent();
	
	
	public void setAddListTimeEvent(TimeEvent timeEvent);
	public ArrayList<TimeEvent> getListTimeEvent();
	
	
	public void setAddListAnyReceiveEvent(AnyReceiveEvent anyReceiveEvent);
	public ArrayList<AnyReceiveEvent> getListAnyReceiveEvent();
	
	
	public void setClassDiagram(ClassDiagram classDiagram);
	public ClassDiagram getClassDiagram();
}
