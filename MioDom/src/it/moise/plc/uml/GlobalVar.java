package it.moise.plc.uml;

import java.util.ArrayList;
/**
 * Classe che costruisce un oggetto da passare tra le varie classi per avere delle varibili globali
 * @author De Carvalho Moise
 *
 */
public class GlobalVar {
	private ArrayList<String> inputVarList = new ArrayList<String>();
	private int numberStateLevels = 0;
	private String stateManagerActionValue = "";
	private String initActionValue = "";
	/**
	 * Metodo costruttore senza parametri
	 */
	public GlobalVar() {
	}
	/**
	 * Aggiunge una variabile individuata
	 * @param value contiene l'id della variabile
	 */
	public void setInputVarList(String value){
		this.inputVarList.add(value);
	}
	/**
	 * Restituisce la lista delle variabili
	 * @return inputVarList Lista delle variabili
	 */
	public ArrayList<String> getInputVarList(){
		return this.inputVarList;
	}
	/**
	 * Imposta il numero degli stati
	 * @param value Contiene il numero degli stati
	 */
	public void setNumberStateLevels(int value){
		this.numberStateLevels = value;
	}
	/**
	 * Restituisce il numero degli stati
	 * @return numberStateLevels Contiene il numero degli stati
	 */
	public int getNumberStateLevels(){
		return this.numberStateLevels;
	}
	/**
	 * Imposta il valore di STATE MANAGER per codesys
	 * @param value Contiene il valroe di STATE MANAGER
	 */
	public void setStateManagerActionValue(String value){
		this.stateManagerActionValue = value;
	}
	/**
	 * Restituisce il valore di STATE MANAGER per Codesys
	 * @return stateManagerActionValue Contiene il valore do STATE MANAGER
	 */
	public String getStateManagerActionValue(){
		return this.stateManagerActionValue;
	}
	/**
	 * Imposta il valore di INIT per codesys
	 * @param value Contiene il valore di INIT
	 */
	public void setInitActionValue(String value){
		this.initActionValue = value;
	}
	/**
	 * Restituisce il valore di INIT per codesys
	 * @return initActionValue Contiene il valore di INIT per codesys
	 */ 
	public String getInitActionValue(){
		return this.initActionValue;
	}
}
