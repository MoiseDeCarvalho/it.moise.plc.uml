package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Activity;
import it.moise.plc.uml.DeferrableTrigger;
import it.moise.plc.uml.DoActivity;
import it.moise.plc.uml.Region;
import it.moise.plc.uml.connection_point.ConnectionPoint;
import it.moise.plc.uml.connection_point.Entry;
import it.moise.plc.uml.connection_point.Exit;
import it.moise.plc.uml.pseudostate.EntryPoint;
import it.moise.plc.uml.pseudostate.ExitPoint;
import it.moise.plc.uml.pseudostate.Final;
import it.moise.plc.uml.pseudostate.Initial;

import java.util.ArrayList;

public interface InterfaceState {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	
	public void setRaggiungibile(boolean raggiungibile);
	public boolean getRaggiungibile();
	
	public void setIdRegionAppartenenza(String idRegion);
	public String getIdRegionAppartenenza();
	
	// uno State pu� contenere molti region per cui creiamo una lista che contiene i loro ID
	public void setAddListIdRegion(String idRegion);
	public String getIdRegion(int index);
	public ArrayList<String> getListIdRegion();
	
	
	public void setIsCompositeState(boolean value);
	public boolean getIsCompositeState();
	public void setIsOrthogonalState(boolean value);
	public boolean getIsOrthogonalState();
	public void setIsSimpleState(boolean value);
	public boolean getIsSimpleState();
	public void setIsSubMachineState(boolean value);
	public boolean getIsSubMachineState();
	public void setIsLeaf(boolean value);
	public boolean getIsLeaf();
	public void setIsActive(boolean value);
	public boolean getIsActive();
	
	public void setEntryPoint(EntryPoint entryPoint);
	public EntryPoint getEntryPoint();
	
	public void setExitPoint(ExitPoint exitPoint);
	public ExitPoint getExitPoint();
	
	public void setAddDeferrableTrigger(DeferrableTrigger deferrableTrigger);
	public DeferrableTrigger getDeferrableTrigger(int index);
	public ArrayList<DeferrableTrigger> getListDeferrableTrigger();
	public void printListDeferrableTrigger(ArrayList<DeferrableTrigger> deferrableTrigger);
	
	public void setDoActivity(DoActivity doActivity);
	public DoActivity getDoActivity();
	
	public void setFinalState(Final finalState);
	public Final getFinalState();
	
	public void setEntry(Activity entry);
	public Activity getEntry();
	
	public void setExit(Activity exit);
	public Activity getExit();
	
	public void setParent(String parent);
	public String getParent();
	
	public void setLevel(int level);
	public Integer getLevel();
	
	//public ArrayList<Region> getListRegion();
	//public void printListRegion(ArrayList<Region> listRegion);
	
	
}
