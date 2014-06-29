package it.moise.plc.uml;

import it.moise.plc.uml.pseudostate.Choice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

/**
 * @author De Carvalho Moïse
 *
 **/
public class StateLevel implements Comparable<StateLevel>{
	private int numberState = 0;
	private int levelStato = 0;
	private String idStato = "";
	private String nomeStato = "";
	private String newName ="";
	private boolean initialPoint;
	private boolean raggiungibile;
	private boolean statoDiAvvio = false;	// se TRUE è il primo stato che si deve avviare
	// localId viene utilizzato in fase di costruzione dello SFC pre rintracciare gli stati con l etransizioni
	private int localId = 0;
	private String idStateLevelUp = "-1";
	private Vector<String> vectorSubState = new Vector<String>();	
	private ArrayList<String> listTransitionIn = new ArrayList<String>();
	private ArrayList<String> listTransitionOut = new ArrayList<String>();
	private ArrayList<Variable> listVariable = new ArrayList<Variable>();
	private ArrayList<String> listaAdiacenti = new ArrayList<String>();		// contiene l'elenco degli stati adiacenti
	private ArrayList<Choice> listChoice = new ArrayList<Choice>();
	private boolean visitato = false;										// indica se lo stato è stato visitato durante la visita DFS
	private boolean aggiuntoAlGrafo = false;								// se TRUE lo stato è stato aggiunto al grafo
	
	private boolean statoSfcDesigned = false;
	
	private String action = "";
	
	/**
	 * Costruttore con paramentro che imposta alcuni valori prendendoli dallo stato
	 * @param state
	 */
	public StateLevel(State state, int numberState){
		this.numberState = numberState;  	// ok
		this.levelStato = state.getLevel();	// ok
		this.idStato = state.getId();		// ok
		this.nomeStato = state.getName();	// ok
		this.raggiungibile = state.getRaggiungibile(); // ok
		this.vectorSubState = state.getVectorSubState(); // ok
		this.setRaggiungibile(false);
		this.setInitialPoint(false);
		this.setIdStateLevelUp(state.getIdStateLevelUp());
		this.listVariable = state.getDoActivity().getListVariable();
		// verifico se ci sono delle variabili nella lista, in caso affermativo, vuol dire che lo stato modifica il valore delle variabili
		// pertanto creo un action che sarà inserito successivamente nel diagramma SFC
		if (state.getDoActivity().getListVariable().size() > 0){
			// trovato delle variabili
			int nrVar = state.getDoActivity().getListVariable().size();
			action = "";
			for (int i=0; i < nrVar; i++){
				action = action + state.getDoActivity().getListVariable().get(i).getName() + " := " 
						+ state.getDoActivity().getListVariable().get(i).getValue() + ";\n";
			}
			setAction(action);
		}
		
	}
	public StateLevel() {
	}

	
	static class LevelComparator implements Comparator<StateLevel> {
		@Override
		public int compare(StateLevel s1, StateLevel s2) {
			int result;
			result = s1.getLevelStato() - s2.getLevelStato();
			return result;
			}
	}
	
	static class IdStateComparator implements Comparator<StateLevel> {
		@Override
		public int compare(StateLevel s1, StateLevel s2) {
			int result;
			result = s1.getIdStato().compareTo(s2.getIdStato());
			return result;
			}
	}
	
	
	public void setNumberState(int numberState){
		this.numberState = numberState;
	}
	public int getNumberState(){
		return this.numberState;
	}
	
	public void setLevelState(int levelStato){
		this.levelStato = levelStato;
	}
	public int getLevelStato(){
		return this.levelStato;
	}
	
	public void setIdStato(String idstato){
		this.idStato = idStato;
	}
	public String getIdStato(){
		return this.idStato;
	}
	
	public void setNomeStato(String nomeStato){
		this.nomeStato = nomeStato;
	}
	public String getNomeStato(){
		return this.nomeStato;
	}
	
	
	

	public void setInitialPoint(boolean initial) {
		this.initialPoint = initial;

	}
	public boolean getInitialPoint() {
		// 
		return this.initialPoint;
	}
	
	
	public void setRaggiungibile(boolean raggiungibile) {
		this.raggiungibile = raggiungibile;

	}
	public boolean getRaggiungibile() {
		// 
		return this.raggiungibile;
	}
	
	
	public void setAddListTransitionIn(String idTransition){
		this.listTransitionIn.add(idTransition);
	}
	public ArrayList<String> getListTransitionIn(){
		return this.listTransitionIn;
	}
	
	public void setAddListTransitionOut(String idTransition){
		this.listTransitionOut.add(idTransition);
	}
	public ArrayList<String> getListTransitionOut(){
		return this.listTransitionOut;
	}
	@Override
	public int compareTo(StateLevel o) {
		// 
		return 0;
	}
	
	
	public void setAddVectorSubState(String idState){
		this.vectorSubState.add(idState);
		}
	public Vector<String> getVectorSubState(){
		return this.vectorSubState;
	}
	
	public void setIdStateLevelUp(String idStateLevelUp){
		this.idStateLevelUp = idStateLevelUp;
	}
	
	
	public String getIdStateLevelUp(){
		return this.idStateLevelUp;
	}
	
	/**
	 * Imposta il valore di localId per lo stato in questione,
	 * utile quando si costruisce lo SFC
	 * @param value
	 */
	public void setLocalID(int value){
		this.localId = value;
	}
	/**
	 * Restituisce il valore di localId, utile quando si costruisce lo SFC
	 * @return localId
	 */
	public int getLocalId(){
		return this.localId;
	}
	/**
	 * Imposta il nuovo nome dello stato dopo la creazione di arrayStateLevel
	 * @param value	
	 */
	public void setNewName(String value){
		this.newName = value;
	}
	/**
	 * Restituisce il nuovo nome dello stato dopo la creazione di arrayStateLevel
	 * @return newName
	 */
	public String getNewName(){
		return this.newName;
	}
	
	/**
	 * Questo metodo imposta il valore a TRUE della variabile statoSfcDesigned, il cui valore indica che lo stato è
	 * stato disegnato nel grafo SFC del file xml di codesys
	 * @param value : boolean
	 */
	public void setStatoSfcDesigned(boolean value){
		this.statoSfcDesigned = value;
	}
	/**
	 * Restituisce il valore della variabile statoSfcDesigned, se TRUE, indica che è già stato aggiunto al grafo SFC del file xml di codesys
	 * @return statoSfcDesigned
	 */
	public boolean getStatoSfcDesigned(){
		return this.statoSfcDesigned;
		}
	/**
	 * Imposta il valore della variabile statoDiAvvio, se TRUE è il prio stato che parte del diagramma
	 * @param value
	 */
	public void setStatoDiAvvio(boolean value){
		this.statoDiAvvio = value;
	}
	/**
	 * Restituisce il valore della variabile statoDiAvvio, se TRUE è il prio stato che parte del diagramma
	 * @return statoDiAvvio
	 */
	public boolean getStatoDiAvvio(){
		return this.statoDiAvvio;
	}
	/**
	 * Aggiunge un elemento (il nome dello stato) alla lista degli stati adiacenti
	 * @param value
	 */
	public void setAddListaAdiacenti(String value){
		this.listaAdiacenti.add(value);
		
	}
	/**
	 * Restituisce la lista degli stati adiacenti
	 * @return listaAdiacenti
	 */
	public ArrayList<String> getListaAdiacenti(){
		return this.listaAdiacenti;
	}
	/**
	 * Imposta il valore a TRUE se lo stato è stato visitato durante il DFS
	 * @param value
	 */
	public void setVisitato(boolean value){
		this.visitato = value;
	}
	/**
	 * Restiutisce lo stato di visitato
	 * @return visitato
	 */
	public boolean getVisitato(){
		return this.visitato;
	}
	/**
	 * Imposta il valore a TRUE se lo stato è stato aggiunto al grafo
	 * @param value
	 */
	public void setAggiuntoAlGrafo(boolean value){
		this.aggiuntoAlGrafo = value;
	}
	/**
	 * Restiutisce lo stato di aggiuntoAlGrafo
	 * @return aggiuntoAlGrafo
	 */
	public boolean getAggiuntoAlGrafo(){
		return this.aggiuntoAlGrafo;
	}
	/**
	 * Imposta il valore di action per cambiare le variabili
	 * @param value
	 */
	public void setAction(String value){
		this.action = value;
	}
	/**
	 * Restituisce il valore di action
	 * @return
	 */
	public String getAction(){
		return this.action;
				
	}
	/**
	 * Aggiunge un nodo variable alla lista listVariable
	 * @param Variable variable
	 **/
	public void setAddListVariable(Variable variable) {
		this.listVariable.add(variable);
	}

	/**
	 * Restituisce la lista listVariable
	 **/
	public ArrayList<Variable> getListVariable() {
		
		return this.listVariable;
	}
	/**
	 * Aggiunge un elemento Choice alla lista
	 * @param choice
	 */
	public void setAddListChoice(Choice choice){
		this.listChoice.add(choice);
	}
	/**
	 * restituisce la listChoice
	 * @return
	 */
	public ArrayList<Choice> getListChoice(){
		return this.listChoice;
	}
	/**
	 * Copia la lista che gli viene passata
	 * @param listaAdiacenti
	 */
	public void setListListaAdiacenti(ArrayList<String> listaAdiacenti){
		this.listaAdiacenti = listaAdiacenti;
	}
}
