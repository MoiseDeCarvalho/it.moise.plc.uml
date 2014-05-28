package it.moise.plc.uml.jaxb_plcopen;

import java.math.BigInteger;

import org.plcopen.xml.tc6_0201.Connection;
import org.plcopen.xml.tc6_0201.ConnectionPointIn;
import org.plcopen.xml.tc6_0201.ObjectFactory;
/**
 * Classe che estende ConnectionPointIn, prepara l'oggetto che sarà istanziato completo dei suoi elementi
 * @author De Carvalho Moïse
 *
 */
public class ConnectionPointInTag extends ConnectionPointIn{
	private int refLocalId;
	private Connection connection = new ObjectFactory().createConnection();
	/**
	 * Metodo costruttore, che prende in input:
	 * - refLocalId : sarà attribuito all'elemento connection
	 * - sfc : sarà impostato come valore all'attributo formalParameter di connection
	 * Questo metodo costruttore deve essere utilizzato solo quando si devono impostare entrambi i parametri e si usa in:
	 * - TRANSITION
	 * - SELECTION_CONVERGENCE
	 * @param refLocaId
	 */
	public ConnectionPointInTag(int refLocaId, String sfc) {
		super();
		this.setRefLocalId(refLocaId);
		this.createConnectionPointIn(refLocaId);
		if (sfc.length() > 0){
			this.setSfcFormalParameter(sfc);
		}
	}
	
	/**
	 * Metodo costruttore che prende in input:
	 * - refLocalId : sarà impostato come valore all'attributo di connection
	 * Questo metodo costruttore deve essere utilizzato solo quando si deve impostare il valore di refLocalid. Si usa con:
	 * - JUMP_STEP
	 * - STEP
	 * - CONDITION DI TRANSITION
	 * - SELECTION_DIVERGENCE
	 * - SIMULTANEOUS_DIVERGENCE
	 * @param refLocalId
	 */
	public ConnectionPointInTag(int refLocalId){
		super();
		this.connection.setRefLocalId(new BigInteger(String.valueOf(refLocalId)));
		this.getConnection().add(this.connection);
	}
	
	/**
	 * Metodo costruttore che istanzia solo un oggetto ConnectionPointIn vuoto.
	 * Questo metodo si usa con:
	 * - STEP
	 */
	public ConnectionPointInTag(){
		super();
	}
	/**
	 * Metodo che popola l'oggetto dei suoi elementi, in questo caso solo di connection
	 * @param refLocalId
	 */
	private void createConnectionPointIn(int refLocalId){
		this.connection.setRefLocalId(new BigInteger(String.valueOf(refLocalId)));
		this.getConnection().add(this.connection);
	}
	/**
	 * Metodo che imposta il valore di refLocalId
	 * @param refLocalId
	 */
	private void setRefLocalId(int refLocalId){
		this.refLocalId = refLocalId;
	}
	/**
	 * Metodo che imposta il valore di sfc
	 * @param sfc
	 */
	private void setSfcFormalParameter(String sfc){
		this.connection.setFormalParameter(sfc);
	}
}
