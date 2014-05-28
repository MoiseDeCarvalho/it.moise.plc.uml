/**
 * Crea un oggetto Region
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceRegion;
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
import java.util.Comparator;

import org.w3c.dom.NamedNodeMap;

/**
 * Classe che costruisce un oggetto di tipo region
 * @author De Carvalho Moise
 *
 **/
public class Region implements InterfaceRegion, Comparable<Region>{
	private ArrayList<Transition> listTransition = new ArrayList<Transition>();
	private ArrayList<String> listVertex = new ArrayList<String>();
	private ArrayList<Join> listJoin = new ArrayList<Join>();
	private ArrayList<Fork> listFork = new ArrayList<Fork>();
	private ArrayList<Junction> listJunction = new ArrayList<Junction>();
	private ArrayList<Choice> listChoice = new ArrayList<Choice>();
	private ArrayList<String> listIdState = new ArrayList<String>();
	private ArrayList<Comment> listComment = new ArrayList<Comment>();
	
	private Terminate terminate = new Terminate();
	private Initial initialState = null;
	private Final finalState = new Final();
	
	private String id = null;
	private String name = null;
	private String type = null;
	private String idStateAppartenenza = null;
	
	private DeepHistory deepHistory = null;
	private ShallowHistory shallowHistory = null;
	private String parent = null;
	/**
	 * Metodo costruttore
	 * @param node Oggetto di tipo NamedNodemap da analizzare
	 */
	public Region(NamedNodeMap node){
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
	 */
	public Region() {
	}
	
	/**
	 * Metodo Implementato per effettuare l'ordinamento delle Collections
	 * @author De Carvalho Moise
	 *
	 */
	static class IdRegionComparator implements Comparator<Region> {
		@Override
		public int compare(Region r1, Region r2) {
			int result;
			result = r1.getId().compareTo(r2.getId());
			return result;
			}
	}
	/**
	 * Imposta il valore dell'id
	 * @param id Id dell'oggetto
	 *  @see it.moise.plc.uml.interfaces.InterfaceRegion#setId(java.lang.String)
	 */
	@Override
	public void setId(String id){
		this.id = id;
	}
	/**
	 * Restituisce l'id
	 *  @see it.moise.plc.uml.interfaces.InterfaceRegion#getId()
	 */
	@Override
	public String getId(){
		return this.id;
	}
	/**
	 * Imposta il nome dell'oggetto
	 * @param name Nome dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setName(java.lang.String)
	 */
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Restituisce il nome dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getName()
	 */
	@Override
	public String getName(){
		return this.name;
	}
	/**
	 * Imposta il tipo dell'oggetto
	 * @param type Tipo dell'oggeto
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setType(java.lang.String)
	 */
	@Override
	public void setType(String type){
		this.type = type;
	}
	/**
	 * Restituisce il tipo dell'oggetto
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getType()
	 */
	@Override
	public String getType(){
		return this.type;
	}
	/**
	 * Imposta l'id dello stato di appartenenza
	 * @param idStateAppartenenza Id dello stato di appartenenza
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setIdStateAppartenenza(java.lang.String)
	 */
	@Override
	public void setIdStateAppartenenza(String idStateAppartenenza){
		this.idStateAppartenenza = idStateAppartenenza;
	}
	/**
	 * Restituisce l'ide dello stato di appartenenza
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getIdStateAppartenenza()
	 */
	@Override
	public String getIdStateAppartenenza(){
		return this.idStateAppartenenza;
	}
	
	/** 
	 * Metodo che permette di aggiungere ud IdState che una regione pu� avere
	 * @param idState Id dello stato
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddListIdState(java.lang.String)
	 ***/
	@Override
	public void setAddListIdState(String idState) {
		this.listIdState.add(idState);
	}

	/** 
	 * Restituisce un oggetto idState dalla lista delle idState
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getIdState(int)
	 ***/
	@Override
	public String getIdState(int index) {
		// 
		return this.listIdState.get(index);
	}

	/** 
	 * Restituisce la lista delle idState
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getListIdState()
	 ***/
	@Override
	public ArrayList<String> getListIdState() {
		// 
		return this.listIdState;
	}
	
	
	/** 
	 * Metodo che permette di aggiungere una transizione alla lista delle transizioni che una regione pu� avere
	 * @param transition Oggetto di tipo transition
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddTransition(it.moise.plc.uml.Transition)
	 ***/
	@Override
	public void setAddTransition(Transition transition) {
		this.listTransition.add(transition);
	}

	/** 
	 * Restituisce un oggetto transizione dalla lista delle transizioni
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getTransition(int)
	 ***/
	@Override
	public Transition getTransition(int index) {
		// 
		return this.listTransition.get(index);
	}

	/** 
	 * Restituisce la lista delle transizioni
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getListTransition()
	 ***/
	@Override
	public ArrayList<Transition> getListTransition() {
		// 
		return this.listTransition;
	}
	

	/**
	 * Permette di aggiungere un nodo alla lista Vertex
	 * @param vertex Indica il vertex
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddVertex(java.lang.String)
	 **/
	@Override
	public void setAddVertex(String vertex) {
		this.listVertex.add(vertex);

	}

	/** 
	 * Restituisce un vertex
	 * @param index Indica l'indice
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getVertex(int)
	 ***/
	@Override
	public String getVertex(int index) {
		// 
		return this.listVertex.get(index);
	}

	/** 
	 * Restituisce listVertex
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getListVertex()
	 ***/
	@Override
	public ArrayList<String> getListVertex() {
		// 
		return this.listVertex;
	}
	

	
	/**
	 * Set deepHistory
	 * @param deepHistory Oggetto di tipo DeepHistory
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setDeepHistory(it.moise.plc.uml.pseudostate.DeepHistory)
	 ***/
	@Override
	public void setDeepHistory(DeepHistory deepHistory) {
		this.deepHistory = deepHistory;

	}

	/**
	 * Restituisce il valore del deepHistory
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getDeepHistory()
	 ***/
	@Override
	public DeepHistory getDeepHistory() {
		// 
		return this.deepHistory;
	}

	/**
	 * Set shallowHistory
	 * @param shallowHistory Oggetto di tipo ShallowHistory
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setShallowHistory(it.moise.plc.uml.pseudostate.ShallowHistory)
	 ***/
	@Override
	public void setShallowHistory(ShallowHistory shallowHistory) {
		this.shallowHistory = shallowHistory;

	}

	/**
	 * Restituisce shallowHistory
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getShallowHistory()
	 ***/
	@Override
	public ShallowHistory getShallowHistory() {
		// 
		return this.shallowHistory;
	}
	
	@Override
	/**
	 * Permette di settare lo stato iniziale
	 * @param initialState Oggetto di tipo Initial
	 * @see Interfaces.InitialPseudostate#setInitialPseudostate(it.moise.plc.uml.pseudostate.Initial)
	 **/
	public void setInitialPseudoState(Initial initialState){
		this.initialState = initialState;
	}
	
	@Override
	/**
	 * Restituisce lo InitialPseudoState
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getInitialPseudoState()
	 **/
	public Initial getInitialPseudoState(){
		return this.initialState;
	}
	
	
	/**
	 * Permette di aggiungere un nodo di tipo Join alla lista listJoin
	 * @param join Oggetto di tipo Join
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddListJoin(it.moise.plc.uml.pseudostate.Join)
	 */
	@Override
	public void setAddListJoin(Join join){
		this.listJoin.add(join);
	}
	
	/**
	 * Restituisce  un nodo di tipo Join della lista listJoin
	 * @param index Contiene l'indice
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getJoin(int)
	 */
	@Override
	public Join getJoin(int index){
		return this.listJoin.get(index);
	}
	
	/**
	 * Restituisce  la lista listJoin
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getListJoin()
	 */
	@Override
	public ArrayList<Join> getListJoin(){
		return this.listJoin;
	}
	
	
	
	
	/**
	 * Permette di aggiungere un nodo di tipo Fork alla lista listJoin
	 * @param fork Oggetto di tipo fork
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddListFork(it.moise.plc.uml.pseudostate.Fork)
	 */
	@Override
	public void setAddListFork(Fork fork){
		this.listFork.add(fork);
	}
	
	/**
	 * Restituisce  un nodo di tipo Fork della lista listFork
	 * @param index Indice
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getFork(int)
	 */
	@Override
	public Fork getFork(int index){
		return this.listFork.get(index);
	}
	
	/**
	 * Restituisce  la lista listFork
	 *  @see it.moise.plc.uml.interfaces.InterfaceRegion#getListFork()
	 */
	@Override
	public ArrayList<Fork> getListFork(){
		return this.listFork;
	}
	
	
	
	/**
	 * Permette di aggiungere un nodo di tipo Junction alla lista listJunction
	 * @param junction Oggetto di tipo Junction
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddListJunction(it.moise.plc.uml.pseudostate.Junction)
	 */
	@Override
	public void setAddListJunction(Junction junction){
		this.listJunction.add(junction);
	}
	
	/**
	 * Restituisce  un nodo di tipo Junction della lista listJunction
	 * @param index Indice
	 *@see it.moise.plc.uml.interfaces.InterfaceRegion#getJunction(int)
	 */
	@Override
	public Junction getJunction(int index){
		return this.listJunction.get(index);
	}
	
	/**
	 * Restituisce  la lista listJunction
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getListJunction()
	 */
	@Override
	public ArrayList<Junction> getListJunction(){
		return this.listJunction;
	}
	
	
	
	/**
	 * Permette di aggiungere un nodo di tipo Choice alla lista listChoice
	 * @param choice Oggetto di tipo choice
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddListChoice(it.moise.plc.uml.pseudostate.Choice)
	 */
	@Override
	public void setAddListChoice(Choice choice){
		this.listChoice.add(choice);
	}
	
	/**
	 * Restituisce  un nodo di tipo Choice della lista listChoice
	 * @param index Indice
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getChoice(int)
	 */
	@Override
	public Choice getChoice(int index){
		return this.listChoice.get(index);
	}
	
	/**
	 * Restituisce  la lista listChoice
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getListChoice()
	 */
	@Override
	public ArrayList<Choice> getListChoice(){
		return this.listChoice;
	}
	
	
	/**
	 * Permette di aggiungere un nodo di tipo Comment alla lista listaComment
	 * @param comment Oggetto di tipo comment
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setAddListComment(it.moise.plc.uml.Comment)
	 */
	@Override
	public void setAddListComment(Comment comment){
		this.listComment.add(comment);
	}
	
	/**
	 * Restituisce la listComment
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#getListComment()
	 */
	@Override
	public ArrayList<Comment> getListComment(){
		return this.listComment;
	}
	
	@Override
	/**
	 * Set lo Terminate
	 * @param  terminate Oggetto di tipo terminate
	 * it.moise.plc.uml.interfaces.pseudostate.InitialPseudostate#setAddListComment(it.moise.plc.uml.pseudostate.Terminate)
	 **/
	public void setTerminate(Terminate terminate){
		this.terminate = terminate;
	}
	
	@Override
	/**
	 * Restituisce lo Terminate
	 * @see it.moise.plc.uml.interfaces.pseudostate.InitialPseudostate#getTerminate()
	 **/
	public Terminate getTerminate(){
		return this.terminate;
	}
	
	
	@Override
	/**
	 * Set lo FinalState
	 * @param finalState Oggetto di tipo finalestate
	 * @see it.moise.plc.uml.interfaces.pseudostate.InitialPseudostate#setFinalState(it.moise.plc.uml.pseudostate.FinalState)
	 **/
	public void setFinalState(Final finalState){
		this.finalState = finalState;
	}
	
	@Override
	/**
	 * Restituisce lo FinalState
	 * @see it.moise.plc.uml.interfaces.pseudostate.InitialPseudostate#getFinalState()
	 **/
	public Final getFinalState(){
		return this.finalState;
	}
	
	@Override
	/**
	 * Set il valore di parent
	 * @param parent Indica l'id del parent
	 * @see it.moise.plc.uml.interfaces.InterfaceRegion#setParent(java.lang.String)
	 **/
	public void setParent(String parent){
		this.parent = parent;
	}
	
	@Override
	/**
	 * Restituisce il valore di parent
	 *  @see it.moise.plc.uml.interfaces.InterfaceRegione#getParent()
	 **/
	public String getParent(){
		return this.parent;
	}
	/**
	 * Metodo implementato per l'ordinamento
	 * @param o oggetto di tipo region
	 * @return 0
	 */
	@Override
	public int compareTo(Region o) {
		// 
		return 0;
	}
	
	
}
