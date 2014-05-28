package it.moise.plc.uml.jaxb_plcopen;

import it.moise.plc.uml.ConstantString;

import java.math.BigInteger;

import org.plcopen.xml.tc6_0201.AddData;
import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.ObjectFactory;
import org.plcopen.xml.tc6_0201.Position;
/**
 * Questa classe estende Body.SFC.SelectionDivergence creando un oggetto già completo di tutti i suoi valori ed elementi
 * @author De Carvalho Moïse
 *
 */
public class SelectionDivergenceTag extends Body.SFC.SelectionDivergence{
	private String name = "";
	/**
	 * Metodo costruttore che permette di creare un oggetto completo di valori ed elementi a partire dalla sua superclasse
	 * @param localId					: contiene il localId
	 * @param position					: contiene l'oggetto position completo di suoi valori
	 * @param refLocalId				: contiene il refLocalId (l'oggetto che lo precede)
	 * @param numberStateSelection	: il numero di stati che possono essere attivati automaticamente con le transizioni di initialState
	 * @param addData					: contiene l'oggetto addData completo di valori ed attributi
	 */
	public SelectionDivergenceTag(	int localId, 
										Position position,
										int refLocalId, 
										int numberStateSelection, 
										AddData addData) {
		super();
		this.createSelectionDivergence(localId, position, refLocalId, numberStateSelection, addData);
	}

	/**
	 * Metodo che permette di creare un oggetto completo di valori ed elementi a partire dalla sua superclasse
	 * @param localId					: contiene il localId
	 * @param position					: contiene l'oggetto position completo di suoi valori
	 * @param refLocalId				: contiene il refLocalId (l'oggetto che lo precede)
	 * @param numberStateSelection	: il numero di stati che possono essere attivati automaticamente con le transizioni di initialState
	 * @param addData					: contiene l'oggetto addData completo di valori ed attributi
	 */
	private void createSelectionDivergence(	int localId, 
												Position position, 
												int refLocalId, 
												int numberStateSelection, 
												AddData addData){
		Body.SFC.SelectionDivergence.ConnectionPointOut connectionPointOutSelection = new ObjectFactory().createBodySFCSelectionDivergenceConnectionPointOut();
		this.setLocalId(new BigInteger(String.valueOf(localId)));
		this.setName("selectionDivergence" + localId);
		this.setPosition(position);
		this.setConnectionPointIn(new ConnectionPointInTag(refLocalId));
		connectionPointOutSelection.setFormalParameter(ConstantString.SFC);
		for (int i=0; i < numberStateSelection; i++){
			this.getConnectionPointOut().add(connectionPointOutSelection);
		}
		this.setAddData(addData);
	}
	/**
	 * Imposta il nome in names in modo che sia visibile
	 * @param value
	 */
	public void setName(String value){
		this.name = value;
	}
	/**
	 * Restiuisce il nome
	 * @return names
	 */
	public String getName(){
		return this.name;
	}
}
