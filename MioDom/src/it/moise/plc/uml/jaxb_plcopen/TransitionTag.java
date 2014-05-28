package it.moise.plc.uml.jaxb_plcopen;

import it.moise.plc.uml.ConstantString;

import java.math.BigInteger;

import org.plcopen.xml.tc6_0201.AddData;
import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.ObjectFactory;
import org.plcopen.xml.tc6_0201.Position;
/**
 * Classe che permette di popolare la super classe avendo a disposizione diversi parametri in entrata dal metodo costruttore
 * @author De Carvalho Moïse 
 *
 */
public class TransitionTag extends Body.SFC.Transition{
	private Body.SFC.Transition.Condition condition = new ObjectFactory().createBodySFCTransitionCondition();
	private ConnectionPointInTag connectionPointIn;
	/**
	 * Metodo Costruttore con parametri
	 * @param position		: contiene un oggetto Position già impostato	
	 * @param localId		: contiene il localId di dell'oggetto transition instanziato
	 * @param firstContact	: contiene il localId utilizzato da connectionPointIn come refLocalId (primo contatto della transizione)
	 * @param lastContact	: contiene il localId utilizzato da connectionPointIn come ultimo contatto
	 * @param addData		: oggetto addData che contiene gli attributi
	 */
	public TransitionTag(Position position, int localId, int firstContact, int lastContact, AddData addData) {
		super();
		this.createTransition(position, localId, firstContact, lastContact, addData);
	}

	/**
	 * Metodo che viene utilizzato per popolare la super classe dei relativi valori che devono essere impostati
	 * @param position
	 * @param localId
	 * @param firstContact
	 * @param lastContact
	 * @param addData
	 */
	private void createTransition(Position position, int localId, int firstContact, int lastContact, AddData addData){
		
		this.setPosition(position);
		this.setLocalId(new BigInteger(String.valueOf(localId)));
		// primo contatto della transizione
		this.connectionPointIn = new ConnectionPointInTag(firstContact, ConstantString.SFC);
		this.setConnectionPointIn(this.connectionPointIn);
		// secondo contatto della transizione con la condition
		this.connectionPointIn = new ConnectionPointInTag(lastContact);
		this.condition.setConnectionPointIn(this.connectionPointIn);
		
		// aggiungo la condizione
		this.setCondition(this.condition);
		// aggiungo addData
		this.setAddData(addData);
	}
}
