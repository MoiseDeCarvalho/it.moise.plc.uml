package it.moise.plc.uml;

import java.util.Comparator;
/**
 * Crea un oggetto transitions finale
 * @author De Carvalho Moise
 *
 */
public class Transitions implements Comparable<Transitions>{
	private int numberTransition = 0;
	private String idTransition = "";
	private String name = "";
	private Comment comment;
	private boolean commentValutato = false; 	//se TRUE vuol dire che il commento è già stato valutato ed inserito nell'Action dello StateLevel
	
	private int numberStateSource = 0;
	private String idStateSource = "";
	private String nameStateSource = "";
	private String newNameStateSource = "";
	
	private int numberStateTarget = 0;
	private String idStateTarget = "";
	private String nameStateTarget = "";
	private String newNameStateTarget = "";
	
	private int levelSource;
	private int levelTarget;
	
	private boolean valueTransition = false;
	
	private boolean leafTransitions = false; // se true significa che o in source o in target non c'è uno state a nessun livello
	
	// CHOICE //
	private boolean choice = false; 	// se true vuol dire che parte o arriva in choice
	private boolean choiceEnd = false;	 // se true vuol dire che la transizione  arriva al choice
	private boolean choiceStart = false; // se true vuol dire che la transizione parte dal choice
	private String idChoiceSource = "-1" ;	// memorizzo ilChoice da dove parte la transition
	
	
	// FORK //
	private boolean fork = false; 	// se true vuol dire che parte o arriva in fork
	private boolean forkEnd = false;	 // se true vuol dire che la transizione  arriva al fork
	private boolean forkStart = false; // se true vuol dire che la transizione parte dal fork
	
	// JOIN //
	private boolean join = false; 	// se true vuol dire che parte o arriva in join
	private boolean joinEnd = false;	 // se true vuol dire che la transizione  arriva al join
	private boolean joinStart = false; // se true vuol dire che la transizione parte dal join
	
	private boolean TrxUtilizzatoInSFC = false;
	/**
	 * Costruttore con parametro, che inizializza l'oggetto in base ai valori contenuti nell'oggetto transition
	 * @param transition Oggetto di tipo transition
	 * @param numberTransition numero della transizione
	 */
	public Transitions(Transition transition, int numberTransition){
		this.idTransition = transition.getId();
		this.idStateSource = transition.getSource();
		this.idStateTarget = transition.getTarget();
		this.numberTransition = numberTransition;
		this.name = transition.getName();
		comment = new Comment();
	}
	/**
	 * Costruttore senza parametri
	 */
	public Transitions() {
	}
	/**
	 * Implemento il metodo per eseguire l'ordinamento
	 *
	 */
	static class NameComparator implements Comparator<Transitions> {
		@Override
		public int compare(Transitions t1, Transitions t2) {
			int result;
			result = t1.getName().compareTo(t2.getName());
			return result;
			}
	}

	/**
	 * Implemento il metodo per eseguire l'ordinamento
	 *
	 */
	static class SourceComparator implements Comparator<Transitions> {
		@Override
		public int compare(Transitions t1, Transitions t2) {
			int result;
			result = t1.getIdStateSource().compareTo(t2.getIdStateSource());
			return result;
			}
	}
	/**
	 * Implemento il metodo per eseguire l'ordinamento
	 *
	 */
	static class TargetComparator implements Comparator<Transitions> {
		@Override
		public int compare(Transitions t1, Transitions t2) {
			int result;
			result = t1.getIdStateTarget().compareTo(t2.getIdStateTarget());
			return result;
			}
	}
	
	/**
	 * Implemento il metodo per eseguire l'ordinamento
	 *
	 */
	static class LevelSourceComparator implements Comparator<Transitions> {
		@Override
		public int compare(Transitions t1, Transitions t2) {
			int result;
			result = t1.getLevelSource() - (t2.getLevelSource());
			return result;
			}
	}
	
	/**
	 * Imposta il numero della transizione
	 * @param numberTransition Numero della transizone
	 */
	public void setNumberTransition(int numberTransition){
		this.numberTransition = numberTransition;
	}
	/**
	 * Restituisce il numero della transizione
	 * @return numberTransition Numero della transizione
	 */
	public int getNumberTransition(){
		return this.numberTransition;
	}
	/**
	 * Imposta l'id della transizione
	 * @param idTransition Id della transizione
	 */
	public void setIdTransition(String idTransition){
		this.idTransition = idTransition;
	}
	/**
	 * Restituisce l'id
	 * @return
	 */
	public String getIdTransition(){
		return this.idTransition;
	}
	/**
	 * Imposta il numero dello stato source
	 * @param numberStateSource Numero dello stato source
	 */
	public void setNumberStateSource(int numberStateSource){
		this.numberStateSource = numberStateSource;
	}
	/**
	 * Restituisce il numero dello stato source
	 * @return numberStateSource
	 */
	public int getNumberStateSource(){
		return this.numberStateSource;
	}
	/**
	 * Imposta l'id dello stato source
	 * @param idStateSource Id
	 */
	public void setIdStateSource(String idStateSource){
		this.idStateSource = idStateSource;
	}
	/**
	 * Restituisce l'ide dello stato source
	 * @return idStateSource ID
	 */
	public String getIdStateSource(){
		return this.idStateSource;
	}
	
	public void setNameStateSource(String nameStateSource){
		this.nameStateSource = nameStateSource;
	}
	public String getNameStateSource(){
		return this.nameStateSource;
	}
	
	
	public void setNumberStateTarget(int numberStateTarget){
		this.numberStateTarget = numberStateTarget;
	}
	public int getNumberStateTarget(){
		return this.numberStateTarget;
	}
	
	public void setIdStateTarget(String idStateTarget){
		this.idStateTarget = idStateTarget;
	}
	public String getIdStateTarget(){
		return this.idStateTarget;
	}
	
	public void setNameStateTarget(String nameStateTarget){
		this.nameStateTarget = nameStateTarget;
	}
	public String getNameStateTarget(){
		return this.nameStateTarget;
	}
	
	
	public void setLevelSource(int levelSource){
		this.levelSource = levelSource;
	}
	public int getLevelSource(){
		return this.levelSource;
	}
	
	public void setLevelTarget(int levelTarget){
		this.levelTarget = levelTarget;
	}
	public int getLevelTarget(){
		return this.levelTarget;
	}
	
	
	public void setValueTransition(boolean valueTransition){
		this.valueTransition = valueTransition;
	}
	public boolean getValueTransition(){
		return this.valueTransition;
	}
	@Override
	public int compareTo(Transitions o) {
		return 0;
	}
	
	public void setLeafTransitions(boolean leafTransitions){
		this.leafTransitions = leafTransitions;
	}
	public boolean getLeafTransitions(){
		return this.leafTransitions;
	}
	public void setChoice(boolean value){
		this.choice = value;
	}
	public boolean getChoice(){
		return this.choice;
	}
	public void setChoiceEnd(boolean value){
		this.choiceEnd = value;
	}
	public boolean getChoiceEnd(){
		return this.choiceEnd;
	}
	public void setChoiceStart(boolean value){
		this.choiceStart = value;
	}
	public boolean getChoiceStart(){
		return this.choiceStart;
	}
	
	public void setNewNameStateSource(String value){
		this.newNameStateSource = value;
	}
	public String getNewNameStateSource(){
		return this.newNameStateSource;
	}
	public void setNewNameStateTarget(String value){
		this.newNameStateTarget = value;
	}
	public String getNewNameStateTarget(){
		return this.newNameStateTarget;
	}
	public void setFork(boolean value){
		this.fork = value;
	}
	public boolean getFork(){
		return this.fork;
	}
	public void setForkEnd(boolean value){
		this.forkEnd = value;
	}
	public boolean getForkEnd(){
		return this.forkEnd;
	}
	public void setForkStart(boolean value){
		this.forkStart = value;
	}
	public boolean getForkStart(){
		return this.forkStart;
	}
	public void setJoin(boolean value){
		this.join = value;
	}
	public boolean getJoin(){
		return this.join;
	}
	public void setJoinEnd(boolean value){
		this.joinEnd = value;
	}
	public boolean getJoinEnd(){
		return this.joinEnd;
	}
	public void setJoinStart(boolean value){
		this.joinStart = value;
	}
	public boolean getJoinStart(){
		return this.joinStart;
	}
	/**
	 * Imposta il valore idChoice da dove parte la transition, in modo tale che se il CHOICE ha un commento, 
	 * esso viene considerato come una condizione e viene inserita nell'action dello state un if che modifica il valore del
	 * valore del table pattern per poter transitare e cambiare stato
	 * @param value
	 */
	public void setIdChoiceSource(String value){
		this.idChoiceSource = value;
	}
	public String getIdChoiceSource(){
		return this.idChoiceSource;
	}
	public void setComment(Comment comment){
		this.comment = comment;
	}
	public Comment getComment(){
		return this.comment;
	}
	public void setCommentValutato(boolean value){
		this.commentValutato = value;
	}
	public boolean getCommentValutato(){
		return this.commentValutato;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	/**
	 * Imposta il valore booleano se TRUE la transizione è utilizzata nell'sfc
	 * @param utilizzato Valore booleano
	 */
	public void setTrxUtilizzatoInSFC(boolean utilizzato){
		this.TrxUtilizzatoInSFC = utilizzato;
	}
	/**
	 * Restituisce il valore booleano della variabile, se TRue è utilizzzata nello SFC
	 * @return TrxUtilizzatoInSFC Valore booleano
	 */
	public boolean getTrxUtilizzatoInSfc(){
		return this.TrxUtilizzatoInSFC;
	}
	/**
	 * Restituisce un valore boolenaom se TRUE la transitions contiene un commento
	 * @return is Comment Se TRUE la transitions contiene un commento
	 */
	public boolean isComment(){
		boolean isComment = false;
		if ((this.getComment() != null) && (this.getComment().getBody() != null)){
			isComment = true;
		}
		return isComment;
	}
}
