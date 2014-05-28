package it.moise.plc.uml.jaxb_plcopen;

import it.moise.plc.uml.ConstantString;

import java.math.BigInteger;
import java.util.List;

import org.plcopen.xml.tc6_0201.Body;

public class SimultaneousConvergenceTag extends Body.SFC.SimultaneousConvergence {

	public SimultaneousConvergenceTag(int localId, List<Integer> arrayRefLocalId, int numberStateSimultaneous) {
		super();
		this.createSimultaneousConvergence(localId, arrayRefLocalId, numberStateSimultaneous);
	}

	private void createSimultaneousConvergence(int localId, List<Integer> arrayRefLocalId, int numberStateSimultaneous){
		this.setLocalId(new BigInteger(String.valueOf(localId)));
		ConnectionPointInTag connectionPointIn;
		for (int i=0; i < numberStateSimultaneous; i++){
			if (i < (numberStateSimultaneous-1)){
				connectionPointIn= new ConnectionPointInTag(arrayRefLocalId.get(i), ConstantString.SFC);
				this.getConnectionPointIn().add(connectionPointIn);
			}
			else{
				connectionPointIn= new ConnectionPointInTag(localId-1, ConstantString.SFC);
				this.getConnectionPointIn().add(connectionPointIn);
			}
		}
	}
}
