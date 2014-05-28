package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Comment;
import it.moise.plc.uml.Transition;
import it.moise.plc.uml.pseudostate.Choice;
import it.moise.plc.uml.pseudostate.DeepHistory;
import it.moise.plc.uml.pseudostate.Final;
import it.moise.plc.uml.pseudostate.Fork;
import it.moise.plc.uml.pseudostate.Initial;
import it.moise.plc.uml.pseudostate.Join;
import it.moise.plc.uml.pseudostate.Junction;
import it.moise.plc.uml.pseudostate.ShallowHistory;
import it.moise.plc.uml.pseudostate.Terminate;

import java.util.ArrayList;


public interface InterfaceRegion {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	
	// indico a quale stato appartiene la regione
	public void setIdStateAppartenenza(String idState);
	public String getIdStateAppartenenza();
	
	// una region pu� contenere molti stati per cui creiamo una lista che contiene i loro ID
	public void setAddListIdState(String idState);
	public String getIdState(int index);
	public ArrayList<String> getListIdState();
	
	// possiamo avere molte transition all'interno di una regione ecco perch� le gestiamo con una lista	
	public void setAddTransition(Transition transition);
	public Transition getTransition(int index);
	public ArrayList<Transition> getListTransition();
	
	
	// possiamo avere molti vertex in una region ecco perche li gestiamo con una lista
	public void setAddVertex(String vertex);
	public String getVertex(int index);
	public ArrayList<String> getListVertex();
	
	
	
	// Una region ha un solo deepHistory
	public void setDeepHistory(DeepHistory deepHistory);
	public DeepHistory getDeepHistory();
	
	// Una region ha un solo shallowHistory
	public void setShallowHistory(ShallowHistory shallowHistory);
	public ShallowHistory getShallowHistory();
	
	// Una region ha un solo initial (Pseudostate)
	public void setInitialPseudoState(Initial initialPseudostate);
	public Initial getInitialPseudoState();
	
	// una region pu� avere diversi orthogonal state, quindi possono servire diversi join, ed usiamo una lista per gestirli
	public void setAddListJoin(Join join);
	public Join getJoin(int index);
	public ArrayList<Join> getListJoin();
	
	
	// una region pu� avere diversi orthogonal state, quindi possono servire diversi fork, ed usiamo una lista per gestirli
	public void setAddListFork(Fork fork);
	public Fork getFork(int index);
	public ArrayList<Fork> getListFork();
	
	
	// una region pu� avere diversi orthogonal state, quindi possono servire diversi junction, ed usiamo una lista per gestirli
	public void setAddListJunction(Junction junction);
	public Junction getJunction(int index);
	public ArrayList<Junction> getListJunction();
	
	
	// una region pu� avere diversi orthogonal state, quindi possono servire diversi choice, ed usiamo una lista per gestirli
	public void setAddListChoice(Choice choice);
	public Choice getChoice(int index);
	public ArrayList<Choice> getListChoice();
	
	
	// una regione pu� avere solo uno pseudostate terminate
	public void setTerminate(Terminate terminate);
	public Terminate getTerminate();
	
	// una regione pu� avere solo uno FinalState
	public void setFinalState(Final finalState);
	public Final getFinalState();
		
	public void setParent(String parent);
	public String getParent();
	
	public void setAddListComment(Comment comment);

	public ArrayList<Comment> getListComment();
	
}
