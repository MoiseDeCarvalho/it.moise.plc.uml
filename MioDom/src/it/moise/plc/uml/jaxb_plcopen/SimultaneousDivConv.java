package it.moise.plc.uml.jaxb_plcopen;

import java.util.List;
import java.util.Vector;

public class SimultaneousDivConv {
	private String name = "";
	private Vector<Integer> listRefLocalId = new Vector<Integer>();
	private int localId;
	public SimultaneousDivConv(String name, int localId) {
		this.setLocalId(localId);
		this.setName(name);
	}
	
	/**
	 * Imposta il valore del nome di Simultaneous
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Restituisce il nome del simultaneous
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Aggiunge il valore di un refLocalId alla lista che poi deve essere chiusa con il simultaneous Convergence
	 * @param value
	 */
	public void setAddListRefLocalId(int value){
		this.listRefLocalId.add(value);
	}
	/**
	 * Restituisce la lista dei refLocalId che saranno utilizzati da simultaneousConvergence
	 * @return listRefLocalId : lista dei refLocalId che saranno utilizzati da simultaneousConvergence
	 */
	public List<Integer> getListRefLocalId(){
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
