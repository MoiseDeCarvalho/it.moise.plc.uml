package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Signal;
import it.moise.plc.uml.TypeParameter;
import it.moise.plc.uml.value.LowerValue;
import it.moise.plc.uml.value.UpperValue;

public interface InterfaceVariable {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setXmiType(String xmiType);
	public String getXmiType();
	public void setTypeReference(String typeReference);
	public String getTypeReference();
	public void setIsOrdered(boolean isOrdered);
	public boolean getIsOrdered();
	public void setIsUnique(boolean isUnique);
	public boolean getIsUnique();
	
	public void setLowerValue(LowerValue lowerValue);
	public LowerValue getLowerValue();
	
	public void setUpperValue(UpperValue upperValue);
	public UpperValue getUpperValue();
	
	public void setIdTypeVariable(String idTypeVariable);
	public String getIdTypeVariable();
	
	public void setTypeVariable(TypeParameter typeVariable);
	public TypeParameter getTypeVariable();
	
	public void setTypeVariableSignal(Signal typeVariableSignal);
	public Signal getTypeVariableSignal();
	
	public void setVisibility(boolean visibility);
	public boolean getVisibility();
	
	
}
