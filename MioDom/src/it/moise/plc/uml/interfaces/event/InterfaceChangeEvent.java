package it.moise.plc.uml.interfaces.event;

import it.moise.plc.uml.value.Value;

public interface InterfaceChangeEvent extends it.moise.plc.uml.interfaces.event.InterfaceEvent {
	 public void setChangeExpression(Value changeExpression);
	 public Value getChangeExpression();
}
