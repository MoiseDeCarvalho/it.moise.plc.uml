package it.moise.plc.uml.jaxb_plcopen;

import java.math.BigInteger;
import java.util.Vector;

import org.plcopen.xml.tc6_0201.AddData;
import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.Position;
/**
 * Classe che estende la superclasse utilizzando dei parametri in entrata in modo da creare un oggettto già popolato
 * @author De Carvalho Moïse
 *
 */
public class StepTag extends Body.SFC.Step{
	private ConnectionPointInTag connectionPointIn;
	private String simultaneousDivergenceName = "";
	private boolean simultaneousDivergence = false;
	private String selection = "";
	private boolean selectionDivergence = false;
	
	private int precLocalId = -1;
	
	ConnectionPointOutStepTag connectionPointOutStep = new ConnectionPointOutStepTag();
	/**
	 * Metodo costruttore che istanzia un oggetto completo dei suoi elementi necessari
	 * @param initialStep	: variabile booleana se TRUE, è lo Step Init
	 * @param localId		: contiene il localId
	 * @param name			: contiene il nome dello step
	 * @param position		: oggetto Position già settato
	 * @param refLocalId	: contiene il refLocalId
	 * @param addData		: oggetto addData già completo dei suoi elementi
	 */
	public StepTag(boolean initialStep, int localId, String name, Position position, int refLocalId, AddData addData, int precLocalId, String selection) {
		super();
		this.createStep(initialStep, localId, name, position, refLocalId, addData, selection);
	}
	/**
	 * Metodo server per popolare l'oggetto istanziato della superclasse che istanzia un oggetto completo dei suoi elementi necessari
	 * @param initialStep	: variabile booleana se TRUE, è lo Step Init
	 * @param localId		: contiene il localId
	 * @param name			: contiene il nome dello step
	 * @param position		: oggetto Position già settato
	 * @param refLocalId	: contiene il refLocalId
	 * @param addData		: oggetto addData già completo dei suoi elementi
	 */
	private void createStep(boolean initialStep, int localId,String name, Position position, int refLocalId, AddData addData, String selection){
		// questo vale solo per lo step Init
		if (initialStep)
			this.setInitialStep(initialStep);
		this.setLocalId(new BigInteger(String.valueOf(localId)));
		this.setName(name);
		this.setPosition(position);
		this.selection = selection;
		/*
		 *  Creazione dell'oggetto ConnectionPointInTag
		 *  se il valore di initialStep è TRUE, vuol dire che non c'è nessuna connessione prima dell step INIT quindi sarà:
		 *  <connectionPoinIn />
		 *  altrimenti viene utilizzato il valore di refLocalId, da passare a ConnectionPointInTag per creare un oggetto che si
		 *  connette ad un qualcosa di precedente
		 */
		if (initialStep){
			this.connectionPointIn = new ConnectionPointInTag();
			this.setConnectionPointIn(this.connectionPointIn);
		}
		else
		{
			this.connectionPointIn = new ConnectionPointInTag(refLocalId);
			this.setConnectionPointIn(this.connectionPointIn);
		}
		this.setConnectionPointOut(this.connectionPointOutStep);
		this.setAddData(addData);
	}
	
	/**
	 * Imposta il nome della simultaneousDivergence
	 * 
	 * @param name
	 */
	public void setSimultaneousDivergenceName(String name){
		this.simultaneousDivergenceName = name;
		this.setSimultaneousDivergence(true);
	}
	/**
	 * Restituisce il nome di SimultaneousDivergence
	 * @return simultaneousDivergenceName
	 */
	public String getSimultaneousDivergenceName(){
		return this.simultaneousDivergenceName;
	}
	/**
	 * Imposta il valore della variabile simultaneousDivergence a TRUE vuol dire dallo step esce una simultaneous
	 * @param value
	 */
	private void setSimultaneousDivergence(boolean value){
		this.simultaneousDivergence = value;
	}
	/**
	 * Restituisce il valore della variabile simultaneousDivergence, se TRUE c'è una simultaneous
	 * @return simultaneousDivergence
	 */
	public boolean getSimultaneousDivergence(){
		return this.simultaneousDivergence;
	}

	
	/**
	 * Aggiunge il nome della selection a cui appartiene
	 * 
	 * @param name
	 */
	public void setSelection(String name){
		this.selection = name;
	}
	/**
	 * Restituisce il nome della selection a cui appartiene
	 * @return selection
	 */
	public String getSelection(){
		return this.selection;
	}
	/**
	 * Imposta il valore della variabile selectionDivergence a TRUE vuol dire dallo step esce una selection
	 * @param value
	 */
	private void setSelectionDivergence(boolean value){
		this.selectionDivergence = value;
	}
	/**
	 * Restituisce il valore della variabile selectionDivergence, se TRUE c'è una selection
	 * @return simultaneousDivergence
	 */
	public boolean getSelectionDivergence(){
		return this.simultaneousDivergence;
	}
	/**
	 * Imposta il valore di precLocalId
	 * @param value
	 */
	public void setPrecLocalId(int value){
		this.precLocalId = value;
	}
	/**
	 * Ritorna il precLocalId
	 * @return precLocalId
	 */
	public int getPrecLocalId(){
		return this.precLocalId;
	}
}
