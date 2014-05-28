package it.moise.plc.uml.jaxb_plcopen;

import it.moise.plc.uml.ConstantString;

import java.math.BigInteger;

import org.plcopen.xml.tc6_0201.AddData;
import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.ObjectFactory;
import org.plcopen.xml.tc6_0201.Position;
/**
 * Questa classe estende Body.SFC.SimultaneousDivergence creando un oggeto già completo di tutti i suoi valori ed elementi
 * @author De Carvalho Moïse
 *
 */
public class SimultaneousDivergenceTag extends Body.SFC.SimultaneousDivergence{
	private String names = "";
	/**
	 * Metodo costruttore che permette di creare un oggetto completo di valori ed elementi a partire dalla sua superclasse
	 * @param localId					: contiene il localId
	 * @param position					: contiene l'oggetto position completo di suoi valori
	 * @param refLocalId				: contiene il refLocalId (l'oggetto che lo precede)
	 * @param numberStateSimultaneous	: il numero di stati che possono essere attivati automaticamente con le transizioni di initialState
	 * @param addData					: contiene l'oggetto addData completo di valori ed attributi
	 */
	public SimultaneousDivergenceTag(	int localId, 
										Position position,
										int refLocalId, 
										int numberStateSimultaneous, 
										AddData addData) {
		super();
		this.createSimultaneousDivergence(localId, position, refLocalId, numberStateSimultaneous, addData);
	}

	/**
	 * Metodo che permette di creare un oggetto completo di valori ed elementi a partire dalla sua superclasse
	 * @param localId					: contiene il localId
	 * @param position					: contiene l'oggetto position completo di suoi valori
	 * @param refLocalId				: contiene il refLocalId (l'oggetto che lo precede)
	 * @param numberStateSimultaneous	: il numero di stati che possono essere attivati automaticamente con le transizioni di initialState
	 * @param addData					: contiene l'oggetto addData completo di valori ed attributi
	 */
	private void createSimultaneousDivergence(	int localId, 
												Position position, 
												int refLocalId, 
												int numberStateSimultaneous, 
												AddData addData){
		Body.SFC.SimultaneousDivergence.ConnectionPointOut connectionPointOutSimultaneous = new ObjectFactory().createBodySFCSimultaneousDivergenceConnectionPointOut();
		this.setLocalId(new BigInteger(String.valueOf(localId)));
		this.setName("simultaneousDivergence" + localId);
		this.setNames("simultaneousDivergence" + localId);
		this.setPosition(position);
		this.setConnectionPointIn(new ConnectionPointInTag(refLocalId));
		connectionPointOutSimultaneous.setFormalParameter(ConstantString.SFC);
		for (int i=0; i < numberStateSimultaneous; i++){
			this.getConnectionPointOut().add(connectionPointOutSimultaneous);
		}
		this.setAddData(addData);
	}
	/**
	 * Imposta il nome in names in modo che sia visibile
	 * @param value
	 */
	public void setNames(String value){
		this.names = value;
	}
	/**
	 * Restiuisce il nome
	 * @return names
	 */
	public String getNames(){
		return this.names;
	}
}
