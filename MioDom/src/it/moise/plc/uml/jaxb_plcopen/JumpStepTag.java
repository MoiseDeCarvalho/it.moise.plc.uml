package it.moise.plc.uml.jaxb_plcopen;

import java.math.BigInteger;

import org.plcopen.xml.tc6_0201.AddData;
import org.plcopen.xml.tc6_0201.Body;
import org.plcopen.xml.tc6_0201.Position;

public class JumpStepTag extends Body.SFC.JumpStep {

	public JumpStepTag(String name, Position position, int localId, AddData addData) {
		super();
		this.createJumpStep( name,  position,  localId, addData);
	}

	private void createJumpStep(String name, Position position, int localId, AddData addData){
		this.setTargetName(name);
		this.setPosition(position);
		this.setLocalId(new BigInteger(String.valueOf(localId)));
		ConnectionPointInTag connectionPointIn = new ConnectionPointInTag(localId-1);
		this.setConnectionPointIn(connectionPointIn);
		this.setAddData(addData);
	}
}
