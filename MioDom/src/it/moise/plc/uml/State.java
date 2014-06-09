/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceState;
import it.moise.plc.uml.pseudostate.EntryPoint;
import it.moise.plc.uml.pseudostate.ExitPoint;
import it.moise.plc.uml.pseudostate.Final;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che costruisce un oggeto di tipo state che contiene tutte le informazioni riguardante uno stato
 * @author De Carvalho Moise
 *
 **/
public class State implements InterfaceState, Comparable<State> {
	private int level = -1;
	private String id = null;
	private String name = null;
	private String type = null ;
	private String parent = null;
	private boolean raggiungibile = false;
	private String idRegionAppartenenza = null;
	private String idStateLevelUp = "-1";
	private boolean isActive = false;
	private boolean isComposite = false;
	private boolean isLeaf = false;
	private boolean isOrthogonal = false;
	private boolean isSimpleState = false;
	private boolean isSubMachineState = false;
	
	//private Initial  initialState = new Initial();
	private EntryPoint entryPoint = new EntryPoint();
	private ExitPoint exitPoint = new ExitPoint();
	private Final finalState = new Final();
	private Activity entry = new Activity();
	private Activity exit = new Activity();
	private DoActivity doActivity = new DoActivity();
	
	private ArrayList<String> listIdRegion = new ArrayList<String>();
	private ArrayList<DeferrableTrigger> listDeferrableTrigger = new ArrayList<DeferrableTrigger>();
	
	private Vector<String> vectorSubState = new Vector<String>();
	/**
	 * Costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap da analizzare 
	 */
	public State(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				
				switch (temp[0]){
				case "name":
					String tmp = node.item(i).getNodeValue();
					tmp = tmp.replace("-|.|!| ", "_");
					tmp = tmp.replace(" ", "_");
					this.setName(tmp);
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					break;
				
					
				}
			}
		}
	}
	
	/**
	 * Costruttore senza parametri
	 **/
	public State() {
	}

	/**
	 * Implementa il metodo da Collections per effettuare l'ordinamento
	 * @author De Carvalho Moise
	 *
	 */
	static class LevelComparator implements Comparator<State> {
		@Override
		public int compare(State s1, State s2) {
			int result;
			result = s1.getLevel() - s2.getLevel();
			return result;
			}
	}
	/**
	 * Implementa il metodo da Collections per effettuare l'ordinamento
	 * @author De Carvalho Moise
	 *
	 */
	static class IdStateComparator implements Comparator<State> {
		@Override
		public int compare(State s1, State s2) {
			int result;
			result = s1.getId().compareTo(s2.getId());
			return result;
			}
	}
	
	/**
	 * Set l'id dello State
	 * @param id Id dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id dello State
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name dello State
	 * @param name Nome dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il name  dello State
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il type dello State
	 * @param type Tipo dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	
	/**
	 * Set se è raggiungibile lo State
	 * @param raggiungibile Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setRaggiungibile(boolean)
	 **/
	@Override
	public void setRaggiungibile(boolean raggiungibile) {
		this.raggiungibile = raggiungibile;

	}

	/**
	 * Restituisce il raggiungibile dello state
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getRaggiungibile()
	 **/
	@Override
	public boolean getRaggiungibile() {
		// 
		return this.raggiungibile;
	}
	/**
	 * Indica l'id della regione di appartenenza
	 * @param idRegionAppartenenza Id della regione di appartenenza
	 */
	@Override
	public void setIdRegionAppartenenza(String idRegionAppartenenza){
		this.idRegionAppartenenza = idRegionAppartenenza;
	}
	/**
	 * Restituisce l'id della regione di apparenenza
	 */
	@Override
	public String getIdRegionAppartenenza(){
		return this.idRegionAppartenenza;
	}
	
	
	/** 
	 * Metodo che permette di aggiungere ud IdRegion che uno stato può avere
	 * @param idRegion Valore dell'id della regione
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setAddListIdRegion(java.lang.String)
	 ***/
	@Override
	public void setAddListIdRegion(String idRegion) {
		this.listIdRegion.add(idRegion);
	}

	/** 
	 * Restituisce un oggetto idRegion dalla lista delle idRegion
	 * @param index Indice
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getIdRegion(int)
	 ***/
	@Override
	public String getIdRegion(int index) {
		// 
		return this.listIdRegion.get(index);
	}

	/** 
	 * Restituisce la lista listIdRegion
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getListIdRegion()
	 ***/
	@Override
	public ArrayList<String> getListIdRegion() {
		// 
		return this.listIdRegion;
	}
	
	
	
	/**
	 * Set il valore di isComposite
	 * @param value Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setIsCompositeState(boolean)
	 **/
	@Override
	public void setIsCompositeState(boolean value) {
		this.isComposite = value;

	}

	/**
	 * Restituisce il valore di isComposite
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getIsCompositeState()
	 **/
	@Override
	public boolean getIsCompositeState() {
		// 
		return this.isComposite;
	}

	/**
	 * Set il valore di isOthogonalState
	 * @param value Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setIsOrthogonalState(boolean)
	 **/
	@Override
	public void setIsOrthogonalState(boolean value) {
		this.isOrthogonal = value;

	}

	/**
	 * Restituisce il valore di isOrthogonalState
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getIsOrthogonalState()
	 **/
	@Override
	public boolean getIsOrthogonalState() {
		// 
		return this.isOrthogonal;
	}

	/**
	 * Set il valore di isSimplestate
	 * @param value Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setIsSimpleState(boolean)
	 **/
	@Override
	public void setIsSimpleState(boolean value) {
		this.isSimpleState = value;

	}

	/**
	 * Restituisce il valore di isSimpleState
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getIsSimpleState()
	 **/
	@Override
	public boolean getIsSimpleState() {
		// 
		return this.isSimpleState;
	}

	/**
	 * Set il valore di isSubMachineState
	 * @param value Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setIsSubMachineState(boolean)
	 **/
	@Override
	public void setIsSubMachineState(boolean value) {
		this.isSubMachineState = value;

	}

	/**
	 * Restituisce il valore di isSubMachineState
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getIsSubMachineState()
	 **/
	@Override
	public boolean getIsSubMachineState() {
		// 
		return this.isSubMachineState;
	}

	/**
	 * Set il valore di isLeaf
	 * @param value Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setIsLeaf(boolean)
	 **/
	@Override
	public void setIsLeaf(boolean value) {
		this.isLeaf = value;

	}

	/**
	 * Restituisce il valore di isLeaf
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getIsLeaf()
	 **/
	@Override
	public boolean getIsLeaf() {
		// 
		return this.isLeaf;
	}

	/**
	 * Set il valore di isActiveState
	 * @param value Valore booleano
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setIsActive(boolean)
	 **/
	@Override
	public void setIsActive(boolean value) {
		this.isActive = value;

	}

	/**
	 * Restituisce il valore di isActive
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getIsActive()
	 **/
	@Override
	public boolean getIsActive() {
		// 
		return this.isActive;
	}

	/**
	 * Set l'entryPoint
	 * @param entryPoint Oggetto di tipo EntryPoint
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setEntryPoint(it.moise.plc.uml.pseudostate.EntryPoint)
	 **/
	@Override
	public void setEntryPoint(EntryPoint entryPoint) {
		this.entryPoint = entryPoint;

	}

	/**
	 * Restituisce 'lentryPoint
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getEntryPoint()
	 **/
	@Override
	public EntryPoint getEntryPoint() {
		// 
		return this.entryPoint;
	}
	
	/**
	 * Set l'exitPoint
	 * @param exitPoint Oggetto di tipo EntryPoint
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setExitPoint(it.moise.plc.uml.pseudostate.ExitPoint)
	 **/
	@Override
	public void setExitPoint(ExitPoint exitPoint) {
		this.exitPoint = exitPoint;

	}

	/**
	 * Restituisce l'l'exitPoint
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getExitPoint()
	 **/
	@Override
	public ExitPoint getExitPoint() {
		// 
		return this.exitPoint;
	}
	
	
	/**
	 * Aggiunge un nodo alla lista listDeferrableTrigger
	 * @param deferrableTrigger Oggetto di tipo DeferrableTrigger 
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setAddDeferrableTrigger(it.moise.plc.uml.DeferrableTrigger)
	 **/
	@Override
	public void setAddDeferrableTrigger(DeferrableTrigger deferrableTrigger) {
		this.listDeferrableTrigger.add(deferrableTrigger);

	}
	/**
	 * Restituisce un DeferrableTrigger indicato dall'indice
	 * @param index Indice 
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getDeferrableTrigger(int)
	 **/
	@Override
	public DeferrableTrigger getDeferrableTrigger(int index){
		return this.listDeferrableTrigger.get(index);
	}
	
	/**
	 * Restituisce la lista listDeferrableTrigger
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getListDeferrableTrigger()
	 **/
	@Override
	public ArrayList<DeferrableTrigger> getListDeferrableTrigger() {
		// 
		return this.listDeferrableTrigger;
	}

	/**
	 * Stampa la lista listDeferrableTrigger
	 * @param deferrableTrigger Lista dei deferrabletrigger
	 * @see it.moise.plc.uml.interfaces.InterfaceState#printListDeferrableTrigger(java.util.ArrayList)
	 **/
	@Override
	public void printListDeferrableTrigger(
			ArrayList<DeferrableTrigger> deferrableTrigger) {
		// 

	}

	/**
	 * Set il doActivity dello State
	 * @param doActivity Oggetto di tipo DoActivity 
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setDoActivity(it.moise.plc.uml.DoActivity)
	 **/
	@Override
	public void setDoActivity(DoActivity doActivity) {
		this.doActivity = doActivity;

	}

	/**
	 * Restituisce il doActivity
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getDoActivity()
	 **/
	@Override
	public DoActivity getDoActivity() {
		// 
		return this.doActivity;
	}

	/**
	 * Set il valore di finalState
	 * @param finalState Oggetto di tipo Final 
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setFinalState(it.moise.plc.uml.pseudostate.Final)
	 */
	@Override
	public void setFinalState(Final finalState) {
		this.finalState = finalState;

	}

	/**
	 * Restituisce il valore di finalState
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getFinalState()
	 **/
	@Override
	public Final getFinalState() {
		// 
		return this.finalState;
	}

	/**
	 * Set il valore di entry 
	 * @param entry Oggetto di tipo Activity
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setEntry(it.moise.plc.uml.Activity)
	 **/
	@Override
	public void setEntry(Activity entry) {
		this.entry = entry;

	}

	/**
	 * Restituisce il valore di entry
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getEntry()
	 **/
	@Override
	public Activity getEntry() {
		// 
		return this.entry;
	}

	/**
	 * Set il valore di Exit
	 * @param exit Oggetto di tipo Activity
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setExit(it.moise.plc.uml.Activity)
	 **/
	@Override
	public void setExit(Activity exit) {
		this.exit = exit;

	}

	/**
	 * Restituisce il valore di exit
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getExit()
	 **/
	@Override
	public Activity getExit() {
		// 
		return this.exit;
	}


	@Override
	/**
	 * Set il valore di parent
	 * @param parent Indica l'id del paren a cui è collegato
	 * @see it.moise.plc.uml.interfaces.InterfaceState#setParent(java.lang.String)
	 **/
	public void setParent(String parent){
		this.parent = parent;
	}
	
	@Override
	/**
	 * Restituisce il valore di parent
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getParent()
	 **/
	public String getParent(){
		return this.parent;
	}

	/**
	 * Imposta il livello di appartenenza
	 * @param level Indica il livello
	 *  @see it.moise.plc.uml.interfaces.InterfaceState#setLevel(int)
	 */
	@Override
	public void setLevel(int level){
		this.level = level;
	}
	/**
	 * Restituisce il livello di appartenenza
	 * @see it.moise.plc.uml.interfaces.InterfaceState#getLevel()
	 */
	@Override
	public Integer getLevel(){
		return Integer.valueOf(this.level);
	}
	
	/**
	 * Imposta l'id dello stato superiore
	 * @param idStateLevelUp Valore dell'ide dello stato di livello superiore
	 */
	public void setIdStateLevelUp(String idStateLevelUp){
		this.idStateLevelUp = idStateLevelUp;
	}
	
	/**
	 * Restituisce il valore dell'id dello stato di livello superiore
	 * @return idStateLevelUp Valore dell'id del livello superiore
	 */
	public String getIdStateLevelUp(){
		return this.idStateLevelUp;
	}

	/**
	 * Implementa il metodo per eseguire l'ordinamento
	 * 
	 */
	@Override
	public int compareTo(State arg0) {
		// 
		return 0;
	}
	
	/**
	 * Aggiunge un id dello stato che è un substate
	 * @param idState Valore dell'id del substate
	 */
	public void setAddVectorSubState(String idState){
		this.vectorSubState.add(idState);
		}
	/**
	 * Restituisce la lista degli substate
	 * @return vectorSubState Vettore dei substate
	 */
	public Vector<String> getVectorSubState(){
		return this.vectorSubState;
	}
}
