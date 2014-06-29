package it.moise.plc.uml.jaxb_plcopen;

import it.moise.plc.uml.ConstantString;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.Connection;
import org.plcopen.xml.tc6_0201.ObjectFactory;

public class SelectionConvergenceTag extends Body.SFC.SelectionConvergence {
	private Body.SFC.SelectionConvergence.ConnectionPointIn connectionPointInSelConvergence;
	private Connection connection;
	public SelectionConvergenceTag(int localId, Vector<Integer> arrayRefLocalId, int numberStateSelection) {
		super();
		this.createSelectionConvergence(localId, arrayRefLocalId, numberStateSelection);
	}

	private void createSelectionConvergence(int localId, Vector<Integer> arrayRefLocalId, int numberStateSelection){
		this.setLocalId(new BigInteger(String.valueOf(localId)));
		ConnectionPointInTag connectionPointIn;
		if (localId == 92)
			System.out.println("Errore");
		for (int i=0; i < numberStateSelection; i++){
			if (i < (numberStateSelection-1)){
				this.connectionPointInSelConvergence = new ObjectFactory().createBodySFCSelectionConvergenceConnectionPointIn();					
				this.connection = new ObjectFactory().createConnection();
				this.connection.setRefLocalId(new BigInteger(String.valueOf(arrayRefLocalId.get(i))));
				this.connectionPointInSelConvergence.getConnection().add(this.connection);
				this.getConnectionPointIn().add(this.connectionPointInSelConvergence);
			}
			else{
				this.connectionPointInSelConvergence = new ObjectFactory().createBodySFCSelectionConvergenceConnectionPointIn();					
				this.connection = new ObjectFactory().createConnection();
				this.connection.setRefLocalId(new BigInteger(String.valueOf(localId-1)));
				this.connectionPointInSelConvergence.getConnection().add(this.connection);
				this.getConnectionPointIn().add(this.connectionPointInSelConvergence);
			}
		}
	}
}
