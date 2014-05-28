package it.moise.plc.uml.jaxb_plcopen;

import org.plcopen.xml.tc6_0201.*;
/**
 * Classe che permette di creare un oggetto Project.Instances completo di tutti i suoi parametri
 * @author De Carvalho Moïse
 *
 */
public class InstancesTag extends Project.Instances {
	//private Project.Instances instances;
	Project.Instances.Configurations configurations;
	public InstancesTag() {
		super();
		this.createInstances();
	}

	public void createInstances(){
		this.configurations = new ObjectFactory().createProjectInstancesConfigurations();
		this.setConfigurations(this.configurations);
		
		
		System.out.println ("Oggetto Project.Instances creato");
	}
}
