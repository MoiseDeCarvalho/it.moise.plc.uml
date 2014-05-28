package it.moise.plc.uml.jaxb_plcopen;

import java.math.BigInteger;

import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.ObjectFactory;
import org.plcopen.xml.tc6_0201.Position;
/**
 * Classe che permette di costruire un oggetto completo di tutti i suoi elementi base
 * @author De Carvalho Moïse
 *
 */
public class InVariableTag extends Body.SFC.InVariable {
	private String name = "";
	private int localId;
	private Position position = new ObjectFactory().createPosition();
	/**
	 * Metodo costruttore che istanzia l'oggetto, passando come paramentro il localId ed il nome della variabile
	 * localId : indica il numero dell'elemento all'interno del progetto SFC
	 * name : indica in nome della variabile
	 * @param name 
	 * @param localId
	 */
	public InVariableTag(String name, int localId, Position position) {
		super();
		this.setName(name);
		this.setLocalId(localId);
		this.setPositions(position);
		this.createInVariable();
	}


	/**
	 * Metodo che popola l'oggetto con i suoi elementi
	 */
	private void createInVariable(){
		this.setPosition(this.position);
		this.setLocalId(new BigInteger(String.valueOf(this.localId)));
		this.setExpression(this.name);
	}
	/**
	 * Metodo che imposta il parametro del nome della variabile
	 * @param name
	 */
	private void setName(String name){
		this.name = name;
	}
	/**
	 * metodo che imposta in localId della variabile
	 */
	private void setLocalId(int localId){
		this.localId = localId;
	}
	/**
	 * Metodo che imposta il valore di position
	 * @param position
	 */
	private void setPositions(Position position){
		this.position = position;
	}
}
