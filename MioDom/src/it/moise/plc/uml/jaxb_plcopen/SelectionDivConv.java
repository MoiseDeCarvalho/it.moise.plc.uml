package it.moise.plc.uml.jaxb_plcopen;

import java.util.List;
import java.util.Vector;

public class SelectionDivConv {
	private String name = "";
	private int localId;
	private Vector<Integer> listRefLocalId = new Vector<Integer>();;
	public SelectionDivConv(String name, int localId) {
		this.setLocalId(localId);
		this.setName(name);
	}
	
	/**
	 * Imposta il valore del nome di Selection
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Restituisce il nome del Selection
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Aggiunge il valore di un refLocalId alla lista che poi deve essere chiusa con il Selection Convergence
	 * @param value
	 */
	public void setAddListRefLocalId(int value){
		this.listRefLocalId.add(value);
	}
	/**
	 * Restituisce la lista dei refLocalId che saranno utilizzati da SelectionConvergence
	 * @return listRefLocalId : lista dei refLocalId che saranno utilizzati da SelectionConvergence
	 */
	public Vector<Integer> getListRefLocalId(){
		return this.listRefLocalId;
	}
	/**
	 * Imposta il localId di Simultaneous
	 * @param value
	 */
	public void setLocalId(int value){
		this.localId = value;
	}
	/**
	 * Restituisce il localId
	 * @return
	 */
	public int getLocalId(){
		return this.localId;
	}

}
