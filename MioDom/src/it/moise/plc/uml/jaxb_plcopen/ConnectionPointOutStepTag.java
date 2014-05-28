package it.moise.plc.uml.jaxb_plcopen;

import it.moise.plc.uml.ConstantString;

import org.plcopen.xml.tc6_0201.Body;
/**
 * Questa classe è stata creata per estendere Body.SFC.Step.ConnectionPointOut, in modo tale da creare un oggetto già completo d tutti
 * i suoi elementi che servono alla sua creazione.
 * Questo oggetto è utilizzato all'interno dei STEP
 * @author De Carvalho Moïse
 *
 */
public class ConnectionPointOutStepTag extends Body.SFC.Step.ConnectionPointOut{

	public ConnectionPointOutStepTag() {
		super();
		this.createConnectionPointOutStep();
	}
	
	private void createConnectionPointOutStep(){
		this.setFormalParameter(ConstantString.SFC);
	}

}
