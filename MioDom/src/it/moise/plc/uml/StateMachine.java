/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceStateMachine;
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
import java.util.Vector;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che implementa uno oggetto di StateMachine
 * @author De Carvalho Moise
 *
 **/
public class StateMachine implements InterfaceStateMachine{
	private String id = null;
	private String name = null;
	private String type = null;
	private String idRegion = null;
	private String nameRegion = null;
	private String idState = null;
	private String nameState = null;
	private String idSpecification="";
	
	private Specification specification = new Specification();
	private HashMap mapRegion = new HashMap();
	private HashMap mapState = new HashMap();
	
	private ArrayList<Region> listRegion = new ArrayList<Region>();
	private ArrayList<State> listState = new ArrayList<State>();
	
	private ArrayList<Operation> listOperation = new ArrayList<Operation>();
	private ArrayList<Signal> listSignal = new ArrayList<Signal>();
	private ArrayList<Transition> listTransition = new ArrayList<Transition>();
	private ArrayList<StateLevel> listStateLevel = new ArrayList<StateLevel>();
	private ArrayList<Initial> listInitial = new ArrayList<Initial>();
	
	private Vector<Choice> vectorChoice = new Vector<Choice>();
	private Vector<DeepHistory> vectorDeepHistory = new Vector<DeepHistory>();
	private Vector<EntryPoint> vectorEntryPoint = new Vector<EntryPoint>();
	private Vector<ExitPoint> vectorExitPoint = new Vector<ExitPoint>();
	private Vector<Final> vectorFinal = new Vector<Final>();
	private Vector<Fork> vectorFork = new Vector<Fork>();
	private Vector<Join> vectorJoin = new Vector<Join>();
	private Vector<ShallowHistory> vectorShallowHistory = new Vector<ShallowHistory>();
	private Vector<Terminate> vectorTerminate = new Vector<Terminate>();
	private Vector<Junction> vectorJunction = new Vector<Junction>();
	
	private Vector<Variable> vectorVariable = new Vector<Variable>();
	private ArrayList<PrimitiveType> vectorPrimitiveType = new ArrayList<PrimitiveType>();
	
	/**
	 * Costruttore con parametri 
	 * @param node Oggetto di tipo NamedNodeMap da analizzare
	 **/
	public StateMachine(NamedNodeMap node) {
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
					this.setName(node.item(i).getNodeValue());
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					break;
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "specification":
					this.setIdSpecification(node.item(i).getNodeValue());
					break;
				}
			}
		}
	}

	public StateMachine() {
	}
	/**
	 * Set l'id dello StateMachine
	 * @param id Id dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il name dello StateMachine
	 * @param name Nome dell'oggetto 
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setName(java.lang.String)
	 **/
	@Override
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Restituisce il nome
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getName()
	 **/
	@Override
	public String getName() {
		// 
		return this.name;
	}

	/**
	 * Set il type
	 * @param type Tipo dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set l'idSpecification StateMachine
	 * @param idSpecification Id della specification 
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setIdSpecification(java.lang.String)
	 **/
	@Override
	public void setIdSpecification(String idSpecification) {
		this.idSpecification = idSpecification;

	}

	/**
	 * Restituisce l'idSpecification
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getIdSpecification()
	 **/
	@Override
	public String getIdSpecification() {
		// 
		return this.idSpecification;
	}
	/**
	 * Set specification
	 * @param specification Oggetto di tipo Specification
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setSpecification(it.moise.plc.uml.Specification)
	 **/
	@Override
	public void setSpecification(Specification specification) {
		this.specification = specification;

	}
	
	/**
	 * Restituisce specification
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getSpecification()
	 **/
	@Override
	public Specification getSpecification() {
		return this.specification;

	}
	/**
	 * Aggiunge un nodo alla lista listState
	 * @param state Oggetto di tipo State
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddState(it.moise.plc.uml.State)
	 **/
	@Override
	public void setAddState(State state) {
		this.listState.add(state);

	}

	/**
	 * Restituisce la lista listState
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getListState()
	 **/
	@Override
	public ArrayList<State> getListState() {
		// 
		return this.listState;
	}

	/**
	 * print la lista listState
	 * @param listState Lista degli stati
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#printListState(java.util.ArrayList)
	 **/
	@Override
	public void printListState(ArrayList<State> listState) {
		/**
		 * 
		 * devo stampare la lista listState
		 */

	}
	
	
	/**
	 * Aggiunge un nodo alla lista listRegion
	 * @param region Oggetto di tipo region
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddRegion(it.moise.plc.uml.Region)
	 **/
	@Override
	public void setAddRegion(Region region) {
		this.listRegion.add(region);

	}

	/**
	 * Restituisce la lista listRegion
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getListRegion()
	 **/
	@Override
	public ArrayList<Region> getListRegion() {
		// 
		return this.listRegion;
	}

	/**
	 * print la lista listRegion
	 * @param listRegion Lista delle regioni
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#printListRegion(java.util.ArrayList)
	 **/
	@Override
	public void printListRegion(ArrayList<Region> listRegion) {
		/**
		 * 
		 * devo stampare la lista
		 */

	}
	
	
	/**
	 * Aggiunge un elemento alla mappa mapRegion
	 * @param idRegion Id della regione
	 * @param nameRegion Nome della regione
	 **/
	@Override
	public void setAddMapRegion(String idRegion, String nameRegion) {
		this.mapRegion.put(idRegion, nameRegion);
	}
	
	/**
	 * Restituisce la mappa mapRegion
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getMapRegion()
	 **/
	@Override
	public HashMap getMapRegion() {
		return this.mapRegion;
	}
	
	
	/**
	 * Aggiunge un elemento alla mappa mapState
	 * @param idState Id dello stato
	 * @param nameState Nome dello stato
	 **/
	@Override
	public void setAddMapState(String idState, String nameState) {
		this.mapState.put(idState, nameState);
	}
	
	/**
	 * Restituisce la mappa mapState
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getMapRegion()
	 **/
	@Override
	public HashMap getMapState() {
		return this.mapState;
	}
	
	
	/**
	 * Aggiunge un nuovo nodo alla lista listOperation
	 * @param operation Oggetto di tipo Operation
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddOperation(it.moise.plc.uml.Operation)
	 **/
	@Override
	public void setAddOperation(Operation operation) {
		this.listOperation.add(operation);
	}

	/**
	 * Restituisce la lista listOperation
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getListOperation()
	 **/
	@Override
	public ArrayList<Operation> getListOperation() {
		// 
		return this.listOperation;
	}

	/**
	 * Stampa la lista listOperation
	 * @param listOperation listOperation Lista delle operation
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#printListOperation(java.util.ArrayList)
	 **/
	@Override
	public void printListOperation(ArrayList<Operation> listOperation) {
		// 
		/**
		 * 
		 * Stampare la lista
		 */

	}

	/**
	 * Aggiunge un nodo alla lista listSignal
	 * @param signal Oggetto di tipo  Signal
	 **/
	@Override
	public void setAddSignal(Signal signal) {
		this.listSignal.add(signal);

	}

	/**
	 * Restituisce la lista listSignal
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getListSignal()
	 **/
	@Override
	public ArrayList<Signal> getListSignal() {
		// 
		return this.listSignal;
	}

	/**
	 * Stampa la lista listSignal
	 * @param listSignal Lista dei signal
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#printListSignal(java.util.ArrayList)
	 **/
	@Override
	public void printListSignal(ArrayList<Signal> listSignal) {
		// 
		/**
		 * 
		 * Stampa la lista
		 * 
		 */

	}
	
	
	/**
	 * Aggiunge un nodo alla lista listTransition
	 * @param transition Oggetto di tipo transition
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddTransition(it.moise.plc.uml.Transition)
	 **/
	@Override
	public void setAddTransition(Transition transition) {
		this.listTransition.add(transition);

	}

	/**
	 * Restituisce la lista listTransition
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getListTransition()
	 **/
	@Override
	public ArrayList<Transition> getListTransition() {
		// 
		return this.listTransition;
	}

	/**
	 * Stampa la lista listTransition
	 * @param listTransition Lista delle transition
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#printListSignal(java.util.ArrayList)
	 **/
	@Override
	public void printListTransition(ArrayList<Transition> listTransition) {
		// 
		/**
		 * 
		 * Stampa la lista
		 * 
		 */

	}
	
	
	/**
	 * Aggiunge un nodo alla lista listInitial
	 * @param initial Oggetto di tipo Initial
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddInitial(it.moise.plc.uml.pseudostate.Initial)
	 **/
	@Override
	public void setAddInitial(Initial initial) {
		this.listInitial.add(initial);

	}

	/**
	 * Restituisce la lista listInitial
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getListInitial()
	 **/
	@Override
	public ArrayList<Initial> getListInitial() {
		// 
		return this.listInitial;
	}

	/**
	 * Stampa la lista listInitial
	 * @param listInitial Lista initial
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#printListInitial(java.util.ArrayList)
	 **/
	@Override
	public void printListInitial(ArrayList<Initial> listInitial) {
		// 
		/**
		 * 
		 * Stampa la lista
		 * 
		 */

	}
	
	/**
	 * CAggiunge un oggetto di tipo state alla lista
	 * @param  stateLevel Oggetto di tipo StateLevel
	 */
	
	public void setAddListStateLevel(StateLevel stateLevel){
		this.listStateLevel.add(stateLevel);
	}
	/**
	 * Restituisce la lista
	 * @return listStateLevel Lista degli stateLevel
	 */
	public ArrayList<StateLevel> getListStateLevel(){
		return this.listStateLevel;
	}
	
	/** Restituisce un oggetto Region dalla listRegion, passando il suo id come parametro
	 * @param idRegion Id della regione
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#getRegionFromListRegion(java.lang.String)
	 */
	@Override
	public Region getRegionFromListRegion(String idRegion){
		Region region = new Region();
		for (Region item : this.listRegion){
			if (item.getId().equals(idRegion)){
				region = item;
			}
		}
		return region;
	}

	
	/** Aggiunge un elemento al vettore vectorChoice
	 * @param choice Oggetto di tipo Choice
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddChoice(it.moise.plc.uml.pseudostate.Choice)
	 */
	@Override
	public void setAddChoice(Choice choice){
		this.vectorChoice.add(choice);
	}
	/**
	 * Restituisce il vettore vectorChoice
	 * @return vectorChoice Vettore lista di oggetti choice
	 */
	@Override
	public Vector<Choice> getVectorChoice(){
		return this.vectorChoice;
	}
	
	
	/** Aggiunge un elemento al vettore vectorDeepHistory
	 * @param deepHistory Oggetto di tipo DeepHistory
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddDeepHistory(it.moise.plc.uml.pseudostate.DeepHistory)
	 */
	@Override
	public void setAddDeepHistory(DeepHistory deepHistory){
		this.vectorDeepHistory.add(deepHistory);
	}
	/**
	 * Restituisce il vettore vectorDeepHistory
	 * @return vectorDeepHistory Vettore lista di Oggetti Deephistory
	 */
	@Override
	public Vector<DeepHistory> getVectorDeepHistory(){
		return this.vectorDeepHistory;
	}
	
	
	/** Aggiunge un elemento al vettore vectorEntryPoint
	 * @param entryPoint Oggetto di tipo EntryPoint
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddEntryPoint(it.moise.plc.uml.pseudostate.EntryPoint)
	 */
	@Override
	public void setAddEntryPoint(EntryPoint entryPoint){
		this.vectorEntryPoint.add(entryPoint);
	}
	/**
	 * Restituisce il vettore EntryPoint
	 * @return vectorEntryPoint Vettore lista di entryPoint
	 */
	@Override
	public Vector<EntryPoint> getVectorEntryPoint(){
		return this.vectorEntryPoint;
	}
	
	
	/** Aggiunge un elemento al vettore vectorExitPoint
	 * @param exitPoint Oggetto di tipo ExitPoint
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddExitPoint(it.moise.plc.uml.pseudostate.ExitPoint)
	 */
	@Override
	public void setAddExitPoint(ExitPoint exitPoint){
		this.vectorExitPoint.add(exitPoint);
	}
	/**
	 * Restituisce il vettore ExitPoint
	 * @return vectorExitPoint Vettore lista di oggetti ExitPoint
	 */
	@Override
	public Vector<ExitPoint> getVectorExitPoint(){
		return this.vectorExitPoint;
	}
	
	
	/** Aggiunge un elemento al vettore vectorFinal
	 * @param finals Oggetto di tipo Final
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddFinal(it.moise.plc.uml.pseudostate.Final)
	 */
	@Override
	public void setAddFinal(Final finals){
		this.vectorFinal.add(finals);
	}
	/**
	 * Restituisce il vettore Final
	 * @return vectorFinal Vettore lista di oggetti Final
	 */
	@Override
	public Vector<Final> getVectorFinal(){
		return this.vectorFinal;
	}
	
	
	/** Aggiunge un elemento al vettore vectorJoin
	 * @param join Oggetto di tipo Join
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddJoin(it.moise.plc.uml.pseudostate.Join)
	 */
	@Override
	public void setAddJoin(Join join){
		this.vectorJoin.add(join);
	}
	/**
	 * Restituisce il vettore Join
	 * @return vectorJoin Vettore lista di oggetti di tipo join
	 */
	@Override
	public Vector<Join> getVectorJoin(){
		return this.vectorJoin;
	}
	
	
	/** Aggiunge un elemento al vettore vectorFork
	 * @param fork Oggetto di tipo Fork
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddFork(it.moise.plc.uml.pseudostate.Fork)
	 */
	@Override
	public void setAddFork(Fork fork){
		this.vectorFork.add(fork);
	}
	/**
	 * Restituisce il vettore Fork
	 * @return vectorFork Vettore lista di oggetti fork
	 */
	@Override
	public Vector<Fork> getVectorFork(){
		return this.vectorFork;
	}
	
	
	/** Aggiunge un elemento al vettore vectorShallowHistory
	 * @param shallowHistory Oggetto di tipo ShallowHistory
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddShallowHistory(it.moise.plc.uml.pseudostate.ShallowHistory)
	 */
	@Override
	public void setAddShallowHistory(ShallowHistory shallowHistory){
		this.vectorShallowHistory.add(shallowHistory);
	}
	/**
	 * Restituisce il vettore ShallowHistory
	 * @return vectorShallowHistory Vettore lista di oggetti shallowHistory
	 */
	@Override
	public Vector<ShallowHistory> getVectorShallowHistory(){
		return this.vectorShallowHistory;
	}
	
	
	/** Aggiunge un elemento al vettore vectorTerminate
	 * @param terminate Oggetto di tipo Terminate
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddTerminate(it.moise.plc.uml.pseudostate.Terminate)
	 */
	@Override
	public void setAddTerminate(Terminate terminate){
		this.vectorTerminate.add(terminate);
	}
	/**
	 * Restituisce il vettore Terminate
	 * @return vectorTerminate Vettore lista di oggetti di tipo terminate
	 */
	@Override
	public Vector<Terminate> getVectorTerminate(){
		return this.vectorTerminate;
	}
	
	/** Aggiunge un elemento al vettore vectorJunction
	 * @param junction Oggetto di tipo Junction
	 * @see it.moise.plc.uml.interfaces.InterfaceStateMachine#setAddJunction(it.moise.plc.uml.pseudostate.Junction)
	 */
	@Override
	public void setAddJunction(Junction junction){
		this.vectorJunction.add(junction);
	}
	/**
	 * Restituisce il vettore Junction
	 * @return vectorJunction Vettore lista di oggetti di tipo junction
	 */
	@Override
	public Vector<Junction> getVectorJunction(){
		return this.vectorJunction;
	}
	/**
	 * Aggiungo un elemento al vettore generale delle variabili
	 * @param value valore del parametro variabile
	 */
	public void setAddVectorVariable(Variable value){
		this.vectorVariable.add(value);
	}
	/**
	 * Restituisce il vettore vectorVariable
	 * @return vectorVariable Vettore lista delle variabili
	 */
	public Vector<Variable> getVectorVariable(){
		return this.vectorVariable;
	}
	/**
	 * Aggiunge un elemento a vectorPrimitiveType
	 * @param extension Oggetto di tipo PrimitiveType
	 */
	
	public void setAddVectorPrimitiveType(PrimitiveType extension){
		this.vectorPrimitiveType.add(extension);
	}
	/**
	 * Restituisce il vectorPrimitiveType
	 */
	
	public ArrayList<PrimitiveType> getVectorPrimitiveType(){
		return this.vectorPrimitiveType;
	}
}
